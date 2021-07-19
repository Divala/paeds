package com.xtrem.peads_cardiac.ui.main.home.registration.demographics

import com.xtrem.peads_cardiac.data.db.PatientRecordDao
import com.xtrem.peads_cardiac.data.records.PatientRecord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class DemographicsRepository(var peadsApp: PatientRecordDao) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    suspend fun saveData(
        patientId: Int,
        visitNumber: Int,
        name: String,
        dob: String,
        gender: String,
        location: String,
        phone: String,
        secPhone: String
    ) {

        withContext(Dispatchers.IO) {
            val patientRecordDB = PatientRecord()

            patientRecordDB.id = System.currentTimeMillis()
            patientRecordDB.visitNumber = visitNumber
            patientRecordDB.patientId = patientId
            patientRecordDB.name = name
            patientRecordDB.dob = dob
            patientRecordDB.sex = gender
            patientRecordDB.location = location
            patientRecordDB.phone = phone
            patientRecordDB.secondaryPhone = secPhone

            peadsApp.insert(patientRecordDB)
        }


    }
}