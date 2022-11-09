package com.example.fragmentquizgetversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentquizgetversion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)
    }


}