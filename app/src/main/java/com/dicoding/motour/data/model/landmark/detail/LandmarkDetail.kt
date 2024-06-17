package com.dicoding.motour.data.model.landmark.detail


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "landmark_detail")
data class LandmarkDetail(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("history")
    val history: String?,

    @SerializedName("location")
    @Embedded val location: Location?,

    @SerializedName("photos")
    val photos: List<String?>,

    @SerializedName("unique_activities")
    val uniqueActivities: List<String?>,

    @SerializedName("opening_hours")
    @Embedded val openingHours: OpeningHours?,

    @SerializedName("ticket_prices")
    @Embedded val ticketPrice: TicketPrice?,

    @SerializedName("contact_info")
    @Embedded val contactInfo: ContactInfo?,
)

data class LandmarkDetailData(
    @SerializedName("landmark")
    val landmark: LandmarkDetail
)