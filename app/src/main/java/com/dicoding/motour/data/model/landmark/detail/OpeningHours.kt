package com.dicoding.motour.data.model.landmark.detail


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "opening_hours")
data class OpeningHours(
    @SerializedName("Friday")
    val friday: String?,
    @SerializedName("Monday")
    val monday: String?,
    @SerializedName("Saturday")
    val saturday: String?,
    @PrimaryKey
    @SerializedName("Sunday")
    val sunday: String,
    @SerializedName("Thursday")
    val thursday: String?,
    @SerializedName("Tuesday")
    val tuesday: String?,
    @SerializedName("Wednesday")
    val wednesday: String?
)