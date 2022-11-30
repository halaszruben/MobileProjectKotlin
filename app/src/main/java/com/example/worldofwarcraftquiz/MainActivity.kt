package com.example.wowquizz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.example.wowquizz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root

        setContentView(root)

        binding.btnLogin.setOnClickListener {
            if (binding.etHeroname.text.toString().isEmpty()) {
                Toast.makeText(
                    this,
                    "Enter a name Hero, or you won't be remembered and the people will FORGET your deeds!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val intent = Intent(this, QuizGame::class.java)
                intent.putExtra(Result.HERO_NAME, binding.etHeroname.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}