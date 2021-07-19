package com.xtrem.peads_cardiac.ui.main.home.registration.investigation

import com.xtrem.peads_cardiac.data.db.PatientRecordDB
import com.xtrem.peads_cardiac.data.db.PatientRecordDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class InvestigationRepository(val patientRecordDao: PatientRecordDao) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    suspend fun saveData(
        situs: String,
        venousReturn: String,
        vulvesMorphology: String,
        cardiacMeasurements: String,
        greatVesselMeasurements: String,
        other: String,
        greatVesselRelations: String,
        coronaries: String,
        aortic: String,
        pericardialEffusion: String
    ) {
        withContext(Dispatchers.IO) {
            val patientRecordDB = patientRecordDao.getLastSavedRecord()

            patientRecordDB.situs = situs
            patientRecordDB.venousReturn = venousReturn
            patientRecordDB.valvesMorphology = vulvesMorphology
            patientRecordDB.cardiacMeasurements = cardiacMeasurements
            patientRecordDB.greatVesselRelationsMeasurements = greatVesselMeasurements
            patientRecordDB.cardiacOther = other
            patientRecordDB.greatVesselRelationsState = greatVesselRelations
            patientRecordDB.greatVesselRelationsCoronaries = coronaries
            patientRecordDB.greatVesselRelationsAortic = aortic
            patientRecordDB.pericardialEffusion = pericardialEffusion

                patientRecordDao.update(patientRecordDB)
        }


    }
}