package com.ajinkya.santoshdrivingtracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "driving_entries")
data class DrivingEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val phoneNumber: String = "",
    val date: String,
    val time: String,
    val rideCount: Int,
    val km: Double,
    val amount: Double = 0.0
)