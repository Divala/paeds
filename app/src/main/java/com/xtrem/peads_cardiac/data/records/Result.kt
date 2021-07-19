package com.xtrem.peads_cardiac.data.records


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("code")
    val code: Int,
    @SerializedName("error")
    val error: Any,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)