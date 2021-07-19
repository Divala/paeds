package com.xtrem.peads_cardiac.services

import com.xtrem.peads_cardiac.data.auth.AuthResponse
import com.xtrem.peads_cardiac.data.records.PatientRecordsResponse
import com.xtrem.peads_cardiac.data.records.stats.StatsResponse
import retrofit2.Response
import retrofit2.http.*

interface PeadsService {
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun attemptLogin(
        @Header("key") key: String,
        @Query(encoded = true, value = "email") email: String,
        @Field("password") password: String
    ): Response<AuthResponse>

    @FormUrlEncoded
    @POST("auth/reset-password")
    suspend fun changePassword(
        @Header("Authorization") token: String,
        @Header("key") key: String,
        @Field("old") email: String,
        @Field("new") password: String
    ): Response<AuthResponse>

    @GET("patients/today")
    suspend fun getTodayPatients(
        @Header("Authorization") token: String,
        @Header("key") key: String
    ): Response<PatientRecordsResponse>

    @GET("patients/summary")
    suspend fun getStatSummary(
        @Header("Authorization") token: String,
        @Header("key") key: String
    ): Response<StatsResponse>

    @FormUrlEncoded
    @POST("patients/record")
    suspend fun getVisits(
        @Header("Authorization") token: String,
        @Header("key") key: String,
        @Field("patient_id") patientId: Int
    ): Response<PatientRecordsResponse>

    @FormUrlEncoded
    @POST("patients/search")
    suspend fun search(
        @Header("Authorization") token: String,
        @Header("key") key: String,
        @Field("query") query: String
    ): Response<PatientRecordsResponse>

    @FormUrlEncoded
    @POST("patients/register")
    suspend fun registerPatient(
        @Header("Authorization") token: String,
        @Header("key") key: String,
        @Field("patient_id") patientId: Int,
        @Field("name") name: String,
        @Field("dob") dob: String,
        @Field("sex") sex: String,
        @Field("phone") phone: String,
        @Field("mg") mg: String,
        @Field("admission_date") admissionDate: String,
        @Field("visit_number") visitNumber: Int,
        @Field("valves_morphology") valvesMorphology: String,
        @Field("venous_return") venousReturn: String,
        @Field("visit_reason") visitReason: String,
        @Field("admission_duration") admissionDuration: String,
        @Field("admission_outcome") admissionOutcome: String,
        @Field("admission_reason") admissionReason: String,
        @Field("admission_status") admissionStatus: String,
        @Field("discharge_date") dischargedDate: String,
        @Field("admission_treatment") admissionTreatment: String,
        @Field("awaiting_surgery") awaitingSurgery: String,
        @Field("blood_pressure") bloodPressure: String,
        @Field("cardiac_eco") cardiacEco: String,
        @Field("cardiac_measurements") cardiacMeasurements: String,
        @Field("cardiac_other") cardiacOther: String,
        @Field("chest_x_ray") chestXRay: String,
        @Field("death_reason") deathReason: String,
        @Field("diagnosis_details") diagnosisDetails: String,
        @Field("hb") hb: String,
        @Field("head_circumference") headCircumference: String,
        @Field("heart_rate") heartRate: String,
        @Field("height") height: String,
        @Field("location") location: String,
        @Field("medication_details") medicationDetails: String,
        @Field("muac") muac: String,
        @Field("mvc") mvc: String,
        @Field("next_visit_date") nextVisitDate: String,
        @Field("previous_cardiac_surgery") previousCardiacSurgery: String,
        @Field("pericardial_effusion") pericardialEffusion: String,
        @Field("plt") plt: String,
        @Field("pulse_rate") pulseRate: String,
        @Field("situs") situs: String,
        @Field("sponsor") sponsor: String,
        @Field("surgery") surgery: String,
        @Field("surgery_date") surgeryDate: String,
        @Field("surgery_location") surgeryLocation: String,
        @Field("surgery_period") surgeryPeriod: String,
        @Field("surgery_reason") surgeryReason: String,
        @Field("pvc") pvc: String,
        @Field("referral_source") referralSource: String,
        @Field("referred") referred: String,
        @Field("referred_reason") referredReason: String,
        @Field("respiratory_rate") respiratoryRate: String,
        @Field("great_vessel_relations_aortic") greatVesselRelationsAortic: String,
        @Field("great_vessel_relations_coronaries") greatVesselRelationsCoronaries: String,
        @Field("great_vessel_relations_measurements") greatVesselRelationsMeasurements: String,
        @Field("great_vessel_relations_state") greatVesselRelationsState: String,
        @Field("weight") weight: String,
        @Field("wbc") wbc: String,
        @Field("ca") ca: String,
        @Field("cl") cl: String,
        @Field("creatinine") creatinine: String,
        @Field("k") k: String,
        @Field("na") na: String,
        @Field("urea") urea: String,
        @Field("bmi") bmi: String,
        @Field("age_presentation") ageAtPresentation: String,
        @Field("secondary_phone") secondaryPhone: String,
        @Field("general_outcome") generalOutcome: String
    ): Response<PatientRecordsResponse>
}