package com.xtrem.peads_cardiac.ui.main.home.registration.anthropometry

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.xtrem.peads_cardiac.data.db.PatientRecordDB
import kotlinx.coroutines.launch

class AnthropometricViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: AnthropometricRepository

    init {
        val patientRecordDao =
            PatientRecordDB.getDatabase(application.applicationContext).patientRecordDao()
        repository = AnthropometricRepository(patientRecordDao)
    }

    fun saveData(
        weight: String,
        height: String,
        muac: String,
        headCircumference: String
    ) {

        viewModelScope.launch {
            repository.saveData(weight, height, muac, headCircumference)

        }
    }
}
