package com.dicoding.motour.data.model.landmark.list


import com.google.gson.annotations.SerializedName

data class LandmarkList(
    @SerializedName("landmarks")
    val landmarks: List<Landmark>
)