package com.xtrem.peads_cardiac.data.records


import com.google.gson.annotations.SerializedName

data class Record(
    @SerializedName("admission_date")
    val admissionDate: Any,
    @SerializedName("admission_duration")
    val admissionDuration: Any,
    @SerializedName("admission_outcome")
    val admissionOutcome: Any,
    @SerializedName("admission_reason")
    val admissionReason: Any,
    @SerializedName("admission_status")
    val admissionStatus: String,
    @SerializedName("admission_treatment")
    val admissionTreatment: Any,
    @SerializedName("age")
    val age: Any,
    @SerializedName("awaiting_surgery")
    val awaitingSurgery: String,
    @SerializedName("blood_pressure")
    val bloodPressure: String,
    @SerializedName("cardiac_eco")
    val cardiacEco: List<CardiacEco>,
    @SerializedName("chest_x_ray")
    val chestXRay: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("death_reason")
    val deathReason: Any,
    @SerializedName("diagnosis_details")
    val diagnosisDetails: String,
    @SerializedName("dob")
    val dob: String,
    @SerializedName("fbc")
    val fbc: List<Fbc>,
    @SerializedName("head_circumference")
    val headCircumference: String,
    @SerializedName("heart_rate")
    val heartRate: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("location")
    val location: String,
    @SerializedName("medication_details")
    val medicationDetails: String,
    @SerializedName("muac")
    val muac: String,
    @SerializedName("bmi")
    val bmi: String,
    @SerializedName("age_presentation")
    val agePresentation: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("next_visit_date")
    val nextVisitDate: String,
    @SerializedName("patient_id")
    val patientId: Int,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("secondary_phone")
    val secondaryPhone: String,
    @SerializedName("previous_cardiac_surgery")
    val previousCardiacSurgery: String,
    @SerializedName("general_outcome")
    val generalOutcome: String,
    @SerializedName("pulse_rate")
    val pulseRate: String,
    @SerializedName("pvc")
    val pvc: String,
    @SerializedName("referral_source")
    val referralSource: String,
    @SerializedName("referred")
    val referred: String,
    @SerializedName("referred_reason")
    val referredReason: String,
    @SerializedName("registered_by")
    val registeredBy: String,
    @SerializedName("respiratory_rate")
    val respiratoryRate: String,
    @SerializedName("sex")
    val sex: String,
    @SerializedName("sponsor")
    val sponsor: String,
    @SerializedName("surgery")
    val surgery: String,
    @SerializedName("surgery_date")
    val surgeryDate: Any,
    @SerializedName("surgery_location")
    val surgeryLocation: Any,
    @SerializedName("surgery_period")
    val surgeryPeriod: Any,
    @SerializedName("surgery_reason")
    val surgeryReason: Any,
    @SerializedName("uandes")
    val uandes: List<Uande>,
    @SerializedName("visit_number")
    val visitNumber: Int,
    @SerializedName("visit_reason")
    val visitReason: String,
    @SerializedName("weight")
    val weight: Int
)