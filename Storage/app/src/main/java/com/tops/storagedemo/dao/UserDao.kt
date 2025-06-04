package com.tops.storagedemo.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tops.storagedemo.entities.User

@Dao
interface UserDao {

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

    @Update
    fun update(user: User)

    @Query("SELECT * from user")
    fun getAll()

}