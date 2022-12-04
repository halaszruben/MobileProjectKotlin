package com.example.worldofwarcraftquiz.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val _currentTimer = MutableLiveData<Long>()
    val currentTimer: LiveData<Long>
        get() = _currentTimer

    val currentTimeToString = Transformations.map(currentTimer) { time ->
        DateUtils.formatElapsedTime(time)
    }

    private val timer: CountDownTimer

    init {
        timer = object : CountDownTimer(300000, 1000) {
            override fun onTick(p0: Long) {
                _currentTimer.value = p0 / 1000
            }

            override fun onFinish() {
                _currentTimer.value = 0
            }
        }
        timer.start()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}