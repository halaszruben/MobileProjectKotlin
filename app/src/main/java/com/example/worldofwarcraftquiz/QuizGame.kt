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
import com.example.wowquizz.databinding.ActivityMainBinding
import com.example.wowquizz.databinding.ActivityQuizGameBinding

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

    lateinit var binding: ActivityQuizGameBinding

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: MutableList<Question>? = questionsList
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mHeroName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuizGameBinding.inflate(layoutInflater)

        setContentView(binding.root)

        mHeroName = intent.getStringExtra(Result.HERO_NAME)

        setQuestion()

        binding.answerOptionOne.setOnClickListener(this)
        binding.answerOptionTwo.setOnClickListener(this)
        binding.answerOptionThree.setOnClickListener(this)
        binding.answerOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)

    }

    private fun setQuestion() {

        val question = questionsList!![mCurrentPosition - 1]

        defaultOptionsView()

        if (mCurrentPosition == mQuestionsList!!.size) {
            binding.btnSubmit.text = "This is the End Hero!"
        } else {
            binding.btnSubmit.text = "'OOK'"
        }

        binding.progressBar.progress = mCurrentPosition
        binding.textViewProgress.text =
            "$mCurrentPosition" + "/" + binding.progressBar.max
        binding.textViewQuestions.text = question!!.question
        binding.imageViewImages.setImageResource(question.img)

        binding.answerOptionOne.text = question.answers[0]
        binding.answerOptionTwo.text = question.answers[1]
        binding.answerOptionThree.text = question.answers[2]
        binding.answerOptionFour.text = question.answers[3]
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, binding.answerOptionOne)
        options.add(1, binding.answerOptionTwo)
        options.add(2, binding.answerOptionThree)
        options.add(3, binding.answerOptionFour)

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
            binding.answerOptionOne.id -> {
                selectedOptionView(binding.answerOptionOne, 1)
            }
            binding.answerOptionTwo.id -> {
                selectedOptionView(binding.answerOptionTwo, 2)
            }
            binding.answerOptionThree.id -> {
                selectedOptionView(binding.answerOptionThree, 3)
            }
            binding.answerOptionFour.id -> {
                selectedOptionView(binding.answerOptionFour, 4)
            }
            binding.btnSubmit.id -> {
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
                        binding.btnSubmit.text =
                            "Well Done Hero, You Have Done It!"
                    } else {
                        binding.btnSubmit.text = "Hurry, to the next one!"
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
                binding.answerOptionOne.background = ContextCompat
                    .getDrawable(this, drawableView)
            }
            2 -> {
                binding.answerOptionTwo.background = ContextCompat
                    .getDrawable(this, drawableView)
            }
            3 -> {
                binding.answerOptionThree.background = ContextCompat
                    .getDrawable(this, drawableView)
            }
            4 -> {
                binding.answerOptionFour.background = ContextCompat
                    .getDrawable(this, drawableView)
            }
        }
    }

}