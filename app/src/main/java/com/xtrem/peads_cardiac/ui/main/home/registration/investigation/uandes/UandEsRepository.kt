package com.xtrem.peads_cardiac.ui.main.home.registration.investigation.uandes

import com.xtrem.peads_cardiac.data.db.PatientRecordDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class UandEsRepository(val patientRecordDao: PatientRecordDao) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    suspend fun saveData(
        na: String,
        k: String,
        cl: String,
        mg: String,
        ca: String,
        urea: String,
        creatine: String,
        pvc: String
    ) {

        withContext(Dispatchers.IO) {
            val patientRecordDB = patientRecordDao.getLastSavedRecord()

            patientRecordDB.na = na
            patientRecordDB.k = k
            patientRecordDB.cl = cl
            patientRecordDB.mg = mg
            patientRecordDB.ca = ca
            patientRecordDB.urea = urea
            patientRecordDB.creatinine = creatine
            patientRecordDB.pvc = pvc

            patientRecordDao.update(patientRecordDB)
        }


    }
}