package com.example.myapplicationsqlite

import com.example.myapplicationsqlite.room.User

interface UserClickListener {
    fun userDelete(user: User)
    fun userUpdate(user: User)
}