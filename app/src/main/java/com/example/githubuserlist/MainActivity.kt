package com.example.githubuserlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.githubuserlist.databinding.ActivityMainBinding
import com.example.githubuserlist.repository.UserRepository
import com.example.githubuserlist.viewmodel.UserViewModel
<<<<<<< HEAD
import com.example.githubuserlist.viewmodel.UserViewModelProvider
=======
>>>>>>> 1d5f8c1 (Enabled offline fetching from local SQLite DB)

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}