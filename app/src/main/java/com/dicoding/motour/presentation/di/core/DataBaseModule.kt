package com.dicoding.motour.presentation.di.core

import android.content.Context
import androidx.room.Room
import com.dicoding.motour.data.db.EducaTourDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Singleton
    @Provides
    fun provideEducaTourDB(context: Context): EducaTourDB {
        return Room.databaseBuilder(context, EducaTourDB::class.java, "educaTour")
            .build()
    }

    @Singleton
    @Provides
    fun provideContactInfoDao(educaTourDB: EducaTourDB) = educaTourDB.contactInfoDao()

    /*
    @Singleton
    @Provides
    fun provideLandmarkDetailDao(educaTourDB: EducaTourDB) = educaTourDB.landmarkDetailDao()
    */
    @Singleton
    @Provides
    fun provideLocationDao(educaTourDB: EducaTourDB) = educaTourDB.locationDao()

    @Singleton
    @Provides
    fun provideOpeningHoursDao(educaTourDB: EducaTourDB) = educaTourDB.openingHoursDao()

    @Singleton
    @Provides
    fun provideTicketPriceDao(educaTourDB: EducaTourDB) = educaTourDB.ticketPriceDao()

    @Singleton
    @Provides
    fun provideLandmarkDao(educaTourDB: EducaTourDB) = educaTourDB.landmarkDao()
}