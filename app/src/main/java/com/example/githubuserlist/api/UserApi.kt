package com.example.githubuserlist.api

import com.example.githubuserlist.models.Users
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    @GET("/users")
    suspend fun fetchUsers(): Response<Users>
}