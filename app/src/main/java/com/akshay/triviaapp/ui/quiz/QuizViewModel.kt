package com.akshay.triviaapp.ui.quiz

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akshay.triviaapp.data.local.model.UserDetails
import com.akshay.triviaapp.data.repository.UserRepository
import com.akshay.triviaapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Singleton
class QuizViewModel @ViewModelInject constructor(
    private val userPreferences: UserRepository

) : ViewModel(){

    var liveData: MutableLiveData<Resource<String>> = MutableLiveData()
    var userDetails: MutableLiveData<Resource<String>> = MutableLiveData()
    var questionTwoList: MutableLiveData<Resource<MutableList<String>>> = MutableLiveData()


     fun updateAnswer(string: String) {
        if (!string.isNullOrEmpty()) {
            liveData.value = Resource.success(string)
        }else{
            liveData.value = Resource.error(string)

        }
    }

    fun updateUserDetails(string: String) {
        if (!string.isNullOrEmpty()) {
            userDetails.value = Resource.success(string)
        } else {
            userDetails.value = Resource.error()

        }
    }

    fun updateSecondAnswer(list: MutableList<String>){
        if (!list.isNullOrEmpty()){
            questionTwoList.postValue(Resource.success(list))
        }else{
            questionTwoList.postValue(Resource.error())
        }

    }

    suspend fun addUserDetails(userDetails: UserDetails) {
       var job = viewModelScope.launch(Dispatchers.IO) {
            userPreferences.addUserDetails(userDetails)
        }
    }

}