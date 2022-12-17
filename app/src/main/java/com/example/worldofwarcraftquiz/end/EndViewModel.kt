package com.example.worldofwarcraftquiz.end

import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class EndViewModel(questionsAnswered: Int) : ViewModel() {

    private val _answered = MutableLiveData<Int>()
    val answered: LiveData<Int>
        get() = _answered

    private val _oneMore = MutableLiveData<Boolean>()
    val oneMore: LiveData<Boolean>
        get() = _oneMore

    fun onPlayAgain() {
        _oneMore.value = true
    }

    fun onPlayAgainComplete() {
        _oneMore.value = false
    }

    init {
        _answered.value = questionsAnswered
    }


}