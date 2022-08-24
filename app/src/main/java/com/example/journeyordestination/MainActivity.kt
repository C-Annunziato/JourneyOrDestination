package com.example.journeyordestination

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.journeyordestination.Adapters.DestinationAdapter
import com.example.journeyordestination.Api.RetrofitInstance
import com.example.journeyordestination.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException


class MainActivity : AppCompatActivity() {

private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}