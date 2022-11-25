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








    fun getRadioGroup(): RadioGroup {
        return binding.questionGroup
    }




}