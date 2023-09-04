package com.example.myapplicationsqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplicationsqlite.databinding.ActivityMainBinding
import com.example.myapplicationsqlite.room.User
import com.example.myapplicationsqlite.room.UserDao
import com.example.myapplicationsqlite.room.UserDatabase

class MainActivity : AppCompatActivity(), UserClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var getAllUserList: List<User>
    private lateinit var userDao: UserDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.insertBtn.setOnClickListener {
            startActivity(Intent(this, CreateActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        val userAdapter = UserAdapter(this)
        binding.userRcv.adapter = userAdapter
        userDao = UserDatabase.getInstance(this)
            .getUserDao()
        getAllUserList = userDao.getAllUser()
        userAdapter.submitList(getAllUserList)
    }

    override fun userDelete(user: User) {
        Log.d("TAG", "userDelete: ${user.userId}")
        userDao.userDelete(user)
    }

    override fun userUpdate(user: User) {
        val intent = Intent(this, CreateActivity::class.java)
        intent.putExtra(CreateActivity.id_key, user.userId)
        intent.putExtra(CreateActivity.name_key, user.name)
        intent.putExtra(CreateActivity.age_key, user.age.toString())
        intent.putExtra(CreateActivity.mobile_key, user.mobile)
        startActivity(intent)
        Log.d("TAG", "userUpdate: ${user.userId}")
    }
}