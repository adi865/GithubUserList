package com.example.githubuserlist.repository

import androidx.room.withTransaction
import com.example.githubuserlist.api.RetrofitInstance
import com.example.githubuserlist.db.DatabaseInstance
import com.example.githubuserlist.models.UsersItem

class UserRepository(private val db: DatabaseInstance) {
    private val userDao = db.getUserDao()
    suspend fun getUsers() = RetrofitInstance.retrofit.fetchUsers()


    suspend fun insertUser(item: UsersItem) = userDao.addUsers(item)

    fun getUsersFromDB() = userDao.getUsers()


    suspend fun savedDataFromApiToDB(item: UsersItem)  {
        db.withTransaction {
            userDao.addUsers(item)
        }
    }
}