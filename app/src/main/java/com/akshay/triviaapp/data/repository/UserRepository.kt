package com.akshay.triviaapp.data.repository

import com.akshay.triviaapp.data.local.UserDao
import com.akshay.triviaapp.data.local.model.UserDetails
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor( private val userDao: UserDao){

    suspend fun getUserHistory() = userDao.getAllChannels()

    suspend fun addUserDetails(userDetails: UserDetails) = userDao.insertChannel(userDetails)
}