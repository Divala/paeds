package com.xtrem.peads_cardiac.ui.main.home.registration.vital_signs

import com.xtrem.peads_cardiac.data.db.PatientRecordDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class VitalSignsRepository(val patientDao: PatientRecordDao) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    suspend fun saveData(
        heartRate: String,
        pulseRate: String,
        respiratoryRate: String,
        bloodPressure: String
    ) {

        withContext(Dispatchers.IO) {
            val patientRecordDB = patientDao.getLastSavedRecord()

            patientRecordDB.heartRate = heartRate
            patientRecordDB.pulseRate = pulseRate
            patientRecordDB.respiratoryRate = respiratoryRate
            patientRecordDB.bloodPressure = bloodPressure

            patientDao.update(patientRecordDB)
        }


    }
}