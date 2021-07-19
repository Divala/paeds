package com.xtrem.peads_cardiac.ui.main.home.visits

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xtrem.peads_cardiac.data.records.Record
import com.xtrem.peads_cardiac.services.ApiHelper
import com.xtrem.peads_cardiac.services.ApiResult
import kotlinx.coroutines.launch

class PatientVisitsViewModel : ViewModel() {
    val post: MutableLiveData<List<Record>> by lazy {
        MutableLiveData<List<Record>>()
    }

    val errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun patientsVisits(token: String, key: String, patient_id: Int) {
        viewModelScope.launch {
            when (val retrofitPost = ApiHelper.getPatientRecords(token, key, patient_id)) {
                is ApiResult.Success -> {
                    post.postValue(retrofitPost.data.records)
                }
                is ApiResult.Error -> {
                    errorMessage.postValue(retrofitPost.exception)
                }
            }

        }
    }

}