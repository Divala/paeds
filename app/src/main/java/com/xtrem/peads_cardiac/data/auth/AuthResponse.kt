package com.xtrem.peads_cardiac.data.auth

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("result")
    @Expose
    val result: Result? = null,
    @SerializedName("data")
    @Expose
    var data: AuthData? = null

)