package com.akshay.triviaapp.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akshay.triviaapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.question_list_frag.*

class CheckerQuestionFragment : Fragment(), SelectedListener {
    lateinit var quizViewModel: QuizViewModel
    var selectedAnswer: String? = null

    companion object {
        const val TAG = "CheckerQuestionFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.question_list_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quizViewModel = ViewModelProvider(requireActivity()).get(QuizViewModel::class.java)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()

    }

    private fun init() {
        initViews()
        initObservers()
        initListener()
    }

    private fun initViews() {
        tv_question.text = "Who is the best cricketer in the world?"
        val adapter = WordListAdapter(requireActivity(), this)
        rv_question_list.adapter = adapter
        rv_question_list.layoutManager = LinearLayoutManager(activity)
        var list = arrayListOf<String>(
            "Sachin Tendulkar",
            " Virat Kolli",
            "Adam Gilchirst",
            "Jacques Kallis"
        )
        adapter.setWords(list)
    }

    private fun initObservers() {
    }

    private fun initListener() {
    }

    override fun addSelectedItem(item: Any, isSelected: Boolean) {
        if (isSelected) {
            selectedAnswer = item as String
            quizViewModel.updateAnswer(selectedAnswer!!)
        }
        rv_question_list.adapter?.notifyDataSetChanged()
    }

    override fun isSelectedItem(item: Any, isSelected: Boolean): Boolean {
        if (selectedAnswer != null && selectedAnswer!!.equals(item)) {
            return true
        }
        return false
    }

}
