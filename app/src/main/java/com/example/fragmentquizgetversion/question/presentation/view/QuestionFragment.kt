package com.example.fragmentquizgetversion.question.presentation.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions
import com.example.fragmentquizgetversion.R
import com.example.fragmentquizgetversion.databinding.FragmentQuestionBinding
import com.example.fragmentquizgetversion.result.presentation.viewmodel.ResultModel
import com.example.fragmentquizgetversion.result.presentation.view.ResultQuizFragment
import com.example.fragmentquizgetversion.question.data.quiz.Quiz
import com.example.fragmentquizgetversion.question.data.quiz.QuizStorage
import com.example.fragmentquizgetversion.start.presentation.view.StartFragment

private val quiz: Quiz = QuizStorage.getQuiz(QuizStorage.Locale.Ru)

class QuestionFragment : Fragment() {

    private val gameResultModel = ResultModel()

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("StartFragmentBinding == null")

    override fun onCreateView(  //из макета создать view
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { //работа с view
        super.onViewCreated(view, savedInstanceState)

        val firstCustomView = binding.customView1
        val secondCustomView = binding.customView2
        val thirdCustomView = binding.customView3


        //**************
        val radioGroup = binding.customView1.getRadioGroup()
        val radioButton1 = RadioButton(context)
        radioButton1.text=quiz.questions[0].question
        radioGroup.addView(radioButton1)

        val d3 = quiz.questions.size
        val question = quiz.questions[0]
        val f4 = question.answers.size
//****************


        for (i in 0..quiz.questions.lastIndex) {
            val question = quiz.questions[i]
            when (i) {
                0 -> {
                    firstCustomView.getBinding().textViewOneQuestion.text = question.question
                    firstCustomView.setQuestions2(question.answers)
                }
                1 -> {
                    secondCustomView.getBinding().textViewOneQuestion.text = question.question
                    secondCustomView.setQuestions2(question.answers)
                }
                2 -> {
                    thirdCustomView.getBinding().textViewOneQuestion.text = question.question
                    thirdCustomView.setQuestions2(question.answers)
                }
            }
        }

        initView()

        fun checkedChangeRadiosButton(
            nameQuestionItemView: QuestionItemView,
            numberCustomView: Int
        ) {

            nameQuestionItemView.getBinding().questionGroup.setOnCheckedChangeListener { _, checkedId ->
//                var id = nameCustomView.getBinding().questionGroup.checkedRadioButtonId
                when (checkedId) {
                    nameQuestionItemView.getBinding().firstAnswer.id -> {
                        showToast(quiz.questions[numberCustomView].feedback[0])
                        gameResultModel.list[numberCustomView] =
                            quiz.questions[numberCustomView].feedback[0]
                    }
                    nameQuestionItemView.getBinding().secondAnswer.id -> {
                        showToast(quiz.questions[numberCustomView].feedback[1])
                        gameResultModel.list[numberCustomView] =
                            quiz.questions[numberCustomView].feedback[1]
                    }
                    nameQuestionItemView.getBinding().thirdAnswer.id -> {
                        showToast(quiz.questions[numberCustomView].feedback[2])
                        gameResultModel.list[numberCustomView] =
                            quiz.questions[numberCustomView].feedback[2]
                    }
                    nameQuestionItemView.getBinding().fourthAnswer.id -> {
                        showToast(quiz.questions[numberCustomView].feedback[3])
                        gameResultModel.list[numberCustomView] =
                            quiz.questions[numberCustomView].feedback[3]
                    }
                }
            }
        }

        checkedChangeRadiosButton(firstCustomView, 0)
        checkedChangeRadiosButton(secondCustomView, 1)
        checkedChangeRadiosButton(thirdCustomView, 2)
    }

    private fun initView(){
        binding.buttonBack.setOnClickListener {
            launchStartFragment()
        }

        binding.buttonResult.setOnClickListener {
            launchResultQuiz(gameResultModel)
        }
    }

    private fun launchStartFragment() {
        findNavController().navigate(R.id.action_questionFragment_to_startFragment)
    }

    private fun launchResultQuiz(gameResultModel: ResultModel) {
//        val args = Bundle().apply {
//            putSerializable(ResultQuizFragment.KEY_GAME_RESULT, gameResultModel)
//        }
//        findNavController().navigate(R.id.action_questionFragment_to_resultQuizFragment, args, navOptions { this.anim {  } })
        findNavController().navigate(QuestionFragmentDirections.actionQuestionFragmentToResultQuizFragment(gameResultModel))
    }

    private fun showToast(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}