package com.akshay.triviaapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.akshay.triviaapp.R
import com.akshay.triviaapp.ui.quiz.QuizActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        initViews()
        initObservers()
        initListener()
    }

    private fun initViews() {
    }

    private fun initObservers() {
    }

    private fun initListener() {
        bt_start_game.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if (!et_name.text.isNullOrEmpty()) {
            var intent = Intent()
            intent.putExtra("USER_NAME", et_name.text)
            startActivity(Intent(applicationContext, QuizActivity::class.java))

        } else {
            Toast.makeText(this, "Please add user name", Toast.LENGTH_LONG).show()
        }
    }


}