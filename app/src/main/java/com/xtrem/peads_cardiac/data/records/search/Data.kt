package com.xtrem.peads_cardiac.data.records.search


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("age")
    val age: Any,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("dob")
    val dob: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("location")
    val location: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("sex")
    val sex: String,
    @SerializedName("updated_at")
    val updatedAt: String
)