package com.example.worldofwarcraftquiz.hero

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.worldofwarcraftquiz.databinding.ListLegendaryNamesBinding

class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private lateinit var binding: ListLegendaryNamesBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        binding =
            ListLegendaryNamesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun setData(item: Hero) {
            binding.apply {
                tvId.text = item.id.toString()
                tvHeroname.text = item.name
                tvRole.text = item.role
            }
        }

    }


    private val differCallback = object : DiffUtil.ItemCallback<Hero>() {
        override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

}

class OnClickListener(val clickListener: (hero: Hero) -> Unit) {
    fun onClick(hero: Hero) = clickListener(hero)
}