package com.xtrem.peads_cardiac.ui.main.home.registration.surgical

import com.xtrem.peads_cardiac.data.db.PatientRecordDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class SurgicalRepository(val patientRecordDao: PatientRecordDao) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    suspend fun saveData(
        reason: String,
        location: String,
        surgeryDate: String,
        surgerySponsor: String,
        awaitingSurgeryStatus: String,
        cardiacSurgeryStatus: String,
        eligibilityStatus: String
    ) {

        withContext(Dispatchers.IO) {
            val patientRecordDB = patientRecordDao.getLastSavedRecord()

            patientRecordDB.surgeryLocation = location
            patientRecordDB.surgeryReason = reason
            patientRecordDB.surgeryDate = surgeryDate
            patientRecordDB.sponsor = surgerySponsor
            patientRecordDB.awaitingSurgery = awaitingSurgeryStatus
            patientRecordDB.surgery = eligibilityStatus
            patientRecordDB.previousCardiacSurgery = cardiacSurgeryStatus

            patientRecordDao.update(patientRecordDB)

        }


    }
}