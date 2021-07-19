package com.xtrem.peads_cardiac.ui.main.home

import androidx.lifecycle.LiveData
import com.xtrem.peads_cardiac.PeadsApp
import com.xtrem.peads_cardiac.data.records.Record
import com.xtrem.peads_cardiac.data.records.stats.StatsData
import com.xtrem.peads_cardiac.services.ServiceListener
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import java.io.IOException

object HomeRepository {
    var job: CompletableJob? = null
    var serviceListener: ServiceListener? = null

    fun getPatients(token: String, key: String): LiveData<List<Record>> {

        job = Job()
        serviceListener?.onStarted()

        return object : LiveData<List<Record>>() {

            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {

                        try {

                            val response = PeadsApp.service?.getTodayPatients(token, key)

                            withContext(Main) {

                                if (response?.isSuccessful!!) {
                                    value = response.body()?.records

                                    return@withContext
                                }

                                serviceListener?.onFailure("Error, try again later")

                                theJob.complete()
                            }
                        } catch (e: IOException) {
                            withContext(Main) {
                                serviceListener?.onFailure("Unable to connect to internet...")
                            }

                        }
                    }

                }

            }
        }

    }

    fun getStats(token: String, key: String): LiveData<StatsData> {
        job = Job()
        serviceListener?.onStarted()

        return object : LiveData<StatsData>() {

            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {

                        try {

                            val response = PeadsApp.service?.getStatSummary(token, key)

                            withContext(Main) {

                                if (response?.isSuccessful!!) {
                                    value = response.body()?.data

                                    return@withContext
                                }

                                serviceListener?.onFailure("Error, try again later")

                                theJob.complete()
                            }
                        } catch (e: IOException) {
                            withContext(Main) {
                                serviceListener?.onFailure("Unable to connect to internet...")
                            }

                        }
                    }

                }

            }
        }
    }
}