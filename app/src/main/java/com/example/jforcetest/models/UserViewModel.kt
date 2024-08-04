package com.example.jforcetest.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.jforcetest.database.AppDatabase
import com.example.jforcetest.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository
    val allUsers: LiveData<List<User>>

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)

        allUsers = repository.getAllUsers()
    }


    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }

    suspend fun getUser(username: String, password: String): User? {
        return repository.getUser(username, password)
    }

    suspend fun getUserByUsername(username: String): User? {
        return repository.getUserByUsername(username)
    }

}
