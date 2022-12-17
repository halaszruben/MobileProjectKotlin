package com.example.worldofwarcraftquiz.end

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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

    private lateinit var recyclerView: RecyclerView
    private lateinit var arrayList: ArrayList<Hero>
    lateinit var imgID: Array<Int>
    lateinit var heading: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentEndBinding>(
            inflater,
            R.layout.fragment_end, container, false
        )


        imgID = arrayOf(
            R.drawable.voljin,
            R.drawable.kicsivarian
        )

        heading = arrayOf(
            "Vol'Jin",
            "Varian"
        )

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        arrayList = arrayListOf<Hero>()
        getHeroData()


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


        return binding.root
    }

    private fun getHeroData() {

        for (i in imgID.indices) {
            val heroes = Hero(imgID[i], heading[i])
            arrayList.add(heroes)
        }

        recyclerView.adapter = MyAdapter(arrayList)

    }


}