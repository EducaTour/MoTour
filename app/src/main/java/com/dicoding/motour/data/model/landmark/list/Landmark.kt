package com.dicoding.motour.data.model.landmark.list


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "landmark")
data class Landmark(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: String?,
    @SerializedName("name")
    val name: String?
)