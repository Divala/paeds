package com.xtrem.peads_cardiac.ui.main.home.registration.investigation.fbc

import com.xtrem.peads_cardiac.data.db.PatientRecordDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class FbcRepository(var patientRecordDao: PatientRecordDao) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    suspend fun saveData(
        wbc: String,
        hb: String,
        plt: String,
        mcv: String,
        chestXray: String
    ) {
        withContext(Dispatchers.Main) {
            val patientRecordDB = patientRecordDao.getLastSavedRecord()

            patientRecordDB.wbc = wbc
            patientRecordDB.hb = hb
            patientRecordDB.plt = plt
            patientRecordDB.mvc = mcv
            patientRecordDB.chestXRay = chestXray

            patientRecordDao.update(patientRecordDB)
        }


    }
}