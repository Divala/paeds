package com.xtrem.peads_cardiac.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xtrem.peads_cardiac.data.auth.AuthData
import com.xtrem.peads_cardiac.services.ApiHelper
import com.xtrem.peads_cardiac.services.ApiResult
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    val post: MutableLiveData<AuthData> by lazy {
        MutableLiveData<AuthData>()
    }

    val errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun attemptLogin(key: String, email: String, password: String) {

        viewModelScope.launch {
            when (val retrofitPost = ApiHelper.login(key, email, password)) {
                is ApiResult.Success -> {
                    post.postValue(retrofitPost.data.data)
                }
                is ApiResult.Error -> {
                    errorMessage.postValue(retrofitPost.exception)
                }
            }
        }
    }

}