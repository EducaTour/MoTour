package com.dicoding.motour.data.model.landmark.detail


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "opening_hours")
data class OpeningHours(
    @SerializedName("friday")
    val friday: String?,
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("monday")
    val monday: String?,
    @SerializedName("saturday")
    val saturday: String?,
    @SerializedName("sunday")
    val sunday: String?,
    @SerializedName("thursday")
    val thursday: String?,
    @SerializedName("tuesday")
    val tuesday: String?,
    @SerializedName("wednesday")
    val wednesday: String?
)