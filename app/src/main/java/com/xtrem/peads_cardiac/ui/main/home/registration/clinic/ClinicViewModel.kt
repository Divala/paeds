package com.xtrem.peads_cardiac.ui.main.home.registration.clinic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xtrem.peads_cardiac.data.db.PatientRecordDB
import com.xtrem.peads_cardiac.data.db.PatientRecordDao
import com.xtrem.peads_cardiac.data.records.Record
import com.xtrem.peads_cardiac.services.ApiHelper
import com.xtrem.peads_cardiac.services.ApiResult
import kotlinx.coroutines.launch

class ClinicViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ClinicRepository
    private val patientRecordDao: PatientRecordDao =
        PatientRecordDB.getDatabase(application.applicationContext).patientRecordDao()

    init {
        repository = ClinicRepository(patientRecordDao)
    }

    fun saveData(visitReason: String, nextVisitDate: String) {
        viewModelScope.launch {
            repository.saveData(visitReason, nextVisitDate)

        }
    }

    val post: MutableLiveData<List<Record>> by lazy {
        MutableLiveData<List<Record>>()
    }

    val errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun register(
        key: String,
        token: String,
        patientId: Int,
        name: String,
        dob: String,
        sex: String,
        phone: String,
        mg: String,
        admissionDate: String,
        visitNumber: Int,
        valvesMorphology: String,
        venousReturn: String,
        visitReason: String,
        admissionDuration: String,
        admissionOutcome: String,
        admissionReason: String,
        admissionStatus: String,
        dischargedDate: String,
        admissionTreatment: String,
        awaitingSurgery: String,
        bloodPressure: String,
        cardiacEco: String,
        cardiacMeasurements: String,
        cardiacOther: String,
        chestXRay: String,
        deathReason: String,
        diagnosisDetails: String,
        hb: String,
        headCircumference: String,
        heartRate: String,
        height: String,
        location: String,
        medicationDetails: String,
        muac: String,
        mvc: String,
        nextVisitDate: String,
        previousCardiacSurgery: String,
        pericardialEffusion: String,
        plt: String,
        pulseRate: String,
        situs: String,
        sponsor: String,
        surgery: String,
        surgeryDate: String,
        surgeryLocation: String,
        surgeryPeriod: String,
        surgeryReason: String,
        pvc: String,
        referralSource: String,
        referred: String,
        referredReason: String,
        respiratoryRate: String,
        greatVesselRelationsAortic: String,
        greatVesselRelationsCoronaries: String,
        greatVesselRelationsMeasurements: String,
        greatVesselRelationsState: String,
        weight: String,
        wbc: String,
        ca: String,
        cl: String,
        creatinine: String,
        k: String,
        na: String,
        urea: String,
        bmi: String,
        ageAtPresentation: String,
        secondaryPhone: String,
        generalOutcome: String
    ) {

        viewModelScope.launch {

            when (val retrofitPost = ApiHelper.register(
                token,
                key,
                patientId,
                name,
                dob,
                sex,
                phone,
                mg,
                admissionDate,
                visitNumber,
                valvesMorphology,
                venousReturn,
                visitReason,
                admissionDuration,
                admissionOutcome,
                admissionReason,
                admissionStatus,
                dischargedDate,
                admissionTreatment,
                awaitingSurgery,
                bloodPressure,
                cardiacEco,
                cardiacMeasurements,
                cardiacOther,
                chestXRay,
                deathReason,
                diagnosisDetails,
                hb,
                headCircumference,
                heartRate,
                height,
                location,
                medicationDetails,
                muac,
                mvc,
                nextVisitDate,
                previousCardiacSurgery,
                pericardialEffusion,
                plt,
                pulseRate,
                situs,
                sponsor,
                surgery,
                surgeryDate,
                surgeryLocation,
                surgeryPeriod,
                surgeryReason,
                pvc,
                referralSource,
                referred,
                referredReason,
                respiratoryRate,
                greatVesselRelationsAortic,
                greatVesselRelationsCoronaries,
                greatVesselRelationsMeasurements,
                greatVesselRelationsState,
                weight,
                wbc,
                ca,
                cl,
                creatinine,
                k,
                na,
                urea,
                bmi,
                ageAtPresentation,
                secondaryPhone,
                generalOutcome
            )) {
                is ApiResult.Success -> {
                    post.postValue(retrofitPost.data.records)
                }
                is ApiResult.Error -> {
                    errorMessage.postValue(retrofitPost.exception)
                }
            }
        }
    }

    fun setSync() {
        viewModelScope.launch {
            repository.saveSyncData()

        }
    }


}