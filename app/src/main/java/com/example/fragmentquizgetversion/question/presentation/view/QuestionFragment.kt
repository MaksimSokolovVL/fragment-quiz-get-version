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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentquizgetversion.R
import com.example.fragmentquizgetversion.databinding.FragmentQuestionBinding
import com.example.fragmentquizgetversion.result.presentation.viewmodel.ResultModel
import com.example.fragmentquizgetversion.result.presentation.view.ResultQuizFragment
import com.example.fragmentquizgetversion.question.data.quiz.Quiz
import com.example.fragmentquizgetversion.question.data.quiz.QuizStorage
import com.example.fragmentquizgetversion.start.presentation.view.StartFragment

private val quiz: Quiz = QuizStorage.getQuiz(QuizStorage.Locale.Ru)

class QuestionFragment : Fragment() {
    //------------------
    private val gameResultModel = ResultModel()

    //------------------
    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("StartFragmentBinding == null")

    private val questionAdapter = QuestionAdapter()

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
//-----------------
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext()) // || context
        binding.recyclerView.adapter = questionAdapter
//-----------------


        questionAdapter.question.clear()
        questionAdapter.question.addAll(quiz.questions)
        questionAdapter.notifyDataSetChanged()

        initView()
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
        findNavController().navigate(R.id.action_questionFragment_to_startFragment)
    }

    private fun launchResultQuiz(gameResultModel: ResultModel) {
        findNavController().navigate(
            QuestionFragmentDirections.actionQuestionFragmentToResultQuizFragment(gameResultModel)
        )
    }

    private fun showToast(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}