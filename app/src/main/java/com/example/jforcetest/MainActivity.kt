package com.example.jforcetest

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.jforcetest.UI.activities.AdminActivity
import com.example.jforcetest.UI.activities.HomeAttendanceActivity
import com.example.jforcetest.models.User
import com.example.jforcetest.databinding.ActivityMainBinding
import com.example.jforcetest.models.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val userViewModel: UserViewModel by viewModels()
    private var currentPage = "login"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            binding.groupHideShowRegister.visibility = View.GONE
            binding.tvCurrentPageName.text = "Login Page"
            currentPage = "login"
            Toast.makeText(this@MainActivity, "You are in Login Page", Toast.LENGTH_SHORT).show()
        }

        binding.btnRegister.setOnClickListener {
            binding.groupHideShowRegister.visibility = View.VISIBLE
            binding.tvCurrentPageName.text = "Register Page"
            currentPage = "register"
            Toast.makeText(this@MainActivity, "You are in Registration Page", Toast.LENGTH_SHORT).show()
        }

        binding.btnSubmit.setOnClickListener {
            when (currentPage) {
                "login" -> {
                    if (binding.username.text.toString() == "admin" && binding.password.text.toString() == "admin") {
                        val intent = Intent(this, AdminActivity::class.java)
                        startActivity(intent)
                    } else {
                        CoroutineScope(Dispatchers.Main).launch {
                            userViewModel.getUser(binding.username.text.toString(), binding.password.text.toString())?.let { user ->
                                val intent = Intent(this@MainActivity, HomeAttendanceActivity::class.java).apply {
                                    putExtra("USER_ID", user.id).putExtra("type", "login")
                                }
                                Toast.makeText(this@MainActivity, "Logged In Successfully", Toast.LENGTH_SHORT).show()
                                startActivity(intent)
                            } ?: run {
                                Toast.makeText(this@MainActivity, "Invalid credentials", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
                else -> {
                    if (binding.username.text.toString().isNotEmpty() && binding.password.text.toString().isNotEmpty() && binding.etEmail.text.toString().isNotEmpty() && binding.etPhoneNo.text.toString().isNotEmpty()) {
                        val user = User(username = binding.username.text.toString(), password = binding.password.text.toString(), email = binding.etEmail.text.toString(), phone = binding.etPhoneNo.text.toString())
                        userViewModel.insert(user)
                        Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@MainActivity, HomeAttendanceActivity::class.java).apply {
                            putExtra("USER_ID", user.id)
                        }
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

}