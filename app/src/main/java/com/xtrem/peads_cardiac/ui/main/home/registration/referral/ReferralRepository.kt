package com.xtrem.peads_cardiac.ui.main.home.registration.referral

import com.xtrem.peads_cardiac.data.db.PatientRecordDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class ReferralRepository(val patientRecordDao: PatientRecordDao) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    suspend fun saveData(source: String, reason: String, referralStatus: String) {

        withContext(Dispatchers.IO) {
            val patientRecordDB = patientRecordDao.getLastSavedRecord()

            patientRecordDB.referralSource = source
            patientRecordDB.referredReason = reason
            patientRecordDB.referred = referralStatus

            patientRecordDao.update(patientRecordDB)


        }
    }
}