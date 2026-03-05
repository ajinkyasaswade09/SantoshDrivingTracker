package com.ajinkya.santoshdrivingtracker.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DrivingDao {
    @Query("SELECT * FROM driving_entries ORDER BY id DESC")
    fun getAllEntries(): Flow<List<DrivingEntry>>

    @Insert
    suspend fun insertEntry(entry: DrivingEntry)

    @Delete
    suspend fun deleteEntry(entry: DrivingEntry)
}