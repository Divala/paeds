package com.xtrem.peads_cardiac.ui.main.account.password

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xtrem.peads_cardiac.data.auth.AuthData

class ChangePasswordViewModel : ViewModel() {
    fun changePassword(token: String, key: String, oldPassword: String, newPassword: String): LiveData<AuthData> {

        return ChangePasswordRepository.changePassword(token, key,oldPassword, newPassword)
    }
}