package com.xtrem.peads_cardiac.data.records.stats

import com.google.gson.annotations.SerializedName

data class StatsData(
    @SerializedName("users")
    val users: String,
    @SerializedName("patients")
    val patients: String,
    @SerializedName("admitted")
    val admitted: String,
    @SerializedName("dead")
    val dead: String
)