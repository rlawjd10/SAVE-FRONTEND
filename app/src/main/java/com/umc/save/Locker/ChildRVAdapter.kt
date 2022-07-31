package com.umc.save.Locker

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.umc.save.R
import com.umc.save.databinding.ItemAddBinding
import com.umc.save.databinding.ItemAddRecordBinding
import com.umc.save.databinding.ItemChildBinding
import kotlinx.coroutines.NonDisposableHandle.parent
import java.text.SimpleDateFormat

class ChildRVAdapter (private val childList : ArrayList<Child>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(child: Child)
//        fun onChangeClick(child: Child)
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
            } else {
            (holder as ViewHolderChild).bind(childList[position])
            (holder as ViewHolderChild).itemView.setOnClickListener{  mItemClickListener.onItemClick(childList[position])}
        }

    }

    override fun getItemCount(): Int = childList.size + 1

    inner class ViewHolderChild(val binding: ItemChildBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(child: Child) {
            val childInfo : String = child.childGender + "/" + child.childAge.toString() + "/" + child.childAddress
            val sdf = SimpleDateFormat("yyyy.MM.dd")

            binding.itemChildInfoNameTv.text = child.childName
            binding.itemChildInfoSpecificTv.text = childInfo
            binding.itemChildInfoDateTv.text = sdf.format(child.createAt).toString()
        }
    }

    inner class ViewHolderAdd(val binding: ItemAddBinding) : RecyclerView.ViewHolder(binding.root) {
        private val btn = itemView.findViewById<ImageView>(R.id.item_add_image)
        fun bind() {
            btn.setOnClickListener {
                //
            }
        }
    }

}
