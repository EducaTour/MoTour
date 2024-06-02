package com.dicoding.motour.data.model.landmark.detail


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "contact_info")
data class ContactInfo(
    @SerializedName("email")
    val email: String?,
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("website")
    val website: String?
)