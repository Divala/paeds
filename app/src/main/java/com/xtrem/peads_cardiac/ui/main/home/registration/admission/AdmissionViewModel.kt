package com.xtrem.peads_cardiac.ui.main.home.registration.admission

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.xtrem.peads_cardiac.data.db.PatientRecordDB
import com.xtrem.peads_cardiac.data.db.PatientRecordDao
import kotlinx.coroutines.launch

class AdmissionViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: AdmissionRepository

    init {
        val patientRecordDao: PatientRecordDao =
            PatientRecordDB.getDatabase(application.applicationContext).patientRecordDao()
        repository = AdmissionRepository(patientRecordDao)
    }

    fun saveData(
        status: String,
        adReason: String,
        treatment: String,
        date: String,
        outcomeStatus: String,
        reason: String,
        discharge: String,
        generalOutcome: String
    ) {
        viewModelScope.launch {
            repository.savePositiveData(
                status,
                adReason,
                treatment,
                date,
                outcomeStatus,
                reason,
                discharge,
                generalOutcome
            )

        }
    }

}