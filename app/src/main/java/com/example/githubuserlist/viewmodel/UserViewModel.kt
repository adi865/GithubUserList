package com.example.githubuserlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuserlist.models.Users
import com.example.githubuserlist.models.UsersItem
import com.example.githubuserlist.repository.UserRepository
import com.example.githubuserlist.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel(val repository: UserRepository): ViewModel() {
    val usersLiveData: MutableLiveData<Resource<Users>> = MutableLiveData()
    var usersFromDB = repository.getUsersFromDB()

    init { //commented out, because altho, the function gets data from Retrofit and saves it into Room.
////            still, it requires the internet connection to be on for fetching the new data everytime app is run


        getUser()
        getUsersFromDB()
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
}