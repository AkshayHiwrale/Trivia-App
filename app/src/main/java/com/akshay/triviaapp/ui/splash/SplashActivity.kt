package com.akshay.triviaapp.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.akshay.triviaapp.R
import com.akshay.triviaapp.ui.home.MainActivity
import com.akshay.triviaapp.ui.quiz.QuizActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        init()
    }

    private fun init() {
        initViews()
        initObservers()
    }

    private fun initViews() {
        splashViewModel.initSplashScreen()
    }

    private fun initObservers() {
        splashViewModel.liveData.observe(this, Observer {
            startActivity(Intent(applicationContext, QuizActivity::class.java))
        })

    }
}