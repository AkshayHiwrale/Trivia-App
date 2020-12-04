package com.akshay.triviaapp.ui.summary

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshay.triviaapp.R
import com.akshay.triviaapp.data.local.model.UserDetails
import com.akshay.triviaapp.ui.quiz.QuizActivity
import com.akshay.triviaapp.ui.quiz.QuizViewModel
import com.akshay.triviaapp.ui.quiz.WordListAdapter
import com.akshay.triviaapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.question_list_frag.*
import kotlinx.android.synthetic.main.summary_layout.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SummaryActivity : AppCompatActivity(), View.OnClickListener {
    val summaryViewModel: SummaryViewModel by viewModels()
    lateinit var adapter: HistoryListAdapter
    lateinit var userDetails: UserDetails

    companion object {
        const val LAB_SUMMARY = "SummaryActivity"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.summary_layout)
        val intent: Intent = getIntent()
        if (intent != null && intent.hasExtra(LAB_SUMMARY)) {
            userDetails = intent.getParcelableExtra<UserDetails>(LAB_SUMMARY) as UserDetails
        }
        init()
    }

    private fun init() {
        initViews()
        initListener()


    }

    private fun initViews() {

        adapter = HistoryListAdapter(this)
        rv_answer_list.adapter = adapter
        rv_answer_list.layoutManager = LinearLayoutManager(this)
        var list = arrayListOf<UserDetails>(userDetails)
        adapter.setWords(list)
    }

    private fun initListener() {
        bt_finish.setOnClickListener(this)
        bt_history.setOnClickListener(this)
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

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.bt_history -> {
                startActivity(
                    Intent(
                        this,
                        HistoryActivity::class.java
                    )
                )
            }
            R.id.bt_finish -> {
                startActivity(Intent(this, QuizActivity::class.java))

            }
        }

    }


}