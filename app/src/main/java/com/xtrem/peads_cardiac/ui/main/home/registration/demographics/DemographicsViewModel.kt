package com.xtrem.peads_cardiac.ui.main.home.registration.demographics

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.xtrem.peads_cardiac.data.db.PatientRecordDB
import kotlinx.coroutines.launch

class DemographicsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DemographicsRepository

    init {
        val patientRecordDao =
            PatientRecordDB.getDatabase(application.applicationContext).patientRecordDao()
        repository = DemographicsRepository(patientRecordDao)
    }

    fun saveData(patientId: Int, visitNumber: Int, name: String, dob: String, gender: String, location: String, phone: String, secPhone: String) {

        viewModelScope.launch {
            repository.saveData(patientId, visitNumber, name, dob, gender, location, phone, secPhone)
        }
    }
}