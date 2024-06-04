package com.dicoding.motour.data.model.landmark.detail


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dicoding.motour.data.db.StringListConverter
import com.dicoding.motour.data.model.StringList
import com.google.gson.annotations.SerializedName

@Entity(tableName = "landmark_detail")
data class LandmarkDetail(
    @SerializedName("contact_info")
    @Embedded val contactInfo: ContactInfo?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("history")
    val history: String?,

    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @SerializedName("images")
    @TypeConverters(StringListConverter::class)
    val images: StringList?,

    @SerializedName("location")
    @Embedded val location: Location?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("opening_hours")
    @Embedded val openingHours: OpeningHours?,

    @SerializedName("ticket_price")
    @Embedded val ticketPrice: TicketPrice?,

    @SerializedName("unique_activities")
    @TypeConverters(StringListConverter::class)
    val uniqueActivities: StringList?
)