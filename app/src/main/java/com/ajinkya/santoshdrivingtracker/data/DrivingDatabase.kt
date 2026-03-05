package com.ajinkya.santoshdrivingtracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DrivingEntry::class], version = 1, exportSchema = false)
abstract class DrivingDatabase : RoomDatabase() {
    abstract fun drivingDao(): DrivingDao

    companion object {
        @Volatile
        private var Instance: DrivingDatabase? = null

        fun getDatabase(context: Context): DrivingDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, DrivingDatabase::class.java, "driving_db")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}