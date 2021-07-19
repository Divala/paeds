package com.xtrem.peads_cardiac.ui.main.home.registration.diagnosis

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.xtrem.peads_cardiac.data.db.PatientRecordDB
import kotlinx.coroutines.launch

class DiagnosisViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: DiagnosisRepository

    init {
        val patientRecordDao =
            PatientRecordDB.getDatabase(application.applicationContext).patientRecordDao()
        repository = DiagnosisRepository(patientRecordDao)
    }

    fun saveData(diagnosis: String) {
        viewModelScope.launch { repository.saveData(diagnosis) }
    }
}