package com.example.githubuserlist.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        private val BASE_URL = "https://api.github.com"

        private val retrofitInstance by lazy {

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val retrofit by lazy {
            retrofitInstance.create(UserApi::class.java)
        }
    }
}