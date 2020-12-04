package com.akshay.triviaapp.ui.summary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akshay.triviaapp.R
import com.akshay.triviaapp.data.local.model.UserDetails
import com.akshay.triviaapp.ui.quiz.SelectedListener
import com.google.android.material.card.MaterialCardView

class HistoryListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder>() {
    private val inflater:
            LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<UserDetails>() // Cached copy of words

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.tv_name)
        val tvAnsOne: TextView = itemView.findViewById(R.id.tv_ans_one)
        val tvAnsTwo: TextView = itemView.findViewById(R.id.tv_ans_two)
        var cardView: MaterialCardView = itemView!!.findViewById(R.id.cv_selection)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val itemView = inflater.inflate(R.layout.item_history_list, parent, false)
        return HistoryViewHolder(itemView)
    }


    override fun getItemCount(): Int {
      return  words.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val current = words[position]
        holder.wordItemView.text = current.userName
        holder.tvAnsOne.text = current.questionOneAns
        holder.tvAnsTwo.text = current.questionTwoList.toString()

    }
    internal fun setWords(words: List<UserDetails>) {
        this.words = words
        notifyDataSetChanged()
    }

}