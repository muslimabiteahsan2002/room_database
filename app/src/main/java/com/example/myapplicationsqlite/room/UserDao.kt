package com.example.myapplicationsqlite.room

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    fun userInsert(user: User)
    @Update
    fun userUpdate(user: User)
    @Delete
    fun userDelete(user: User)
    @Query("SELECT * FROM User")
    fun getAllUser(): List<User>
}