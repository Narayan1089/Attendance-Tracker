package com.example.jforcetest.repository

import androidx.lifecycle.LiveData
import com.example.jforcetest.database.AttendanceDao
import com.example.jforcetest.models.Attendance

class AttendanceRepository(private val attendanceDao: AttendanceDao) {
    suspend fun insert(attendance: Attendance) {
        attendanceDao.insert(attendance)
    }

    suspend fun update(attendance: Attendance) {
        attendanceDao.update(attendance)
    }

    fun getAttendanceRecords(userId: Int): LiveData<List<Attendance>> {
        return attendanceDao.getAttendanceRecords(userId)
    }

    suspend fun getAttendanceForDate(userId: Int, date: String): Attendance? {
        return attendanceDao.getAttendanceForDate(userId, date)
    }
}
