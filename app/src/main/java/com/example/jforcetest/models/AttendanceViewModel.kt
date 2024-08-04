package com.example.jforcetest.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.jforcetest.database.AppDatabase
import com.example.jforcetest.repository.AttendanceRepository
import kotlinx.coroutines.launch

class AttendanceViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AttendanceRepository

    init {
        val attendanceDao = AppDatabase.getDatabase(application).attendanceDao()
        repository = AttendanceRepository(attendanceDao)
    }

    fun insert(attendance: Attendance) = viewModelScope.launch {
        repository.insert(attendance)
    }

    fun update(attendance: Attendance) = viewModelScope.launch {
        repository.update(attendance)
    }

    fun getAttendanceRecords(userId: Int): LiveData<List<Attendance>> {
        return repository.getAttendanceRecords(userId)
    }

    suspend fun getAttendanceForDate(userId: Int, date: String): Attendance? {
        return repository.getAttendanceForDate(userId, date)
    }
}
