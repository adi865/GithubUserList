package com.example.githubuserlist.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubuserlist.repository.UserRepository

class UserViewModelProvider(val repository: UserRepository, val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(repository, context) as T
    }
}