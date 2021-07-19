package com.xtrem.peads_cardiac.ui.main.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xtrem.peads_cardiac.data.records.Record
import com.xtrem.peads_cardiac.services.ApiHelper
import com.xtrem.peads_cardiac.services.ApiResult
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    val post: MutableLiveData<List<Record>> by lazy {
        MutableLiveData<List<Record>>()
    }

    val errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun search(token: String, key: String, query: String) {
        viewModelScope.launch {
            when (val retrofitPost = ApiHelper.searchPatients(token, key, query)) {

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