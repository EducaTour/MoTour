package com.dicoding.motour.data.db.dao.landmark.detail

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.motour.data.model.landmark.detail.ContactInfo

@Dao
interface ContactInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contactInfo: ContactInfo)

    @Query("DELETE FROM contact_info WHERE email = :email")
    suspend fun deleteAllContactInfo(email: String)

    @Query("SELECT * FROM contact_info WHERE email = :email")
    suspend fun getContactInfoById(email: String): ContactInfo
}
