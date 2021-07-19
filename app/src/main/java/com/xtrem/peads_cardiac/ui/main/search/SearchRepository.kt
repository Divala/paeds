package com.xtrem.peads_cardiac.ui.main.search

import androidx.lifecycle.LiveData
import com.xtrem.peads_cardiac.PeadsApp
import com.xtrem.peads_cardiac.services.ServiceListener
import com.xtrem.peads_cardiac.data.records.Record
import kotlinx.coroutines.*

object SearchRepository {
    var job: CompletableJob? = null
    var serviceListener: ServiceListener? = null

    fun searchPatients(token: String, key: String, query: String): LiveData<List<Record>> {
        job = Job()
        serviceListener?.onStarted()
        return object : LiveData<List<Record>>() {

            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(Dispatchers.IO + theJob).launch {

                        try {
                            val response = PeadsApp.service?.search(token, key, query)

                            withContext(Dispatchers.Main) {

                                if (response?.isSuccessful!!) {

                                    return@withContext

                                }
                                serviceListener?.onFailure("Error. Please try again later")

                                theJob.complete()
                            }
                        } catch (e: Exception) {
                            withContext(Dispatchers.Main) {
                                serviceListener?.onFailure("Unable to connect to internet...")
                            }
                        }
                    }

                }

            }
        }
    }
}