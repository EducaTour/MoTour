package com.dicoding.motour.data.db.dao.landmark.detail
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.motour.data.model.landmark.detail.LandmarkDetail

@Dao
interface LandmarkDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(landmarkDetail: LandmarkDetail)

    @Query("DELETE FROM landmark WHERE id = :id")
    suspend fun deleteAllLandmarkDetail(id: Int)

    @Query("SELECT * FROM landmark WHERE id = :id")
    suspend fun getLandmarkById(id: Int): LandmarkDetail
}
