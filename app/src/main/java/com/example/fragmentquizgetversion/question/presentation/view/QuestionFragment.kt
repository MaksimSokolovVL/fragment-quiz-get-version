package com.example.fragmentquizgetversion.question.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentquizgetversion.R
import com.example.fragmentquizgetversion.result.presentation.view.ResultQuizFragment
import com.example.fragmentquizgetversion.databinding.FragmentQuestionBinding
import com.example.fragmentquizgetversion.question.data.quiz.Question
import com.example.fragmentquizgetversion.question.domain.model.ResultModel
import com.example.fragmentquizgetversion.question.data.quiz.Quiz
import com.example.fragmentquizgetversion.question.data.quiz.QuizStorage
import com.example.fragmentquizgetversion.start.presentation.view.StartFragment

private val quiz: Quiz = QuizStorage.getQuiz(QuizStorage.Locale.Ru)

class QuestionFragment : Fragment(), QuestionClickCallback {

    private val gameResultModel = ResultModel()

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("StartFragmentBinding == null")

    private val questionAdapter = QuestionAdapter(this)

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

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = questionAdapter

        questionAdapter.questions.clear()
        questionAdapter.questions.addAll(quiz.questions)
        questionAdapter.notifyDataSetChanged()
    }

    override fun onAnswerClick(question: Question, answerPosition: Int) {
        Log.d(
            "onAnswerClick",
            "${question.question}, answer: ${question.answers.getOrNull(answerPosition)}"
        )
    }

    private fun initView() {
        binding.buttonBack.setOnClickListener {
            launchStartFragment()
        }

        binding.buttonResult.setOnClickListener {
            launchResultQuiz(gameResultModel)
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