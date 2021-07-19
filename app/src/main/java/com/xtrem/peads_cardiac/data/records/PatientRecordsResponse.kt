package com.xtrem.peads_cardiac.data.records


import com.google.gson.annotations.SerializedName

data class PatientRecordsResponse(
    @SerializedName("data")
    val records: List<Record>,
    @SerializedName("result")
    val result: Result
)