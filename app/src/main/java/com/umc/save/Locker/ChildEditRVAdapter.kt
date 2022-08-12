package com.umc.save.Locker

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.umc.save.R
import com.umc.save.databinding.ItemAddBinding
import com.umc.save.databinding.ItemChildBinding
import com.umc.save.databinding.ItemChildEditBinding
import java.text.SimpleDateFormat

class ChildEditRVAdapter (val childList : ArrayList<Child>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    val context: Context,

    interface MyItemClickListener {
        fun onItemClick(child: Child)
        fun onItemClickAdd()
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener : MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == childList.size) {
            R.layout.item_add
        } else {
            R.layout.item_child_edit
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            R.layout.item_child_edit -> {
                val view = ItemChildEditBinding.inflate(LayoutInflater.from(viewGroup.context),
                    viewGroup, false)
                ViewHolderChild(view)
            }
            else -> {
                val view = ItemAddBinding.inflate(LayoutInflater.from(viewGroup.context),
                    viewGroup, false)
                ViewHolderAdd(view)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (position == childList.size) {
            (holder as ViewHolderAdd).bind()
            (holder as ViewHolderAdd).itemView.setOnClickListener{  mItemClickListener.onItemClickAdd()}
            } else {
            (holder as ViewHolderChild).bind(childList[position])
            (holder as ViewHolderChild).itemView.setOnClickListener{
                mItemClickListener.onItemClick(childList[position])
                notifyItemChanged(position)

//                if(childList[position].isClicked) {
//                    holder.itemView.findViewById<ImageView>(R.id.item_child_info_iv).setBackgroundColor(Color.parseColor("#FF7F61"))
//                    holder.itemView.findViewById<TextView>(R.id.item_child_info_name_tv).setTextColor(Color.parseColor("#FFFFFF"))
//                    holder.itemView.findViewById<TextView>(R.id.item_child_info_date_tv).setTextColor(Color.parseColor("#FFFFFF"))
//                } else {
//                    holder.itemView.findViewById<ImageView>(R.id.item_child_info_iv).setBackgroundColor(Color.parseColor("#F5F5F5"))
//                    holder.itemView.findViewById<TextView>(R.id.item_child_info_name_tv).setTextColor(Color.parseColor("#FF7F61"))
//                    holder.itemView.findViewById<TextView>(R.id.item_child_info_date_tv).setTextColor(Color.parseColor("#FF7F61"))
//                }

            }
        }

    }

    override fun getItemCount(): Int = childList.size + 1

    inner class ViewHolderChild(val binding: ItemChildEditBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(child: Child) {
            val childInfo : String = child.childGender + "/" + child.childAge.toString() + "/" + child.childAddress
            val sdf = SimpleDateFormat("yyyy.MM.dd")
//            val sdf = SimpleDateFormat("yyyy년\nMM월 dd일")



            if(child.isClicked) {
                binding.itemChildInfoIv.setBackgroundColor(Color.parseColor("#FF7F61"))
                binding.itemChildInfoNameTv.setTextColor(Color.parseColor("#FFFFFF"))
                binding.itemChildInfoDateTv.setTextColor(Color.parseColor("#FFFFFF"))
            } else {
                binding.itemChildInfoIv.setBackgroundColor(Color.parseColor("#F5F5F5"))
                binding.itemChildInfoNameTv.setTextColor(Color.parseColor("#FF7F61"))
                binding.itemChildInfoDateTv.setTextColor(Color.parseColor("#FF7F61"))
            }


            binding.itemChildInfoNameTv.text = child.childName
            binding.itemChildInfoSpecificTv.text = childInfo
            binding.itemChildInfoDateTv.text = sdf.format(child.createAt).toString()
        }
    }

    inner class ViewHolderAdd(val binding: ItemAddBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }

}
