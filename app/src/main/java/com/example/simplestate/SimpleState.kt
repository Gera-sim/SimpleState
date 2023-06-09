package com.example.simplestate

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random


class SimpleState : ViewModel() {
    val state: LiveData<State> get() = stateLiveData
    private val stateLiveData = MutableLiveData<State>()

    fun initState(state: State) {
        stateLiveData.value = state
            }

    fun increment() {
        val oldState = stateLiveData.value
        stateLiveData.value = oldState?.copy(
            counterValue = oldState.counterValue + 1
        )
    }

    fun setRandomColor() {
        val oldState = stateLiveData.value
        stateLiveData.value = oldState?.copy(
            counterTextColor = Color.rgb(
                Random.nextInt(256),// red
                Random.nextInt(256),// green
                Random.nextInt(256)// blue
            )
        )
    }

    fun switchVisibility() {
        val oldState = stateLiveData.value
        stateLiveData.value = oldState?.copy(
            counterIsVisible = !oldState.counterIsVisible
        )
    }

    data class State(
        val counterValue: Int,
        val counterTextColor: Int,
        val counterIsVisible: Boolean
    )
}