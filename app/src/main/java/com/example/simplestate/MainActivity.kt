package com.example.simplestate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import android.view.View
import com.example.simplestate.databinding.ActivityMainBinding
import com.example.simplestate.SimpleState.State


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<SimpleState>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.Increment.setOnClickListener { viewModel.increment() }
        binding.RandomColor.setOnClickListener { viewModel.setRandomColor() }
        binding.SwitchVisibility.setOnClickListener { viewModel.switchVisibility() }

        if (viewModel.state.value == null) {
            viewModel.initState(
                SimpleState.State(
                    counterValue = 0,
                    counterTextColor = ContextCompat.getColor(this, R.color.black),
                    counterIsVisible = true
                )
            )
        }
        viewModel.state.observe(this, Observer {
            renderState(it)
        })
    }

    private fun renderState(state: State) = with(binding) {
        TextView.setText(state.counterValue.toString())
        TextView.setTextColor(state.counterTextColor)
        TextView.visibility = if (state.counterIsVisible) View.VISIBLE else View.INVISIBLE
    }
}