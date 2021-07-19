package com.xtrem.peads_cardiac.data.records


import com.google.gson.annotations.SerializedName

data class Uande(
    @SerializedName("cl")
    val cl: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("creatinine")
    val creatinine: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("k")
    val k: String,
    @SerializedName("mg")
    val mg: String,
    @SerializedName("na")
    val na: String,
    @SerializedName("ca")
    val ca: String,
    @SerializedName("patient_id")
    val patientId: Int,
    @SerializedName("registered_by")
    val registeredBy: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("urea")
    val urea: String,
    @SerializedName("visit_number")
    val visitNumber: Int
)