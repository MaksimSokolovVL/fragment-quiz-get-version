package com.example.fragmentquizgetversion.question.presentation.view

import android.app.ActionBar.LayoutParams
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentquizgetversion.databinding.MyCustomViewBinding
import com.example.fragmentquizgetversion.question.data.quiz.Question

class QuestionAdapter(
    private val callback: QuestionClickCallback,
) : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    val questions = mutableListOf<Question>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MyCustomViewBinding.inflate(inflater, parent, false)
        return QuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    override fun getItemCount() = questions.size

    inner class QuestionViewHolder(private val viewBinding: MyCustomViewBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(question: Question) {
            viewBinding.textViewOneQuestion.text = question.question

            viewBinding.questionGroup.removeAllViews()

            question.answers.forEachIndexed { index, answerText ->
                val button = RadioButton(viewBinding.root.context)
                button.layoutParams =
                    ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                button.text = answerText

                viewBinding.questionGroup.addView(button)
                button.setOnCheckedChangeListener { compoundButton, b ->
                    callback.onAnswerClick(question, index)
                }
            }
        }
    }
}

interface QuestionClickCallback {
    fun onAnswerClick(question: Question, answerPosition: Int)
}
