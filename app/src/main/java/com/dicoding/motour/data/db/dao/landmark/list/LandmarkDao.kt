package com.dicoding.motour.data.db.dao.landmark.list

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.motour.data.model.landmark.list.Landmark

@Dao
interface LandmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(landmark: List<Landmark>)
    @Query("DELETE FROM landmark")
    suspend fun deleteAllLandmark()

    @Query("SELECT * FROM landmark")
    suspend fun getAllLandmark(): List<Landmark>
}
