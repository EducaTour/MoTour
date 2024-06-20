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

val LANDMARKS_KEY = hashMapOf(
    "benteng_vredeburg" to 3,
    "candi_borobudur" to 4,
    "candi_prambanan" to 7,
    "garuda_wisnu_kencana" to 1,
    "gedung_sate" to 24,
    "istana_maimun" to 19,
    "jam_gadang" to 11,
    "keong_mas" to 9,
    "keraton_jogja" to 25,
    "kota_tua" to 6,
    "lawang_sewu" to 8,
    "masjid_istiqlal" to 15,
    "masjid_menara_kudus" to 23,
    "masjid_raya_baiturrahman" to 14,
    "menara_siger_lampung" to 18,
    "monas" to 5,
    "monumen_bandung_lautan_api" to 10,
    "monumen_gong_perdamaian" to 22,
    "monumen_nol_km" to 16,
    "monumen_simpang_lima_gumul" to 2,
    "patung_ikan_surabaya" to 12,
    "patung_yesus_memberkati" to 20,
    "tugu_jogja" to 13,
    "tugu_khatulistiwa" to 17,
    "tugu_pahlawan_surabaya" to 21
)