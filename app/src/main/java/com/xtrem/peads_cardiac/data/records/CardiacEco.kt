package com.xtrem.peads_cardiac.data.records


import com.google.gson.annotations.SerializedName

data class CardiacEco(
    @SerializedName("cardiac_measurements")
    val cardiacMeasurements: String,
    @SerializedName("cardiac_other")
    val cardiacOther: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("great_vessel_relations_aortic")
    val greatVesselRelationsAortic: String,
    @SerializedName("great_vessel_relations_coronaries")
    val greatVesselRelationsCoronaries: String,
    @SerializedName("great_vessel_relations_measurements")
    val greatVesselRelationsMeasurements: String,
    @SerializedName("great_vessel_relations_state")
    val greatVesselRelationsState: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("patient_id")
    val patientId: Int,
    @SerializedName("pericardial_effusion")
    val pericardialEffusion: String,
    @SerializedName("registered_by")
    val registeredBy: String,
    @SerializedName("situs")
    val situs: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("valves_morphology")
    val valvesMorphology: String,
    @SerializedName("venous_return")
    val venousReturn: String,
    @SerializedName("visit_number")
    val visitNumber: Int
)