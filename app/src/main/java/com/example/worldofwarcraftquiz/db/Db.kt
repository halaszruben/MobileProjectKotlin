package com.example.worldofwarcraftquiz.db

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.worldofwarcraftquiz.R
import com.example.worldofwarcraftquiz.databinding.FragmentDbBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class Db : Fragment() {

    private lateinit var binding: FragmentDbBinding
    private lateinit var appDb: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentDbBinding>(
            inflater, R.layout.fragment_db, container, false
        )

        appDb = AppDatabase.getDatabase(requireContext())

        binding.btnWriteData.setOnClickListener {

            writeData()
        }

        binding.btnDeleteData.setOnClickListener {

            GlobalScope.launch { appDb.heroesDao().deleteAll() }
        }

        binding.btnNewGame.setOnClickListener() { view: View ->
            view.findNavController().navigate(
                DbDirections.navigateToHomeFragment()
            )
        }


        return binding.root
    }

    private fun writeData() {

        val heroName = binding.etHeroName.text.toString()
        val spec = binding.etSpec.text.toString()
        val expansion = binding.etExpansion.text.toString()

        if (heroName.isNotEmpty() && spec.isNotEmpty() && expansion.isNotEmpty()) {

            val hero = Heroes(
                null, heroName, spec, expansion
            )
            GlobalScope.launch(Dispatchers.IO) {
                appDb.heroesDao().insert(hero)
            }

            binding.etHeroName.text.clear()
            binding.etSpec.text.clear()
            binding.etExpansion.text.clear()

            Toast.makeText(context, "You will be remembered forever Hero!", Toast.LENGTH_LONG)
                .show()
        } else {
            Toast.makeText(context, "We need all the credentials Hero!", Toast.LENGTH_LONG).show()
        }

    }


    private fun readData() {
        val heroes = appDb.heroesDao().getAll()
        var displayText = ""

        for (hero in heroes)
            displayText = "${hero.heroName} ${hero.spec} ${hero.expansion} \n"

        binding.tvHeroname.text = displayText

    }


}