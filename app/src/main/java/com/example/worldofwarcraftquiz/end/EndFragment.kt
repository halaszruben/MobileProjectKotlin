package com.example.worldofwarcraftquiz.end

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.worldofwarcraftquiz.R
import com.example.worldofwarcraftquiz.databinding.FragmentEndBinding
import com.example.worldofwarcraftquiz.hero.Hero
import com.example.worldofwarcraftquiz.hero.MyAdapter

class EndFragment : Fragment() {

    private lateinit var viewModel: EndViewModel
    private lateinit var viewModelFactory: EndViewModelFactory

    private val myAdapter by lazy { MyAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentEndBinding>(
            inflater,
            R.layout.fragment_end, container, false
        )

        myAdapter.differ.submitList(loadData())

        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter=myAdapter
            }
        }

        viewModelFactory =
            EndViewModelFactory(EndFragmentArgs.fromBundle(requireArguments()).correctQuestions)
        viewModel = ViewModelProvider(this, viewModelFactory).get(EndViewModel::class.java)
        binding.endViewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.oneMore.observe(viewLifecycleOwner, Observer { again ->
            if (again) {
                findNavController().navigate(EndFragmentDirections.navigateToHomeFragment())
                viewModel.onPlayAgainComplete()
            }
        })

        binding.btnData.setOnClickListener() { view: View ->
            view.findNavController().navigate(EndFragmentDirections.navigateToDb())
        }


        return binding.root
    }


    fun loadData() : MutableList<Hero>{

        val list : MutableList<Hero> = mutableListOf()

        list.add(Hero(1, "Varian Wrynn", "King"))
        list.add(Hero(2, "Vol'jin", "Warchief"))
        list.add(Hero(3, "Varok Saurfang", "Warrior"))
        list.add(Hero(4, "Grand Admiral Daelin Proudmoore", "Admiral"))



        return list

    }




}