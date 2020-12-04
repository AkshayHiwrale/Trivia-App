package com.akshay.triviaapp.ui.summary

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akshay.triviaapp.data.local.model.UserDetails
import com.akshay.triviaapp.data.repository.UserRepository
import com.akshay.triviaapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SummaryViewModel  @ViewModelInject constructor(
    private val userPreferences: UserRepository
) : ViewModel() {
    var questionTwoList: MutableLiveData<Resource<MutableList<UserDetails>>> = MutableLiveData()

    fun getUserHistory(){
        viewModelScope.launch(Dispatchers.IO) {
            var userHistory = userPreferences.getUserHistory()
            if (!userHistory.isNullOrEmpty()) {
                questionTwoList.postValue(Resource.success(userHistory.toMutableList()))
            } else {
                questionTwoList.postValue(Resource.error())

            }

        }
    }

}