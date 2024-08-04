package com.example.jforcetest.UI.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.jforcetest.UI.activities.adapters.UserAdapter
import com.example.jforcetest.databinding.ActivityAdminBinding
import com.example.jforcetest.models.UserViewModel


class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = UserAdapter { user ->
            val intent = Intent(this, UserAttendanceReportActivity::class.java).apply {
                putExtra("USER_ID", user.id)
            }
            startActivity(intent)
        }
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = adapter

        userViewModel.allUsers.observe(this, Observer { users ->
            users?.let { adapter.submitList(it) }
        })
    }
}



//class AdminActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_admin_report_page)
//        val recyclerView: RecyclerView = findViewById(R.id.rvUsers)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        // Sample list of users
//        val users = listOf(
//            User("Alice"),
//            User("Bob"),
//            User("Charlie"),
//            User("David"),
//            User("Eve")
//        )
//
//        val adapter = UsersAdapter(users)
//        recyclerView.adapter = adapter
//    }
//}
