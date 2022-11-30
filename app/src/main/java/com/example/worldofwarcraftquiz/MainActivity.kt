package com.example.wowquizz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.btn_login).setOnClickListener {
            if (findViewById<TextView>(R.id.et_heroname).text.toString().isEmpty()) {
                Toast.makeText(
                    this,
                    "Enter a name Hero, or you won't be remembered and the people will FORGET your deeds!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val intent = Intent(this, QuizGame::class.java)
                intent.putExtra(Result.HERO_NAME, findViewById<TextView>(R.id.et_heroname).text.toString())
                startActivity(intent)
                finish()
            }
        }

    }
}