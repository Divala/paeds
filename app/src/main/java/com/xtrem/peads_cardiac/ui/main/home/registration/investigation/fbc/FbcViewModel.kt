package com.xtrem.peads_cardiac.ui.main.home.registration.investigation.fbc

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.xtrem.peads_cardiac.data.db.PatientRecordDB
import kotlinx.coroutines.launch

class FbcViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: FbcRepository

    init {
        val patientRecordDao =
            PatientRecordDB.getDatabase(application.applicationContext).patientRecordDao()
        repository = FbcRepository(patientRecordDao)
    }


    fun saveData(
        wbc: String,
        hb: String,
        plt: String,
        mcv: String,
        chestXray: String
    ) {
        viewModelScope.launch {
            repository.saveData(wbc, hb, plt, mcv, chestXray)
        }

    }
}