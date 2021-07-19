package com.xtrem.peads_cardiac.data.auth


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("code")
    val code: Int,
    @SerializedName("error")
    val error: Any,
    @SerializedName("error_bag")
    val errorBag: List<Any>,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)