package com.xtrem.peads_cardiac.data.records.stats

import com.google.gson.annotations.SerializedName
import com.xtrem.peads_cardiac.data.records.search.Result

data class StatsResponse(
    @SerializedName("data")
    val `data`: StatsData,
    @SerializedName("result")
    val result: Result
)