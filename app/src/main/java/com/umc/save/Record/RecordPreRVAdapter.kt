package com.umc.save.Record

import android.graphics.Color
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.umc.save.Locker.ChildEditRVAdapter
import com.umc.save.Record.Auth.ChildGet.Child
import com.umc.save.Record.Auth.ChildRecord.childidx_var
import com.umc.save.Record.Auth.SuspectRecord.suspectIdx_var
import com.umc.save.databinding.ItemChildEditBinding

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
        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(childList[position])
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = childList.size


    inner class ViewHolder(val binding: ItemChildEditBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(child: Child) {
            val childInfo: String =
                child.childGender + "/" + child.childAge + "/" + child.childAddress

            binding.itemChildInfoNameTv.text = child.childName
            binding.itemChildInfoSpecificTv.text = childInfo
            binding.itemChildInfoIv.setBackgroundColor(Color.parseColor("#F5F5F5"))

            binding.itemChildInfoIv.setOnClickListener {
                child.isSelected = !child.isSelected

                if(child.isSelected) {
                    selectedList_child.add(child)
                    selectedItem_child++
                }
                else {
                    selectedList_child.remove(child)
                    selectedItem_child--
                }

                if (child.isSelected) {
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