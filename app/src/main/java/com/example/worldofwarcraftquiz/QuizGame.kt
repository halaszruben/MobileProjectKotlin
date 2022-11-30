package com.example.wowquizz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.Typeface.BOLD
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class QuizGame : AppCompatActivity(), View.OnClickListener {
    data class Question(
        val id: Int,
        val question: String,
        val img: Int,
        val answers: List<String>,
        val correctAnswer: Int
    )

    val questionsList: MutableList<Question> = mutableListOf(
        Question(
            1, "Who was Arthas's paladin mentor?", R.drawable.arthas,
            answers = listOf("Muradin", "Jaina", "Thrall", "Uther"), 4
        ),
        Question(
            2, "What is the name of the Orcish ritualistic duel?", R.drawable.durotan,
            answers = listOf("Mak'gora", "Warchief ceremony", "Gul'dan", "For The Horde"), 1
        ),
        Question(
            3, "Who had slain the King of the Pit Lords Mannoroth?", R.drawable.mannoroth,
            answers = listOf("Thrall", "Garrosh Hellscream", "Grom Hellscream", "Cenarius"), 3
        ),
        Question(
            4, "What was Deathwing's name before going mad?", R.drawable.deathwing,
            answers = listOf("Neltharion", "Galakrond", "Wrathion", "Sindragosa"), 1
        ),
        Question(
            5, "Scholomance is short for:", R.drawable.scholomance,
            answers = listOf(
                "School of Semantics",
                "School of Necromancy",
                "School of Numeracy",
                "School of Romans"
            ), 2
        ),
        Question(
            6, "Which city was Purged by Arthas?", R.drawable.stratholme,
            answers = listOf("Lordaeron", "Stratholme", "Stormwind", "Orgrimmar"), 2
        ),
        Question(
            7, "What is the True Lich King's Sword called?", R.drawable.frostmourne,
            answers = listOf("Warglaives", "Gorehowl", "Ashbringer", "Frostmourne"), 4
        ),
        Question(
            8, "Who freed Illidan after 10000 years?", R.drawable.tyrande,
            answers = listOf("Malfurion", "Kil'Jaeden", "Tyrande", "Maiev"), 3
        ),
        Question(
            9,
            "Who said this? 'Alright Chums, let's do this. Leeeeroooooy Jenkins!'",
            R.drawable.leeroyjenkins,
            answers = listOf("Onyxia", "Kel'Thuzad", "Sylvanas", "J Jonah Jameson"),
            4
        ),
        Question(
            10, "Which two races fought in the First War?", R.drawable.flags,
            answers = listOf(
                "Human's-Troll's",
                "Human's-Orc's",
                "Troll's-Night Elf's",
                "Night Elf's-Blood Elf's"
            ), 2
        )
    )


    private var mCurrentPosition: Int = 1
    private var mQuestionsList: MutableList<Question>? = questionsList
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mHeroName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_game)

        mHeroName = intent.getStringExtra(Result.HERO_NAME)

        setQuestion()

        findViewById<TextView>(R.id.answer_option_one).setOnClickListener(this)
        findViewById<TextView>(R.id.answer_option_two).setOnClickListener(this)
        findViewById<TextView>(R.id.answer_option_three).setOnClickListener(this)
        findViewById<TextView>(R.id.answer_option_four).setOnClickListener(this)
        findViewById<Button>(R.id.btn_submit).setOnClickListener(this)

    }

    private fun setQuestion() {

        val question = questionsList!![mCurrentPosition - 1]

        defaultOptionsView()

        if (mCurrentPosition == mQuestionsList!!.size) {
            findViewById<Button>(R.id.btn_submit).text = "This is the End Hero!"
        } else {
            findViewById<Button>(R.id.btn_submit).text = "'OOK'"
        }

        findViewById<ProgressBar>(R.id.progress_bar).progress = mCurrentPosition
        findViewById<TextView>(R.id.textView_progress).text =
            "$mCurrentPosition" + "/" + findViewById<ProgressBar>(R.id.progress_bar).max
        findViewById<TextView>(R.id.textView_questions).text = question!!.question
        findViewById<ImageView>(R.id.imageView_images).setImageResource(question.img)

        findViewById<TextView>(R.id.answer_option_one).text = question.answers[0]
        findViewById<TextView>(R.id.answer_option_two).text = question.answers[1]
        findViewById<TextView>(R.id.answer_option_three).text = question.answers[2]
        findViewById<TextView>(R.id.answer_option_four).text = question.answers[3]
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, findViewById<TextView>(R.id.answer_option_one))
        options.add(1, findViewById<TextView>(R.id.answer_option_two))
        options.add(2, findViewById<TextView>(R.id.answer_option_three))
        options.add(3, findViewById<TextView>(R.id.answer_option_four))

        for (option in options) {
            option.setTextColor(Color.parseColor("#19C3DD"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.answer_option_one -> {
                selectedOptionView(findViewById<TextView>(R.id.answer_option_one), 1)
            }
            R.id.answer_option_two -> {
                selectedOptionView(findViewById<TextView>(R.id.answer_option_two), 2)
            }
            R.id.answer_option_three -> {
                selectedOptionView(findViewById<TextView>(R.id.answer_option_three), 3)
            }
            R.id.answer_option_four -> {
                selectedOptionView(findViewById<TextView>(R.id.answer_option_four), 4)
            }
            R.id.btn_submit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, Result::class.java)
                            intent.putExtra(Result.HERO_NAME, mHeroName)
                            intent.putExtra(Result.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Result.TOTAL_QUESTIONS, mQuestionsList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionsList!!.size) {
                        findViewById<Button>(R.id.btn_submit).text =
                            "Well Done Hero, You Have Done It!"
                    } else {
                        findViewById<Button>(R.id.btn_submit).text = "Hurry, to the next one!"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun selectedOptionView(textView: TextView, selectedNumber: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedNumber

        textView.setTextColor(Color.parseColor("#FFEB3B"))
        textView.setTypeface(textView.typeface, Typeface.ITALIC.and(BOLD))
        textView.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                findViewById<TextView>(R.id.answer_option_one).background = ContextCompat
                    .getDrawable(this, drawableView)
            }
            2 -> {
                findViewById<TextView>(R.id.answer_option_two).background = ContextCompat
                    .getDrawable(this, drawableView)
            }
            3 -> {
                findViewById<TextView>(R.id.answer_option_three).background = ContextCompat
                    .getDrawable(this, drawableView)
            }
            4 -> {
                findViewById<TextView>(R.id.answer_option_four).background = ContextCompat
                    .getDrawable(this, drawableView)
            }
        }
    }

}