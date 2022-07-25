package com.umc.save.Locker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.save.databinding.ItemChildBinding

class ChildRVAdapter (private val childList : ArrayList<Child>) : RecyclerView.Adapter<ChildRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemChildBinding = ItemChildBinding.inflate(LayoutInflater.from(viewGroup.context),
                viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(childList[position])
//        holder.itemView.setOnClickListener{ }
    }

    override fun getItemCount(): Int = childList.size

    inner class ViewHolder(val binding: ItemChildBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(child: Child) {
            var childInfo : String = child.childGender + "/" + child.childAge.toString() + "/" + child.childAddress

            binding.itemChildInfoNameTv.text = child.childName
            binding.itemChildInfoSpecificTv.text = childInfo
            binding.itemChildInfoDateTv.text = child.createAt.toString()
        }
    }
}
