package com.example.fragmentquizgetversion.result.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fragmentquizgetversion.R
import com.example.fragmentquizgetversion.databinding.FragmentResultQuizBinding
import com.example.fragmentquizgetversion.question.presentation.view.QuestionFragment
import com.example.fragmentquizgetversion.question.presentation.view.QuestionItemView
import com.example.fragmentquizgetversion.result.presentation.viewmodel.ResultModel

class ResultQuizFragment : Fragment() {
//    private lateinit var gameResultModel: ResultModel

    private val args by navArgs<ResultQuizFragmentArgs>()

    private var _binding: FragmentResultQuizBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("StartFragmentBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultQuizBinding.inflate(inflater)


//        parseArgs()
        binding.resultText1.text = args.resultQuizModel.list[0]
        binding.resultText2.text = args.resultQuizModel.list[1]
        binding.resultText3.text = args.resultQuizModel.list[2]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }


    private fun initView() {
        binding.buttonRestart.setOnClickListener {
            launchQuestionFragment()
        }
    }

    private fun launchQuestionFragment() {
        findNavController().popBackStack()
        findNavController().navigate(R.id.questionFragment)

    }

//    private fun parseArgs() {
//        gameResultModel = requireArguments().getSerializable(KEY_GAME_RESULT) as ResultModel
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


//    companion object {
//        const val KEY_GAME_RESULT = "game_result"
//    }
}