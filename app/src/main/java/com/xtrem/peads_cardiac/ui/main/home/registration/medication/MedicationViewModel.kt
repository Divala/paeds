package com.xtrem.peads_cardiac.ui.main.home.registration.medication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.xtrem.peads_cardiac.data.db.PatientRecordDB
import com.xtrem.peads_cardiac.data.db.PatientRecordDao
import kotlinx.coroutines.launch

class MedicationViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: MedicationRepository

    init {
        val patientRecordDao: PatientRecordDao =
            PatientRecordDB.getDatabase(application.applicationContext).patientRecordDao()
        repository = MedicationRepository(patientRecordDao)
    }

    fun saveData(details: String) {
        viewModelScope.launch {
            repository.saveData(details)
        }
    }
}