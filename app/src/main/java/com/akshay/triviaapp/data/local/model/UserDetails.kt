package com.akshay.triviaapp.data.local.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
 class UserDetails(
   @PrimaryKey
    var userName: String = "",
    var questionOneAns: String? = null,
    var questionTwoList: List<String>? = null
): Parcelable