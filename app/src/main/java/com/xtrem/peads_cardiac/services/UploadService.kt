package com.xtrem.peads_cardiac.services

import androidx.lifecycle.LiveData
import com.xtrem.peads_cardiac.PeadsApp
import com.xtrem.peads_cardiac.data.records.PatientRecord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class UploadService() {

    public fun upload(
        patientRecord: PatientRecord,
        uploadListener: UploadListener, token: String, key: String
    ): LiveData<PatientRecord> {

        val admissionDate: String = patientRecord.admissionDate.toString()
        val admissionDuration: String = patientRecord.admissionDuration.toString()
        val admissionOutcome: String = patientRecord.admissionOutcome.toString()
        val admissionReason: String = patientRecord.admissionReason.toString()
        val admissionStatus: String = patientRecord.admissionStatus.toString()
        val admissionTreatment: String = patientRecord.admissionDuration.toString()
        val age: String = patientRecord.age.toString()
        val awaitingSurgery: String = patientRecord.awaitingSurgery.toString()
        val bloodPressure: String = patientRecord.bloodPressure.toString()
        val cardiacEco: String = patientRecord.cardiacEco.toString()
        val chestXRay: String = patientRecord.chestXRay.toString()
        val deathReason: String = patientRecord.deathReason.toString()
        val diagnosisDetails: String = patientRecord.diagnosisDetails.toString()
        val dob: String = patientRecord.dob.toString()
        val headCircumference: String = patientRecord.headCircumference.toString()
        val heartRate: String = patientRecord.heartRate.toString()
        val height: String = patientRecord.height.toString()
        val location: String = patientRecord.location.toString()
        val medicationDetails: String = patientRecord.medicationDetails.toString()
        val muac: String = patientRecord.muac.toString()
        val name: String = patientRecord.name.toString()
        val nextVisitDate: String = patientRecord.nextVisitDate.toString()
        val phone: String = patientRecord.phone.toString()
        val previousCardiacSurgery: String = patientRecord.previousCardiacSurgery.toString()
        val pulseRate: String = patientRecord.pulseRate.toString()
        val pvc: String = patientRecord.pvc.toString()
        val referralSource: String = patientRecord.referralSource.toString()
        val referred: String = patientRecord.referred.toString()
        val referredReason: String = patientRecord.referredReason.toString()
        val respiratoryRate: String = patientRecord.respiratoryRate.toString()
        val sex: String = patientRecord.sex.toString()
        val sponsor: String = patientRecord.sponsor.toString()
        val surgery: String = patientRecord.surgery.toString()
        val surgeryDate: String = patientRecord.surgeryDate.toString()
        val surgeryLocation: String = patientRecord.surgeryLocation.toString()
        val surgeryPeriod: String = patientRecord.surgeryPeriod.toString()
        val surgeryReason: String = patientRecord.surgeryReason.toString()
        val visitNumber: Int = patientRecord.visitNumber!!.toInt()
        val visitReason: String = patientRecord.visitReason.toString()
        val cardiacMeasurements: String = patientRecord.cardiacMeasurements.toString()
        val cardiacOther: String = patientRecord.cardiacOther.toString()
        val greatVesselRelationsAortic: String = patientRecord.greatVesselRelationsAortic.toString()
        val greatVesselRelationsCoronaries: String =
            patientRecord.greatVesselRelationsCoronaries.toString()
        val greatVesselRelationsMeasurements: String =
            patientRecord.greatVesselRelationsMeasurements.toString()
        val greatVesselRelationsState: String = patientRecord.greatVesselRelationsState.toString()
        val pericardialEffusion: String = patientRecord.pericardialEffusion.toString()
        val situs: String = patientRecord.situs.toString()
        val valvesMorphology: String = patientRecord.valvesMorphology.toString()
        val venousReturn: String = patientRecord.venousReturn.toString()
        val weight: String = patientRecord.weight.toString()
        val hb: String = patientRecord.hb.toString()
        val mvc: String = patientRecord.mvc.toString()
        val plt: String = patientRecord.plt.toString()
        val wbc: String = patientRecord.wbc.toString()
        val cl: String = patientRecord.cl.toString()
        val ca: String = patientRecord.ca.toString()
        val creatinine: String = patientRecord.creatinine.toString()
        val k: String = patientRecord.k.toString()
        val mg: String = patientRecord.mg.toString()
        val na: String = patientRecord.na.toString()
        val urea: String = patientRecord.urea.toString()


        uploadListener.onStarted()

        return object : LiveData<PatientRecord>() {

            override fun onActive() {
                super.onActive()
                CoroutineScope(IO).launch {

                    try {

                        val response = PeadsApp.service?.registerPatient(
                            token,
                            key,
                            1,
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
                            "",
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
                            "",
                            "",
                            "",
                            ""
                        )

                        withContext(Main) {

                            if (response?.isSuccessful!!) {

                                uploadListener.onSuccess()

                                return@withContext
                            }

                            uploadListener.onFailure("Error, try again later")

                        }
                    } catch (e: IOException) {
                        withContext(Main) {
                            uploadListener.onFailure("Unable to connect to internet...")
                        }

                    }
                }

            }

        }

    }

    interface UploadListener {
        fun onStarted()

        fun onSuccess()

        fun onFailure(error: String)
    }
}