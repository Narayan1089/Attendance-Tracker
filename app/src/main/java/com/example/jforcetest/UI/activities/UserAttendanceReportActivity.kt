package com.example.jforcetest.UI.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jforcetest.UI.activities.adapters.AttendanceAdapter
import com.example.jforcetest.databinding.ActivityUserAttendanceReportBinding
import com.example.jforcetest.models.AttendanceViewModel
import androidx.activity.viewModels
import androidx.lifecycle.Observer


class UserAttendanceReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserAttendanceReportBinding
    private val attendanceViewModel: AttendanceViewModel by viewModels()
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserAttendanceReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userId = intent.getIntExtra("USER_ID", -1)

        val adapter = AttendanceAdapter()
        binding.rvAttendanceRecords.layoutManager = LinearLayoutManager(this)
        binding.rvAttendanceRecords.adapter = adapter

        attendanceViewModel.getAttendanceRecords(userId).observe(this) { records ->
            records?.let { adapter.submitList(it) }
        }
    }
}



//class UserAttendanceReportActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_user_attendance_report)
//
//        val recyclerView: RecyclerView = findViewById(R.id.rvAttendanceRecords)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        val itemList = mutableListOf<Attendance>()
//        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
//        val currentDate = dateFormat.format(Date())
//
//        for (i in 1..20) {
//            itemList.add(Attendance(currentDate, "01-01-2024", "02-01-2024"))
//        }
//
//        val adapter = AttendanceRecordsAdapter(itemList)
//        recyclerView.adapter = adapter
//
//    }
//}