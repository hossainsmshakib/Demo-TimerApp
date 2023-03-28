package com.example.timerapp

import CounterViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CounterViewModel
    private lateinit var textViewCounter: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewCounter = findViewById(R.id.textViewCounter)

        viewModel = ViewModelProvider(this)[CounterViewModel::class.java]
        //observer
        viewModel.counter.observe(this) { count ->
            textViewCounter.text = count.toString()
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            viewModel.startCounter()
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.stopCounter()
    }
}
