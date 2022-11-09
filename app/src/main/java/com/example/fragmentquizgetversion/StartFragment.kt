package com.example.fragmentquizgetversion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.fragmentquizgetversion.databinding.StartFragmentBinding

class StartFragment : Fragment() {

    private var _binding: StartFragmentBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("StartFragmentBinding == null")

    override fun onCreateView(  //из макета создать view
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = StartFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { //раьота с view
        super.onViewCreated(view, savedInstanceState)

        binding.buttonStart.setOnClickListener {
            launchQuestionFragment()
//            findNavController().navigate(R.id.action_startFragment_to_questionFragment)
        }


    }

    private fun launchQuestionFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(QuestionFragment.NAME)
            .replace(R.id.fragment_container, QuestionFragment.newInstance()).commit()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        fun newInstance(): StartFragment {
            return StartFragment()
        }
    }


}