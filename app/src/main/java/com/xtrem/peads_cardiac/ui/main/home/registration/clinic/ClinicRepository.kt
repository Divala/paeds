package com.xtrem.peads_cardiac.ui.main.home.registration.clinic

import com.xtrem.peads_cardiac.data.db.PatientRecordDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class ClinicRepository(val patientRecordDao: PatientRecordDao) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main


    suspend fun saveData(
        visitReason: String,
        nextVisitDate: String
    ) {
        withContext(Dispatchers.IO) {

            val patientRecord = patientRecordDao.getLastSavedRecord()

            patientRecord.visitReason = visitReason
            patientRecord.nextVisitDate = nextVisitDate
            patientRecord.uploadReady = true
            patientRecord.synced = false
            patientRecordDao.update(patientRecord)

        }
    }

    suspend fun saveSyncData() {
        withContext(Dispatchers.IO) {

            val patientRecord = patientRecordDao.getLastSavedRecordNotSync()
            patientRecord.synced = true
            patientRecordDao.update(patientRecord)
        }
    }


}
