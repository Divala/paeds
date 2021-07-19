package com.xtrem.peads_cardiac.data.records.search


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("result")
    val result: Result
)