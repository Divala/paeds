package com.xtrem.peads_cardiac.ui.main.home.registration.anthropometry

import com.xtrem.peads_cardiac.data.db.PatientRecordDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class AnthropometricRepository(private val patientDao: PatientRecordDao) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    suspend fun saveData(
        weight: String,
        height: String,
        muac: String,
        headCircumference: String
    ) {

        withContext(Dispatchers.IO) {
            val patientRecordDB = patientDao.getLastSavedRecord()

            patientRecordDB.weight = weight
            patientRecordDB.height = height
            patientRecordDB.muac = muac
            patientRecordDB.headCircumference = headCircumference

            patientDao.update(patientRecordDB)
        }
    }


}