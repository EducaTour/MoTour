package com.dicoding.motour.data.model.scanner


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("landMarkId")
    val landMarkId: String,
    @SerializedName("rate")
    val rate: String,
    @SerializedName("result")
    val result: String,
    @SerializedName("image")
    val image: String
)