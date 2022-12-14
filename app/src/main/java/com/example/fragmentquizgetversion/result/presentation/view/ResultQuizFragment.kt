package com.example.fragmentquizgetversion.result.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.fragmentquizgetversion.R
import com.example.fragmentquizgetversion.databinding.FragmentResultQuizBinding
import com.example.fragmentquizgetversion.question.domain.model.ResultModel
import com.example.fragmentquizgetversion.question.presentation.view.QuestionFragment

class ResultQuizFragment : Fragment() {
    private lateinit var gameResultModel: ResultModel

    private var _binding: FragmentResultQuizBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("StartFragmentBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultQuizBinding.inflate(inflater)

        parseArgs()
        binding.resultText1.text = gameResultModel.list[0]
        binding.resultText2.text = gameResultModel.list[1]
        binding.resultText3.text = gameResultModel.list[2]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                retryStartFragment()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callBack)
        binding.buttonRestart.setOnClickListener {
            launchQuestionFragment()
        }
    }


    fun retryStartFragment() {

        requireActivity().supportFragmentManager.popBackStack(
            QuestionFragment.NAME, FragmentManager.POP_BACK_STACK_INCLUSIVE
        )

    }

    private fun launchQuestionFragment() {
        requireActivity().supportFragmentManager.popBackStack()
        requireActivity().supportFragmentManager.popBackStack()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, QuestionFragment())
            .commit()
    }

    private fun parseArgs() {
        gameResultModel = requireArguments().getSerializable(KEY_GAME_RESULT) as ResultModel
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    companion object {
        private const val KEY_GAME_RESULT = "game_result"

        fun newInstance(gameResultModel: ResultModel): ResultQuizFragment {
            return ResultQuizFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_GAME_RESULT, gameResultModel)
                }
            }
        }
    }
}