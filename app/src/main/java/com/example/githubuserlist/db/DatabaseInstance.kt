package com.example.githubuserlist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubuserlist.models.UsersItem


@Database(entities = [UsersItem::class], version = 1, exportSchema = false)
abstract class DatabaseInstance: RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseInstance? = null
        fun getInstance(context: Context): DatabaseInstance {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseInstance::class.java,
                        "users"
                    ).build()
                }
                return instance
            }
        }
    }
}