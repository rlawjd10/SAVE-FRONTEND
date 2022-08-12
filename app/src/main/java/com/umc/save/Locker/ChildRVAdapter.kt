package com.umc.save.Locker

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.save.R
import com.umc.save.databinding.ItemAddBinding
import com.umc.save.databinding.ItemChildBinding
import java.text.SimpleDateFormat

class ChildRVAdapter (private val childList : ArrayList<Child>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
            R.layout.item_child
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            R.layout.item_child -> {
                val view = ItemChildBinding.inflate(LayoutInflater.from(viewGroup.context),
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
            (holder as ViewHolderChild).itemView.setOnClickListener{  mItemClickListener.onItemClick(childList[position])}
        }

    }

    override fun getItemCount(): Int = childList.size + 1

    inner class ViewHolderChild(val binding: ItemChildBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(child: Child) {

            var childInfo: String

            val gender: String = when (child.childGender) {
                "male" -> "남자"
                "female" -> "여자"
                else -> "성별 모름"
            }

            childInfo = gender + "/" + child.childAge + "/" + child.childAddress

            if(child.childDetailAddress != null) {
                childInfo = childInfo + "/" + child.childDetailAddress
            }

            val sdf = SimpleDateFormat("yyyy.MM.dd")

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
