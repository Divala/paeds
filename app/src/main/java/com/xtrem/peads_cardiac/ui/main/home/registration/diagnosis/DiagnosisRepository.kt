package com.xtrem.peads_cardiac.ui.main.home.registration.diagnosis

import com.xtrem.peads_cardiac.data.db.PatientRecordDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class DiagnosisRepository(val patientDao: PatientRecordDao) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    suspend fun saveData(diagnosis: String) {

        withContext(Dispatchers.IO) {
            val patientRecordDB = patientDao.getLastSavedRecord()

            patientRecordDB.diagnosisDetails = diagnosis

            patientDao.update(patientRecordDB)
        }

    }

}