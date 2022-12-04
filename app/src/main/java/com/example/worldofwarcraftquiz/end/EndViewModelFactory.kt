package com.example.worldofwarcraftquiz.end

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EndViewModelFactory(private val questionsAnswered: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EndViewModel::class.java)) {
            return EndViewModel(questionsAnswered) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}