package com.xtrem.peads_cardiac.data.auth

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("email")
    @Expose
    var email: String? = null,
    @SerializedName("phone")
    @Expose
    var phone: String? = null
)