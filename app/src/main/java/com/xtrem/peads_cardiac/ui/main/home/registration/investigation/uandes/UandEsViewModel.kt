package com.xtrem.peads_cardiac.ui.main.home.registration.investigation.uandes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.xtrem.peads_cardiac.data.db.PatientRecordDB
import kotlinx.coroutines.launch

class UandEsViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: UandEsRepository

    init {
        val patientRecordDao =
            PatientRecordDB.getDatabase(application.applicationContext).patientRecordDao()
        repository = UandEsRepository(patientRecordDao)
    }

    fun saveData(
        na: String,
        k: String,
        cl: String,
        mg: String,
        ca: String,
        urea: String,
        creatine: String,
        pvc: String
    ) {
        viewModelScope.launch {
            repository.saveData(na, k, cl, mg, ca, urea, creatine, pvc)
        }
    }
}