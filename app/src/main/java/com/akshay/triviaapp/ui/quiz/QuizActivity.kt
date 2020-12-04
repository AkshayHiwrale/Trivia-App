package com.akshay.triviaapp.ui.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.akshay.triviaapp.R
import com.akshay.triviaapp.data.local.model.UserDetails
import com.akshay.triviaapp.ui.summary.HistoryActivity
import com.akshay.triviaapp.ui.summary.SummaryActivity
import com.akshay.triviaapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.quiz_layout.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuizActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var viewPager: ViewPager2
    val quizViewModel: QuizViewModel by viewModels()
    var isQuestionOneAnswer: Boolean = false
    var userName: String = ""
    var questionList: MutableList<String>? = null

    companion object {
        private const val NUM_PAGES = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_layout)
        init()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        refreshUi()
    }

    private fun init() {
        initViews()
        initObservers()
        initListener()
    }

    fun refreshUi() {
        viewPager.currentItem = 0
        isQuestionOneAnswer = false
    }

    private fun initViews() {

        viewPager = findViewById(R.id.view_pager_question)
        viewPager.isUserInputEnabled = false
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter


    }

    private fun initObservers() {
        quizViewModel.liveData.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    isQuestionOneAnswer = true
                }
                Status.ERROR -> {
                    Toast.makeText(this, "Please Select Your Answer", Toast.LENGTH_LONG).show()
                }
            }
        })
        quizViewModel.questionTwoList.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    questionList = it.data
                }
                Status.ERROR -> {
                    questionList?.clear()
                    Toast.makeText(this, "Please Select Your Answer", Toast.LENGTH_LONG).show()
                }
            }
        })
        quizViewModel.userDetails.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    userName = it.data.toString()
                }
                Status.ERROR -> {
                    Toast.makeText(this, "Please add user name", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun initListener() {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        bt_back.setOnClickListener {
                            finish()
                        }
                        bt_next.text = getString(R.string.start_game)

                        bt_next.setOnClickListener {
                            if (quizViewModel.userDetails.value != null) {
                                if (position <= 2)
                                    viewPager.setCurrentItem(position + 1, true)
                            } else
                                Toast.makeText(
                                    this@QuizActivity,
                                    "Please Enter Your Name",
                                    Toast.LENGTH_LONG
                                ).show()

                        }
                    }
                    2 -> {

                        bt_next.text = getString(R.string.submit)
                        bt_next.tag = (getString(R.string.submit))
                        bt_next.setOnClickListener {

                            if (!questionList.isNullOrEmpty()) {
                                var userDetails = UserDetails()
                                userDetails.userName =
                                    quizViewModel.userDetails.value?.data.toString()
                                userDetails.questionOneAns =
                                    quizViewModel.liveData.value?.data.toString()
                                userDetails.questionTwoList =
                                    quizViewModel.questionTwoList.value?.data?.toCollection(
                                        ArrayList<String>()
                                    )
                                lifecycleScope.launch {
                                    quizViewModel.addUserDetails(userDetails)
                                }

                                var intent = Intent(this@QuizActivity, SummaryActivity::class.java)
                                intent.putExtra(SummaryActivity.LAB_SUMMARY, userDetails)
                                startActivity(intent)

                            } else {
                                Toast.makeText(
                                    this@QuizActivity,
                                    "Please Select Your Answer",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                        bt_back.setOnClickListener {
                            if (position > 0)
                                viewPager.setCurrentItem(position - 1, true)
                        }
                    }
                    1 -> {

                        bt_next.text = getString(R.string.next)
                        bt_next.tag = (getString(R.string.next))
                        bt_next.setOnClickListener {
                            if (isQuestionOneAnswer) {
                                if (position <= 2)
                                    viewPager.setCurrentItem(position + 1, true)
                            } else {
                                Toast.makeText(
                                    this@QuizActivity,
                                    "Please Select Your Answer",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                        bt_back.setOnClickListener {
                            if (position > 0)
                                viewPager.setCurrentItem(position - 1, true)
                        }
                    }
                }
            }
        })

    }

    private inner class ScreenSlidePagerAdapter(fa: QuizActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = Companion.NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> {
                    var homeFragment = UserDetailsFragment()
                    var bundle = Bundle().apply {
                        putInt("position", 0)
                    }
                    homeFragment.arguments = bundle
                    return homeFragment

                }
                1 -> {
                    var homeFragment = CheckerQuestionFragment()
                    var bundle = Bundle().apply {
                        putInt("position", 1)
                    }
                    homeFragment.arguments = bundle
                    return homeFragment

                }
                2 -> {
                    var homeFragment = SecondQuestionFragment()
                    var bundle = Bundle().apply {
                        putInt("position", 2)
                    }
                    homeFragment.arguments = bundle
                    return homeFragment

                }

            }
            return Fragment()
        }
    }


    override fun onClick(p0: View?) {

    }


}