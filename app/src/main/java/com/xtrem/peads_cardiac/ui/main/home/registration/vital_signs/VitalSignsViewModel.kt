package com.xtrem.peads_cardiac.ui.main.home.registration.vital_signs

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.xtrem.peads_cardiac.data.db.PatientRecordDB
import kotlinx.coroutines.launch

class VitalSignsViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: VitalSignsRepository

    init {
        val patientRecordDao =
            PatientRecordDB.getDatabase(application.applicationContext).patientRecordDao()
        repository = VitalSignsRepository(patientRecordDao)
    }

    fun saveData(
        heartRate: String,
        pulseRate: String,
        respiratoryRate: String,
        bloodPressure: String
    ) {
        viewModelScope.launch {
            repository.saveData(heartRate, pulseRate, respiratoryRate, bloodPressure)

        }
    }
}