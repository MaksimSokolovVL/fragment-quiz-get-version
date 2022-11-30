package com.example.fragmentquizgetversion.question.presentation.view

import android.app.ActionBar.LayoutParams
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentquizgetversion.databinding.QuestionItemViewBinding
import com.example.fragmentquizgetversion.question.data.quiz.Question

class QuestionAdapter : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    val question = mutableListOf<Question>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder { //1. создает view holder

        val inflater = LayoutInflater.from(parent.context)
        val binding = QuestionItemViewBinding.inflate(inflater, parent, false)

        return QuestionViewHolder(binding)

    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) { //2. в созданную вьюшку вносим нужные нам изменения
        holder.bind(question[position])
    }

    override fun getItemCount(): Int = question.size




    class QuestionViewHolder(private val viewBinding: QuestionItemViewBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(question: Question) {
            viewBinding.textViewOneQuestion.text = question.question

            val radioButton = RadioButton(viewBinding.root.context)
            radioButton.updateLayoutParams<ViewGroup.LayoutParams> {
                width = LayoutParams.MATCH_PARENT
                height = LayoutParams.WRAP_CONTENT

            }
        }

    }
}
