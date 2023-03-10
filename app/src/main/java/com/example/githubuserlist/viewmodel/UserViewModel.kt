package com.example.githubuserlist.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuserlist.models.Users
import com.example.githubuserlist.models.UsersItem
import com.example.githubuserlist.repository.UserRepository
import com.example.githubuserlist.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel(val repository: UserRepository, val context: Context): ViewModel() {
    val usersLiveData: MutableLiveData<Resource<Users>> = MutableLiveData()
    var usersFromDB = repository.getUsersFromDB()

    init {
        //get data from REST API when Internet is connected and finally store it in SQLite
        if(isConnectionAvailable(context)) {
            getUser()
        }
        //get data from local DB if no internet is available
        else {
            getUsersFromDB()
        }
    }

    private fun getUsersFromDB() {
        usersFromDB = repository.getUsersFromDB()
    }


    private fun getUser() {
        viewModelScope.launch {
            usersLiveData.postValue(Resource.Loading())
            val response = repository.getUsers()
            usersLiveData.postValue(handleUserResponse(response))
        }

    }

    private suspend fun handleUserResponse(response: Response<Users>): Resource<Users> {
        if (response.isSuccessful) {
            response.body()?.let { responseResult ->
                responseResult.forEach {
                    repository.insertUser(it)
                }
                return Resource.Success(responseResult)
            }
        }
        return Resource.Error(response.message())
    }

    fun isConnectionAvailable(context: Context): Boolean {
        val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo()!!.isConnected
    }
}