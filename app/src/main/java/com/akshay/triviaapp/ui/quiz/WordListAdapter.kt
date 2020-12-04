package com.akshay.triviaapp.ui.quiz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akshay.triviaapp.R
import com.google.android.material.card.MaterialCardView

class WordListAdapter internal constructor(
    context: Context,selectedListener:SelectedListener
) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {
    var selectedListener = selectedListener
    private val inflater:
            LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<String>() // Cached copy of words

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.tv_ans_text)
        var cardView: MaterialCardView = itemView!!.findViewById(R.id.cv_selection)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.item_question_list, parent, false)
        return WordViewHolder(itemView)
    }


    override fun getItemCount(): Int {
      return  words.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = words[position]
        holder.wordItemView.text = current
        holder.cardView.isChecked = selectedListener.isSelectedItem(current,true)

        holder.cardView?.setOnClickListener {
            holder.cardView.toggle()
            selectedListener?.addSelectedItem(current,holder.cardView.isChecked)
        }
    }
    internal fun setWords(words: List<String>) {
        this.words = words
        notifyDataSetChanged()
    }

}