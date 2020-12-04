package com.akshay.triviaapp.ui.summary

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshay.triviaapp.R
import com.akshay.triviaapp.data.local.model.UserDetails
import com.akshay.triviaapp.ui.quiz.QuizViewModel
import com.akshay.triviaapp.ui.quiz.WordListAdapter
import com.akshay.triviaapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.question_list_frag.*
import kotlinx.android.synthetic.main.summary_layout.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HistoryActivity : AppCompatActivity() {
    val summaryViewModel: SummaryViewModel by viewModels()
    lateinit var adapter: HistoryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.summary_layout)
        init()
    }

    private fun init() {
        summaryViewModel.getUserHistory()
        initViews()
        initObservers()
        initListener()
    }

    private fun initViews() {

         adapter = HistoryListAdapter(this)
        rv_answer_list.adapter = adapter
        rv_answer_list.layoutManager = LinearLayoutManager(this)
    }

    private fun initListener() {
    }

    private fun initObservers() {
        summaryViewModel.questionTwoList.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { it1 ->
                        adapter.setWords(it1)
                    }
                }
                Status.ERROR -> {

                }
            }
        }
        )
    }


}