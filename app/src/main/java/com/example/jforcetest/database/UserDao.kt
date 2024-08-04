package com.example.jforcetest.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.jforcetest.models.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    suspend fun getUser(username: String, password: String): User?

    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getUserByUsername(username: String): User?

    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<User>>
}
