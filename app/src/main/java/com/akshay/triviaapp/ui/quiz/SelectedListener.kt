package com.akshay.triviaapp.ui.quiz

interface SelectedListener {

    fun addSelectedItem(item:Any,isSelected:Boolean)
    fun isSelectedItem(item:Any,isSelected:Boolean):Boolean
}