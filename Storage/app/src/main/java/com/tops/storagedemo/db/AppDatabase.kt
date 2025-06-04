package com.tops.storagedemo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tops.storagedemo.dao.UserDao
import com.tops.storagedemo.entities.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

}