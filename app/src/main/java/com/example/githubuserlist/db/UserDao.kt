package com.example.githubuserlist.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubuserlist.models.UsersItem

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUsers(item: UsersItem)

    @Query("SELECT * FROM users")
   fun getUsers(): LiveData<List<UsersItem>>

   @Query("DELETE FROM users")
   suspend fun deleteUsers()
}