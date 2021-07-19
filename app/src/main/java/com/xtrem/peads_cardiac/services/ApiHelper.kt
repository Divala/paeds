package com.xtrem.peads_cardiac.services

import android.util.Log
import com.google.gson.JsonParser
import com.xtrem.peads_cardiac.PeadsApp
import com.xtrem.peads_cardiac.data.auth.AuthResponse
import com.xtrem.peads_cardiac.data.records.PatientRecordsResponse
import okhttp3.ResponseBody
import retrofit2.Response


object ApiHelper {

    suspend fun login(key: String, email: String, password: String): ApiResult<AuthResponse> {

        return safeApiCall(call = { PeadsApp.service?.attemptLogin(key, email, password)!! })
    }

    suspend fun register(
        token: String,
        key: String,
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
    ): ApiResult<PatientRecordsResponse> {
        return safeApiCall(call = {
            PeadsApp.service?.registerPatient(
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
            )!!
        })
    }


    private suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): ApiResult<T> {

        return try {

            val myResp = call.invoke()

            when {
                myResp.isSuccessful -> {
                    ApiResult.Success(myResp.body()!!)
                }

                myResp.code() == 401 -> {

                    ApiResult.Error("Wrong password or username")

                }

                else -> {
                    ApiResult.Error(
                        myResp.errorBody()?.let { getRequestErrorMessage(it) }
                            ?: "Something went wrong. Try again later"
                    )
                }
            }


        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "No internet connection")
        }
    }

    private fun getRequestErrorMessage(error: ResponseBody): String? {
        return try {
            val json = JsonParser().parse(error.string()).asJsonObject
            json.getAsJsonObject("result")["message"].asString
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            "Something went wrong. Try again later"
        }
    }


    suspend fun searchPatients(
        token: String,
        key: String,
        query: String
    ): ApiResult<PatientRecordsResponse> {
        return safeApiCall { PeadsApp.service?.search(token, key, query)!! }
    }

    suspend fun getPatientRecords(
        token: String,
        key: String,
        patientId: Int
    ): ApiResult<PatientRecordsResponse> {
        return safeApiCall { PeadsApp.service?.getVisits(token, key, patientId)!! }
    }

}