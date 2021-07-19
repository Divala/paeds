package com.xtrem.peads_cardiac.ui.main.account.password

import androidx.lifecycle.LiveData
import com.xtrem.peads_cardiac.PeadsApp
import com.xtrem.peads_cardiac.services.ServiceListener
import com.xtrem.peads_cardiac.data.auth.AuthData
import kotlinx.coroutines.*

object ChangePasswordRepository {
    var job: CompletableJob? = null
    var serviceListener: ServiceListener? = null

    fun changePassword(token: String, key: String, oldPassword: String, newPassword: String): LiveData<AuthData> {
        job = Job()
        serviceListener?.onStarted()
        return object : LiveData<AuthData>() {

            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(Dispatchers.IO + theJob).launch {

                        try {

                            val response = PeadsApp.service?.changePassword(token, key,oldPassword, newPassword)

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