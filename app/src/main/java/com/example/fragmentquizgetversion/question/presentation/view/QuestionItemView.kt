package com.example.fragmentquizgetversion.question.presentation.view

import android.content.Context

import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import com.example.fragmentquizgetversion.databinding.QuestionItemViewBinding
import com.example.fragmentquizgetversion.question.data.quiz.Quiz


class QuestionItemView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAtte: Int = 0
) : LinearLayout(context, attrs, defStyleAtte) {
    private val binding = QuestionItemViewBinding.inflate(LayoutInflater.from(context))

    fun getBinding(): QuestionItemViewBinding {
        return binding
    }


    init {
        addView(binding.root)
    }





    fun setQuestions2(answersList: List<String>) {
        for (i in 0..answersList.lastIndex){
        when (i) {
            0 -> binding.firstAnswer.text = answersList[i]
            1 -> binding.secondAnswer.text = answersList[i]
            2 -> binding.thirdAnswer.text = answersList[i]
            3 -> binding.fourthAnswer.text = answersList[i]
            else  -> error("${answersList[i]}  -> there is no such number")
        }}
    }


    fun getRadioGroup(): RadioGroup {
        return binding.questionGroup
    }

    fun getQuestions(numberQuestion: Int): TextView {
        return when (numberQuestion) {
            0 -> binding.firstAnswer
            1 -> binding.secondAnswer
            2 -> binding.thirdAnswer
            3 -> binding.fourthAnswer
            else  -> error("$numberQuestion  -> there is no such number")
        }
    }


}