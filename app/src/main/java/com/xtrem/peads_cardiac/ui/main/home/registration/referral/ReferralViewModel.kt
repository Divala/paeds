package com.xtrem.peads_cardiac.ui.main.home.registration.referral

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.xtrem.peads_cardiac.data.db.PatientRecordDB
import com.xtrem.peads_cardiac.data.db.PatientRecordDao
import kotlinx.coroutines.launch

class ReferralViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: ReferralRepository

    init {
        val patientRecordDao: PatientRecordDao =
            PatientRecordDB.getDatabase(application.applicationContext).patientRecordDao()
        repository = ReferralRepository(patientRecordDao)
    }

    fun saveData(source: String, reason: String, referralStatus: String) {
        viewModelScope.launch { repository.saveData(source, reason, referralStatus) }
    }
}