package com.dicoding.motour.data.model.landmark.detail


import com.google.gson.annotations.SerializedName

data class LandmarkDetailList(
    @SerializedName("landmark")
    val landmark: LandmarkDetail?
)