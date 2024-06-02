package com.dicoding.motour.data.model.landmark.detail


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ticket_price")
data class TicketPrice(
    @SerializedName("adult")
    val adult: String?,
    @SerializedName("child")
    val child: String?,
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("senior")
    val senior: String?,
    @SerializedName("student")
    val student: String?
)