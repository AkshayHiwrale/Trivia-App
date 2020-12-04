package com.akshay.triviaapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akshay.triviaapp.data.local.model.UserDetails

@Dao
interface UserDao {

    @Query("SELECT * FROM userdetails")
    fun getAllChannels(): List<UserDetails>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChannel(channel: UserDetails)
}
