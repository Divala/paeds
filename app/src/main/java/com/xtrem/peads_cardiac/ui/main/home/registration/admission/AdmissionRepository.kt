package com.xtrem.peads_cardiac.ui.main.home.registration.admission

import com.xtrem.peads_cardiac.data.db.PatientRecordDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class AdmissionRepository(private val patientRecordDao: PatientRecordDao) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    suspend fun savePositiveData(
        status: String,
        adReason: String,
        treatment: String,
        date: String,
        outcomeStatus: String, reason: String, discharge: String, generalOutcome: String
    ) {
        withContext(Dispatchers.IO) {
            val patientRecordDB = patientRecordDao.getLastSavedRecord()

            patientRecordDB.admissionDate = date
            patientRecordDB.admissionReason = adReason
            patientRecordDB.admissionTreatment = treatment
            patientRecordDB.admissionStatus = status
            patientRecordDB.admissionOutcome = outcomeStatus
            patientRecordDB.deathReason = reason
            patientRecordDB.dischargedDate = discharge
            patientRecordDB.generalOutcome = generalOutcome
            patientRecordDao.update(patientRecordDB)
        }


    }

}