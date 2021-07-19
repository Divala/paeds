package com.xtrem.peads_cardiac.ui.main.home.visits

import androidx.lifecycle.LiveData
import com.xtrem.peads_cardiac.PeadsApp
import com.xtrem.peads_cardiac.services.ServiceListener
import com.xtrem.peads_cardiac.data.records.PatientRecord
import kotlinx.coroutines.*

object PatientVisitsRepository {
    var job: CompletableJob? = null
    var serviceListener: ServiceListener? = null

    fun getVisits(token: String, key: String, patientId: Int): LiveData<List<PatientRecord>> {
        job = Job()
        serviceListener?.onStarted()
        return object : LiveData<List<PatientRecord>>() {

            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(Dispatchers.IO + theJob).launch {

                        try {

                            val response = PeadsApp.service?.getVisits(token, key, patientId)

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