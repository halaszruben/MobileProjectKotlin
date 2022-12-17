package com.example.worldofwarcraftquiz.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.worldofwarcraftquiz.R
import com.example.worldofwarcraftquiz.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home, container, false
        )

        binding.btnLogin.setOnClickListener { view: View ->
            if (binding.editTextHeroName.text.toString().isEmpty()) {
                Toast.makeText(
                    context,
                    "Enter a name Hero, or you won't be remembered and the people will FORGET your deeds!",
                    Toast.LENGTH_LONG
                ).show()
            } else {

                view.findNavController()
                    .navigate(HomeFragmentDirections.navigateToGameFragment())
            }
        }
        return binding.root
    }


}


