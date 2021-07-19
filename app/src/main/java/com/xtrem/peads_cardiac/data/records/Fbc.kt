package com.xtrem.peads_cardiac.data.records


import com.google.gson.annotations.SerializedName

data class Fbc(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("hb")
    val hb: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("mvc")
    val mvc: String,
    @SerializedName("patient_id")
    val patientId: Int,
    @SerializedName("plt")
    val plt: String,
    @SerializedName("registered_by")
    val registeredBy: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("visit_number")
    val visitNumber: Int,
    @SerializedName("wbc")
    val wbc: String
)