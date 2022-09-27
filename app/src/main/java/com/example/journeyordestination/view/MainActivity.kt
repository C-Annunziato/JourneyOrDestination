package com.example.journeyordestination.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.journeyordestination.databinding.ActivityMainBinding

const val TAG = "fragment"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}