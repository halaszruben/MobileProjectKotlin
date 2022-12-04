package com.example.worldofwarcraftquiz.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    fun onWrong() {
        _score.value = (score.value)?.minus(1)
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
    }

    init {
        _score.value = 0
    }

}