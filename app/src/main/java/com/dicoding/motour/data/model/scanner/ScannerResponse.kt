package com.dicoding.motour.data.model.scanner


import com.google.gson.annotations.SerializedName

data class ScannerResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)