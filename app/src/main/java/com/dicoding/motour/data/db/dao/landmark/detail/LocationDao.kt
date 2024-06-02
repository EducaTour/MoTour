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

    @Query("DELETE FROM location WHERE id = :id")
    suspend fun deleteAllLocation(id: Int)

    @Query("SELECT * FROM location WHERE id = :id")
    suspend fun getLocationById(id: Int): Location
}
