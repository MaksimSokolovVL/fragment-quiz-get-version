package com.example.fragmentquizgetversion.start.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import com.example.fragmentquizgetversion.R

import com.example.fragmentquizgetversion.databinding.StartFragmentBinding
import com.example.fragmentquizgetversion.question.presentation.view.QuestionFragment
import com.example.fragmentquizgetversion.start.presentation.viewmodel.StartViewModel

class StartFragment : Fragment() {

    private var _binding: StartFragmentBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("StartFragmentBinding == null")

    private lateinit var viewModel: StartViewModel

    override fun onCreateView(  //из макета создать view
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View {
        _binding = StartFragmentBinding.inflate(inflater)
        viewModel = createViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { //раьота с view
        super.onViewCreated(view, savedInstanceState)
        initView()
    }


    private fun createViewModel() = StartViewModel()

    private fun initView() {
        binding.buttonStart.setOnClickListener {
            launchQuestionFragment()
//            findNavController().navigate(R.id.action_startFragment_to_questionFragment)
        }
    }


    private fun launchQuestionFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(QuestionFragment.NAME)
            .replace(R.id.fragment_container, QuestionFragment()).commit()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        const val NAME = "StartFragment"

    }


}