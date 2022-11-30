package com.example.wowquizz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Result : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val heroName = intent.getStringExtra(HERO_NAME)
        findViewById<TextView>(R.id.tv_heroname).text = heroName

        val totalQuestions = intent.getIntExtra(TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(CORRECT_ANSWERS, 0)
        findViewById<TextView>(R.id.tv_finalscore).text =
            "$correctAnswers" + " out of " + "$totalQuestions"

        findViewById<Button>(R.id.btn_finish).setOnClickListener() {
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