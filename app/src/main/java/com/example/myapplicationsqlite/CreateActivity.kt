package com.example.myapplicationsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplicationsqlite.databinding.ActivityCreateBinding
import com.example.myapplicationsqlite.room.User
import com.example.myapplicationsqlite.room.UserDao
import com.example.myapplicationsqlite.room.UserDatabase

class CreateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateBinding
    private lateinit var userDao: UserDao
    private lateinit var user: User
    private var userId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userDao = UserDatabase.getInstance(this)
            .getUserDao()
        if (intent.hasExtra(id_key)) {
            binding.submitBtn.text = update
            userId = intent.getIntExtra(id_key, 0)
            binding.name.setText(intent.getStringExtra(name_key))
            binding.age.setText(intent.getStringExtra(age_key))
            binding.mobile.setText(intent.getStringExtra(mobile_key))
        }
        binding.submitBtn.setOnClickListener {
            val name = binding.name.text.toString()
            val age = binding.age.text.toString()
            val mobile = binding.mobile.text.toString()
            if (binding.submitBtn.text.toString() == update) {
                user = User(userId, name, age.toInt(), mobile)
                userDao.userUpdate(user)
                Toast.makeText(this,"Update", Toast.LENGTH_LONG).show()
            } else {
                user = User(0, name, age.toInt(), mobile)
                userDao.userInsert(user)
                Toast.makeText(this,"Insert", Toast.LENGTH_LONG).show()
            }
        }
    }
    companion object {
        const val id_key = "id"
        const val name_key = "nane"
        const val age_key = "age"
        const val mobile_key = "mobile"
        const val update = "update"
    }
}