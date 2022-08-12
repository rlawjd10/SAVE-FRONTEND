package com.umc.save.Record

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.umc.save.Record.Auth.ChildGet.Child
import com.umc.save.R
import com.umc.save.databinding.ItemChildEditBinding
import com.umc.save.databinding.ItemOffenderBinding

class RecordPreRVAdapter(private val childList : ArrayList<Child>) :
    RecyclerView.Adapter<RecordPreRVAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(child: Child)
    }
    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener : MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemChildEditBinding = ItemChildEditBinding.inflate(LayoutInflater.from(viewGroup.context),
            viewGroup, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(childList[position])
        holder.itemView.setOnClickListener{
            clicked = !clicked
            holder.bind(childList[position])
        }

    }

    override fun getItemCount(): Int = childList.size


    var clicked = false

    inner class ViewHolder(val binding: ItemChildEditBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(child: Child) {
            val childInfo: String =
                child.childGender + "/" + child.childAge.toString() + "/" + child.childAddress

            binding.itemChildInfoNameTv.text = child.childName
            binding.itemChildInfoSpecificTv.text = childInfo
            binding.itemChildInfoIv.setBackgroundColor(Color.parseColor("#F5F5F5"))

            binding.itemChildInfoIv.setOnClickListener {
                clicked = !clicked
                if (clicked) {
                    binding.itemChildInfoIv.setBackgroundColor(Color.parseColor("#FF7F61"))
                    binding.itemChildInfoNameTv.setTextColor(Color.parseColor("#FFFFFFFF"))
                    binding.itemChildInfoDateTv.setTextColor(Color.parseColor("#FFFFFFFF"))
                } else {
                    binding.itemChildInfoIv.setBackgroundColor(Color.parseColor("#F5F5F5"))
                    binding.itemChildInfoNameTv.setTextColor(Color.parseColor("#FF7F61"))
                    binding.itemChildInfoDateTv.setTextColor(Color.parseColor("#FF7F61"))
                }
            }
        }
    }
}