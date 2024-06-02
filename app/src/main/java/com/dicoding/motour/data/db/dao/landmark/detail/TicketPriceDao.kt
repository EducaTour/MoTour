package com.dicoding.motour.data.db.dao.landmark.detail
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dicoding.motour.data.model.landmark.detail.TicketPrice

@Dao
interface TicketPriceDao {
    @Insert
    suspend fun insert(ticketPrice: TicketPrice)

    @Query("DELETE FROM ticket_price WHERE id = :id")
    suspend fun deleteAllTicketPrice(id: Int)

    @Query("SELECT * FROM ticket_price WHERE id = :id")
    suspend fun getTicketPriceById(id: Int): TicketPrice
}
