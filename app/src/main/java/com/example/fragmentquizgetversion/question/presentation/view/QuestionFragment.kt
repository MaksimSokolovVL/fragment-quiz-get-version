package com.example.fragmentquizgetversion.question.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fragmentquizgetversion.R
import com.example.fragmentquizgetversion.result.presentation.view.ResultQuizFragment
import com.example.fragmentquizgetversion.databinding.FragmentQuestionBinding
import com.example.fragmentquizgetversion.question.domain.model.ResultModel
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { //раьота с view
        super.onViewCreated(view, savedInstanceState)

        val firstCustomView = binding.customView1
        val secondCustomView = binding.customView2
        val thirdCustomView = binding.customView3

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

        checkedChangeRadiosButton(firstCustomView, 0)
        checkedChangeRadiosButton(secondCustomView, 1)
        checkedChangeRadiosButton(thirdCustomView, 2)
    }

    private fun initView() {
        binding.buttonBack.setOnClickListener {
            launchStartFragment()
        }

        binding.buttonResult.setOnClickListener {
            launchResultQuiz(gameResultModel)
        }
    }

    private fun checkedChangeRadiosButton(nameCustomView: QuestionItemView, numberCustomView: Int) {

        nameCustomView.getBinding().questionGroup.setOnCheckedChangeListener { _, checkedId ->
//                var id = nameCustomView.getBinding().questionGroup.checkedRadioButtonId
            when (checkedId) {
                nameCustomView.getBinding().firstAnswer.id -> {
                    showToast(quiz.questions[numberCustomView].feedback[0])
                    gameResultModel.list[numberCustomView] = quiz.questions[numberCustomView].feedback[0]
                }
                nameCustomView.getBinding().secondAnswer.id -> {
                    showToast(quiz.questions[numberCustomView].feedback[1])
                    gameResultModel.list[numberCustomView] = quiz.questions[numberCustomView].feedback[1]
                }
                nameCustomView.getBinding().thirdAnswer.id -> {
                    showToast(quiz.questions[numberCustomView].feedback[2])
                    gameResultModel.list[numberCustomView] = quiz.questions[numberCustomView].feedback[2]
                }
                nameCustomView.getBinding().fourthAnswer.id -> {
                    showToast(quiz.questions[numberCustomView].feedback[3])
                    gameResultModel.list[numberCustomView] = quiz.questions[numberCustomView].feedback[3]
                }
            }
        }
    }

    private fun launchStartFragment() {

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, StartFragment())
            .commit()
    }

    private fun launchResultQuiz(gameResultModel: ResultModel) {

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ResultQuizFragment.newInstance(gameResultModel))
            .addToBackStack(null)
            .commit()

    }
    private fun showToast(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        const val NAME = "QuestionFragment"

//        fun newInstance(): QuestionFragment {
//            return QuestionFragment()
//        }
    }
}