package com.dicoding.motour.data.db.dao.landmark.detail

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.motour.data.model.landmark.detail.OpeningHours

@Dao
interface OpeningHoursDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(openingHours: OpeningHours)

    @Query("DELETE FROM opening_hours WHERE sunday = :sunday")
    suspend fun deleteAllOpeningHours(sunday: String)

    @Query("SELECT * FROM opening_hours WHERE sunday = :sunday")
    suspend fun getOpeningHoursById(sunday: String): OpeningHours
}
