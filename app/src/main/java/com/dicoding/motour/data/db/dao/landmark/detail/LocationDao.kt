package com.dicoding.motour.data.db.dao.landmark.detail

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.motour.data.model.landmark.detail.Location

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(location: Location)

    @Query("DELETE FROM location WHERE address = :address")
    suspend fun deleteAllLocation(address: String)

    @Query("SELECT * FROM location WHERE address = :address")
    suspend fun getLocationById(address: String): Location
}
