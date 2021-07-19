package com.xtrem.peads_cardiac.ui.main.home.registration.surgical

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.xtrem.peads_cardiac.data.db.PatientRecordDB
import kotlinx.coroutines.launch

class SurgicalViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: SurgicalRepository

    init {
        val patientRecordDao =
            PatientRecordDB.getDatabase(application.applicationContext).patientRecordDao()
        repository = SurgicalRepository(patientRecordDao)
    }

    fun saveData(
        reason: String,
        location: String,
        surgeryDate: String,
        surgerySponsor: String,
        awaitingSurgeryStatus: String,
        cardiacSurgeryStatus: String,
        eligibilityStatus: String
    ) {
        viewModelScope.launch {
            repository.saveData(
                reason,
                location,
                surgeryDate,
                surgerySponsor,
                awaitingSurgeryStatus,
                cardiacSurgeryStatus,
                eligibilityStatus
            )
        }
    }
}