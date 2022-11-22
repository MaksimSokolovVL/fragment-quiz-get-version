package com.example.fragmentquizgetversion.result.presentation.view

import android.animation.*
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fragmentquizgetversion.R
import com.example.fragmentquizgetversion.databinding.FragmentResultQuizBinding
import com.example.fragmentquizgetversion.question.presentation.view.QuestionFragment
import com.example.fragmentquizgetversion.question.presentation.view.QuestionItemView
import com.example.fragmentquizgetversion.result.presentation.viewmodel.ResultModel

class ResultQuizFragment : Fragment() {
    private val args by navArgs<ResultQuizFragmentArgs>()
    private lateinit var textView1: TextView

    private var _binding: FragmentResultQuizBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("StartFragmentBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultQuizBinding.inflate(inflater)

        binding.resultText1.text = args.resultQuizModel.list[0]
        binding.resultText2.text = args.resultQuizModel.list[1]
        binding.resultText3.text = args.resultQuizModel.list[2]

        textView1 = binding.resultText1

        ObjectAnimator.ofFloat(binding.buttonRestart, View.TRANSLATION_Z, 5f, 30f)
            .apply {
                duration = 2000
                interpolator = AccelerateDecelerateInterpolator()
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.REVERSE
                start()
            }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        animObject(textView1)
        animObjectXML(view)

    }


    private fun initView() {
        binding.buttonRestart.setOnClickListener {
            launchQuestionFragment()
        }
    }

    private fun launchQuestionFragment() {
        findNavController().popBackStack(R.id.questionFragment, true)
        findNavController().navigate(R.id.action_resultQuizFragment_to_questionFragment)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun animObject(textView: TextView) {
        val textShader = LinearGradient(
            0f, 0f,
            textView.paint.measureText(textView.text.toString()),
            textView.textSize,
            intArrayOf(Color.MAGENTA, Color.MAGENTA, Color.MAGENTA),
            null,
            Shader.TileMode.CLAMP
        )

        textView.paint.shader = textShader
        textView.invalidate()

        ValueAnimator.ofObject(
            GradientArgbEvaluator,
            intArrayOf(Color.MAGENTA, Color.MAGENTA, Color.MAGENTA),
            intArrayOf(Color.MAGENTA, Color.MAGENTA, Color.BLUE),
            intArrayOf(Color.MAGENTA, Color.BLUE, Color.BLACK),
            intArrayOf(Color.BLUE, Color.BLACK, Color.RED),
            intArrayOf(Color.BLACK, Color.RED, Color.GREEN),
            intArrayOf(Color.BLACK, Color.GREEN, Color.BLUE),
            intArrayOf(Color.GREEN, Color.BLUE, Color.CYAN),
            intArrayOf(Color.BLUE, Color.CYAN, Color.YELLOW),
            intArrayOf(Color.CYAN, Color.YELLOW, Color.MAGENTA)
        ).apply {
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            duration = 5000

            addUpdateListener { //слушатель изменений анимаций, шде полечаем it.ValueAnimator и массива выше,
                //дальше копируем текст нашего шейдара
                val shader = LinearGradient(
                    0f, 0f,
                    textView.paint.measureText(textView.text.toString()),
                    textView.textSize,
                    it.animatedValue as IntArray, // добавим значение, того что получаем
                    null,
                    Shader.TileMode.CLAMP
                )

                textView.paint.shader = shader
                textView.invalidate()
            }
        }.start()
    }

    private fun animObjectXML(view: View) {
        (AnimatorInflater.loadAnimator(
            view.context,
            R.animator.animation_text_view
        ) as ObjectAnimator)
            .apply {
                target = binding.resultText3
                start()
            }
    }
}

object GradientArgbEvaluator : TypeEvaluator<IntArray> {
    private val argbEvaluator = ArgbEvaluator()

    override fun evaluate(fraction: Float, startValue: IntArray, endValue: IntArray): IntArray {
        return startValue.mapIndexed { index, item ->
            argbEvaluator.evaluate(fraction, item, endValue[index]) as Int
        }.toIntArray()
    }
}