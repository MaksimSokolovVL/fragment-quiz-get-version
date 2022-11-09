package com.example.fragmentquizgetversion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.fragmentquizgetversion.databinding.FragmentResultQuizBinding

class ResultQuizFragment : Fragment() {
    private lateinit var gameResult: Result

    private var _binding: FragmentResultQuizBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("StartFragmentBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultQuizBinding.inflate(inflater)

        parseArgs()
        binding.resultText1.text = gameResult.list[0]
        binding.resultText2.text = gameResult.list[1]
        binding.resultText3.text = gameResult.list[2]

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
            .replace(R.id.fragment_container, QuestionFragment.newInstance())
            .commit()
    }

    private fun parseArgs() {
        gameResult = requireArguments().getSerializable(KEY_GAME_RESULT) as Result
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    companion object {
        private const val KEY_GAME_RESULT = "game_result"

        fun newInstance(gameResult: Result): ResultQuizFragment {
            return ResultQuizFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }
}