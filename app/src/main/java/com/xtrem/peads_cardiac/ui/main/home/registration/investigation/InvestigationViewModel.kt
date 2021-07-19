package com.xtrem.peads_cardiac.ui.main.home.registration.investigation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.xtrem.peads_cardiac.data.db.PatientRecordDB
import kotlinx.coroutines.launch

class InvestigationViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: InvestigationRepository

    init {
        val patientRecordDao =
            PatientRecordDB.getDatabase(application.applicationContext).patientRecordDao()
        repository = InvestigationRepository(patientRecordDao)
    }

    fun saveData(
        situs: String,
        venousReturn: String,
        vulvesMorphology: String,
        cardiacMeasurements: String,
        greatVesselMeasurements: String,
        other: String,
        greatVesselRelations: String,
        coronaries: String,
        aortic: String,
        pericardialEffusion: String
    ) {
        viewModelScope.launch {
            repository.saveData(
                situs,
                venousReturn,
                vulvesMorphology,
                cardiacMeasurements,
                greatVesselMeasurements,
                other,
                greatVesselRelations,
                coronaries,
                aortic,
                pericardialEffusion
            )
        }
    }
}