package com.example.myapplicationsqlite.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    companion object {
        var dp: UserDatabase? = null
        fun getInstance(context: Context): UserDatabase {
            if (dp == null) {
                dp = Room.databaseBuilder(
                    context,
                    UserDatabase::class.java,
                    "user_dp"
                ).allowMainThreadQueries().build()
                return dp as UserDatabase
            } else {
                return dp as UserDatabase
            }
        }
    }
}