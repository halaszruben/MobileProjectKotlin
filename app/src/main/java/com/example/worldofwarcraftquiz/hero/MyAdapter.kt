package com.example.worldofwarcraftquiz.hero

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.worldofwarcraftquiz.R
import java.util.ArrayList

class MyAdapter(private val heroList: ArrayList<Hero>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_legendary_names,
        parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = heroList[position]
        holder.image.setImageResource(currentItem.mHeroImg)
        holder.name.text = currentItem.mHeroName
    }

    override fun getItemCount(): Int {

        return heroList.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val image : ImageView = itemView.findViewById(R.id.title_image)
        val name : TextView = itemView.findViewById(R.id.tvHeading)

    }
}