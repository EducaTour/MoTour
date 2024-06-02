package com.dicoding.motour.data.model.landmark.detail


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "location")
data class Location(
    @SerializedName("address")
    val address: String?,
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("maps")
    val maps: String?
)