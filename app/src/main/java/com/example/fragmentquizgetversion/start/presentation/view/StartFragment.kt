package com.example.fragmentquizgetversion.start.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fragmentquizgetversion.R
import com.example.fragmentquizgetversion.databinding.FragmentStartBinding


class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("StartFragmentBinding == null")

    override fun onCreateView(  //из макета создать view
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { //раьота с view
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.buttonStart.setOnClickListener {
            launchQuestionFragment()
        }
    }

    private fun launchQuestionFragment() {
        findNavController().navigate(R.id.action_startFragment_to_questionFragment)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}