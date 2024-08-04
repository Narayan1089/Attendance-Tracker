package com.example.jforcetest.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.jforcetest.models.Attendance

@Dao
interface AttendanceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(attendance: Attendance)

    @Update
    suspend fun update(attendance: Attendance)

    @Query("SELECT * FROM attendance WHERE userId = :userId")
    fun getAttendanceRecords(userId: Int): LiveData<List<Attendance>>

    @Query("SELECT * FROM attendance WHERE userId = :userId AND date = :date")
    suspend fun getAttendanceForDate(userId: Int, date: String): Attendance?
}
