package com.example.jforcetest.UI.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jforcetest.databinding.ActivityHomePageAttendanceBinding


import androidx.activity.viewModels
import com.example.jforcetest.models.Attendance
import com.example.jforcetest.models.AttendanceViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HomeAttendanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageAttendanceBinding
    private val attendanceViewModel: AttendanceViewModel by viewModels()
    private var userId: Int = -1
    private var login = "login"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageAttendanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userId = intent.getIntExtra("USER_ID", -1)
        login = intent.getIntExtra("type", 1).toString()

        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val currentTime = timeFormat.format(Calendar.getInstance().time)

        binding.tvCurrentDate.text = "Current Date: $currentDate"
        binding.tvCurrentTime.text = "Current Time: $currentTime"

        binding.btnSignInOrSignOut.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                attendanceViewModel.getAttendanceForDate(userId, currentDate)?.let { attendance ->
                    if (attendance.signInTime == null) {
                        var signInTime =
                            SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
                        val attendance = Attendance(
                            userId = userId,
                            date = currentDate,
                            signInTime = signInTime,
                            signOutTime = null
                        )
                        attendanceViewModel.insert(attendance)
//                        binding.btnSignInOrSignOut.setOnClickListener {
                            attendance.signInTime = signInTime
                            attendanceViewModel.update(attendance)
//                            if(login == "login") {
//                            login == "login"
                            binding.btnSignInOrSignOut.text = "Sign Out"
//                        } else {
//                            binding.btnSignInOrSignOut.text = "Sign In"
//                            }
//                            finish()
//                        }
                    } else {
//                        binding.btnSignInOrSignOut.setOnClickListener {
                            val signOutTime =
                                SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
                            attendance.signOutTime = signOutTime
                            val attendance = Attendance(
                                userId = userId,
                                date = currentDate,
                                signInTime = null,
                                signOutTime = null
                            )
                            attendanceViewModel.update(attendance)
//                            login == "sign out"
                            binding.btnSignInOrSignOut.text = "Sign In"
//                            if (login == "login") {
//                                binding.btnSignInOrSignOut.text = "Sign Out"
//                            } else {
//                                binding.btnSignInOrSignOut.text = "Sign In"
//                            }
//                            finish()
//                        }
                    }
                }
//                    ?: run {
//                    val signInTime =
//                        SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
//                    val attendance = Attendance(
//                        userId = userId,
//                        date = currentDate,
//                        signInTime = signInTime,
//                        signOutTime = null
//                    )
//                    attendanceViewModel.insert(attendance)
//                    binding.btnSignInOrSignOut.setOnClickListener {
//                        val signOutTime =
//                            SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
//                        attendance.signOutTime = signOutTime
//                        attendanceViewModel.update(attendance)
//                        login == "login"
//                        binding.btnSignInOrSignOut.text = "Sign In"
////                        if (login == "login") {
////                            binding.btnSignInOrSignOut.text = "Sign Out"
////                        } else {
////                            binding.btnSignInOrSignOut.text = "Sign In"
////                        }
////                        finish()
//                    }
//                }
            }
        }

        binding.btnViewReport.setOnClickListener {
            val intent = Intent(this, UserAttendanceReportActivity::class.java).apply {
                putExtra("USER_ID", userId)
            }
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
    }
}



//class HomeAttendanceActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityHomePageAttendanceBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityHomePageAttendanceBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.btnViewReport.setOnClickListener {
//            val intent = Intent(this, UserAttendanceReportActivity::class.java)
//            intent.putExtra("KEY_STRING", "Login")
//            startActivity(intent)
//        }
//    }
//}