package com.xtrem.peads_cardiac.ui.main.home.registration.medication

import com.xtrem.peads_cardiac.data.db.PatientRecordDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class MedicationRepository(val patientRecordDao: PatientRecordDao) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    suspend fun saveData(details: String) {

        withContext(Dispatchers.IO) {
            val patientRecordDB = patientRecordDao.getLastSavedRecord()

            patientRecordDB.medicationDetails = details

            patientRecordDao.update(patientRecordDB)


        }
    }
}