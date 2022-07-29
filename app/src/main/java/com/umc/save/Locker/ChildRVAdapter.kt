package com.umc.save.Locker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.save.databinding.ItemChildBinding
import java.text.SimpleDateFormat

class ChildRVAdapter (private val childList : ArrayList<Child>) : RecyclerView.Adapter<ChildRVAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(child: Child)
//        fun onChangeClick(child: Child)
    }
    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener : MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemChildBinding = ItemChildBinding.inflate(LayoutInflater.from(viewGroup.context),
                viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(childList[position])
        holder.itemView.setOnClickListener{  mItemClickListener.onItemClick(childList[position])}
    }

    override fun getItemCount(): Int = childList.size

    inner class ViewHolder(val binding: ItemChildBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(child: Child) {
            val childInfo : String = child.childGender + "/" + child.childAge.toString() + "/" + child.childAddress
            val sdf = SimpleDateFormat("yyyy.MM.dd")

            binding.itemChildInfoNameTv.text = child.childName
            binding.itemChildInfoSpecificTv.text = childInfo
            binding.itemChildInfoDateTv.text = sdf.format(child.createAt).toString()
        }
    }
}
