package com.dicoding.motour.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.motour.data.db.dao.landmark.detail.ContactInfoDao
import com.dicoding.motour.data.db.dao.landmark.detail.LandmarkDetailDao
import com.dicoding.motour.data.db.dao.landmark.detail.LocationDao
import com.dicoding.motour.data.db.dao.landmark.detail.OpeningHoursDao
import com.dicoding.motour.data.db.dao.landmark.detail.TicketPriceDao
import com.dicoding.motour.data.model.landmark.detail.ContactInfo
import com.dicoding.motour.data.model.landmark.detail.LandmarkDetail
import com.dicoding.motour.data.model.landmark.detail.Location
import com.dicoding.motour.data.model.landmark.detail.OpeningHours
import com.dicoding.motour.data.model.landmark.detail.TicketPrice

@Database(
    entities = [
        ContactInfo::class,
        LandmarkDetail::class,
        Location::class,
        OpeningHours::class,
        TicketPrice::class
    ],
    version = 1,
    exportSchema = false
)
abstract class EducaTourDB : RoomDatabase(){
    abstract fun landmarkDao(): LandmarkDetailDao
    abstract fun landmarkDetailDao(): LandmarkDetailDao
    abstract fun locationDao(): LocationDao
    abstract fun contactInfoDao(): ContactInfoDao
    abstract fun openingHoursDao(): OpeningHoursDao
    abstract fun ticketPriceDao(): TicketPriceDao
}