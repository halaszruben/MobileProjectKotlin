package com.example.worldofwarcraftquiz.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.worldofwarcraftquiz.R
import com.example.worldofwarcraftquiz.databinding.FragmentGameBinding
import com.example.worldofwarcraftquiz.end.EndViewModel
import com.example.worldofwarcraftquiz.end.EndViewModelFactory

class GameFragment : Fragment() {

    data class Question(
        val question: String,
        val img: Int,
        val answers: List<String>
    )

    val questionsList: MutableList<Question> = mutableListOf(
        Question(
            "Who was Arthas's paladin mentor?", R.drawable.arthas,
            answers = listOf("Uther", "Muradin", "Jaina", "Thrall")
        ),
        Question(
            "What is the name of the Orcish ritualistic duel?", R.drawable.durotan,
            answers = listOf("Mak'gora", "Warchief ceremony", "Gul'dan", "For The Horde")
        ),
        Question(
            "Who had slain the King of the Pit Lords Mannoroth?", R.drawable.mannoroth,
            answers = listOf("Grom Hellscream", "Thrall", "Garrosh Hellscream", "Cenarius")
        ),
        Question(
            "What was Deathwing's name before going mad?", R.drawable.deathwing,
            answers = listOf("Neltharion", "Galakrond", "Wrathion", "Sindragosa")
        ),
        Question(
            "Scholomance is short for:", R.drawable.scholomance,
            answers = listOf(
                "School of Necromancy",
                "School of Semantics",
                "School of Numeracy",
                "School of Romans"
            )
        ),
        Question(
            "Which city was Purged by Arthas?", R.drawable.stratholme,
            answers = listOf("Stratholme", "Lordaeron", "Stormwind", "Orgrimmar")
        ),
        Question(
            "What is the True Lich King's Sword called?", R.drawable.frostmourne,
            answers = listOf("Frostmourne", "Warglaives", "Gorehowl", "Ashbringer")
        ),
        Question(
            "Who freed Illidan after 10000 years?", R.drawable.tyrande,
            answers = listOf("Tyrande", "Malfurion", "Kil'Jaeden", "Maiev")
        ),
        Question(

            "Who said this? 'Alright Chums, let's do this. Leeeeroooooy Jenkins!'",
            R.drawable.leeroyjenkins,
            answers = listOf("J Jonah Jameson", "Onyxia", "Kel'Thuzad", "Sylvanas")
        ),
        Question(
            "Which two races fought in the First War?", R.drawable.flags,
            answers = listOf(
                "Human's-Orc's",
                "Human's-Troll's",
                "Troll's-Night Elf's",
                "Night Elf's-Blood Elf's"
            )
        )
    )

    lateinit var mQuestion: Question
    lateinit var mAnswers: MutableList<String>
    private var mNumberQuestion: Int = questionsList.size
    private var mQuestionIndex: Int = 0

    lateinit var viewModel: GameViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
            inflater,
            R.layout.fragment_game, container, false
        )

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        binding.gameViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        randomizeQuestions()

        binding.game = this

        binding.btnSubmit.setOnClickListener() { view: View ->

            val checkId = binding.questionRadioGroup.checkedRadioButtonId

            if (-1 != checkId) {

                binding.firstAnswer.isChecked = false
                binding.thirdAnswer.isChecked = false
                binding.secondAnswer.isChecked = false
                binding.fourthAnswer.isChecked = false

                var answerIndex = 0
                when (checkId) {

                    binding.secondAnswer.id -> answerIndex = 1
                    binding.thirdAnswer.id -> answerIndex = 2
                    binding.fourthAnswer.id -> answerIndex = 3
                }

                binding.firstAnswer

                if (mAnswers[answerIndex] == mQuestion.answers[0]) {

                    mQuestionIndex++

                    if (mQuestionIndex < mNumberQuestion) {

                        mQuestion = questionsList[mQuestionIndex]
                        setQuestion()
                        binding.invalidateAll()

                    } else {
                        view.findNavController().navigate(
                            GameFragmentDirections.navigateToEndFragment(mNumberQuestion)
                        )
                    }
                } else {
                    view.findNavController()
                        .navigate(GameFragmentDirections.navigateToHomeFragment())
                }
            }
        }
        return binding.root
    }

    private fun randomizeQuestions() {

        questionsList.shuffle()
        mQuestionIndex = 0
        setQuestion()
    }

    private fun setQuestion() {

        mQuestion = questionsList[mQuestionIndex]
        mAnswers = mQuestion.answers.toMutableList()
        mAnswers.shuffle()
    }

}




