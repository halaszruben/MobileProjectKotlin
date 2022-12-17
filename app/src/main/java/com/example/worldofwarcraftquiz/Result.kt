package com.example.wowquizz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.worldofwarcraftquiz.MainActivity
import com.example.worldofwarcraftquiz.databinding.ActivityResultBinding

class Result : AppCompatActivity() {

    lateinit var binding : ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val heroName = intent.getStringExtra(HERO_NAME)
        binding.tvHeroname.text = heroName

        val totalQuestions = intent.getIntExtra(TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(CORRECT_ANSWERS, 0)
        binding.tvFinalscore.text =
            "$correctAnswers" + " out of " + "$totalQuestions"

        binding.btnFinish.setOnClickListener() {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

    companion object {
        const val HERO_NAME: String = "hero_name"
        const val TOTAL_QUESTIONS: String = "total_questions"
        const val CORRECT_ANSWERS: String = "correct_answers"
    }
}