package com.example.jforcetest.repository

import androidx.lifecycle.LiveData
import com.example.jforcetest.models.User
import com.example.jforcetest.database.UserDao

class UserRepository(private val userDao: UserDao) {
    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun getUser(username: String, password: String): User? {
        return userDao.getUser(username, password)
    }

    suspend fun getUserByUsername(username: String): User? {
        return userDao.getUserByUsername(username)
    }
    fun getAllUsers(): LiveData<List<User>> {
        return userDao.getAllUsers()
    }
}
