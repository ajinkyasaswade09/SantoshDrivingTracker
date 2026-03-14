package com.ajinkya.santoshdrivingtracker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ajinkya.santoshdrivingtracker.data.DrivingDao
import com.ajinkya.santoshdrivingtracker.data.DrivingEntry
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DrivingViewModel(private val drivingDao: DrivingDao) : ViewModel() {
    val allEntries: StateFlow<List<DrivingEntry>> = drivingDao.getAllEntries()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addEntry(name: String, phoneNumber: String, date: String, time: String, rideCount: Int, km: Double, amount: Double) {
        viewModelScope.launch {
            drivingDao.insertEntry(
                DrivingEntry(
                    name = name,
                    phoneNumber = phoneNumber,
                    date = date,
                    time = time,
                    rideCount = rideCount,
                    km = km,
                    amount = amount
                )
            )
        }
    }

    fun deleteEntry(entry: DrivingEntry) {
        viewModelScope.launch {
            drivingDao.deleteEntry(entry)
        }
    }
}

class DrivingViewModelFactory(private val drivingDao: DrivingDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DrivingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DrivingViewModel(drivingDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}