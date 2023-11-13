package com.kot.tainex.data

import com.google.gson.annotations.SerializedName

data class activitys(
    @SerializedName("title") val title: String,
    @SerializedName("dateTime") val dateTime: String,
    @SerializedName("hall") val hall: List<String>,
    @SerializedName("content") val content: String,
)