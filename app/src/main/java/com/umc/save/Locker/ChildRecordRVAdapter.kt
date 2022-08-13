package com.umc.save.Locker

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.umc.save.R
import com.umc.save.databinding.ItemAddBinding
import com.umc.save.databinding.ItemAddRecordBinding
import com.umc.save.databinding.ItemChildBinding
import com.umc.save.databinding.ItemRecordBinding
import java.text.SimpleDateFormat

class ChildRecordRVAdapter (private val recordList : ArrayList<RecordData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(recordData: RecordData)
        fun onItemClickAdd()
    }
    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener : MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == recordList.size) {
            R.layout.item_add_record
        } else {
            R.layout.item_record
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            R.layout.item_record -> {
                val view = ItemRecordBinding.inflate(LayoutInflater.from(viewGroup.context),
                    viewGroup, false)
                ViewHolderRecord(view)
            }
            else -> {
                val view = ItemAddRecordBinding.inflate(LayoutInflater.from(viewGroup.context),
                    viewGroup, false)
                ViewHolderAdd(view)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (position == recordList.size) {
            (holder as ChildRecordRVAdapter.ViewHolderAdd).bind()
            (holder as ChildRecordRVAdapter.ViewHolderAdd).itemView.setOnClickListener{  mItemClickListener.onItemClickAdd() }
        } else {
            (holder as ChildRecordRVAdapter.ViewHolderRecord).bind(recordList[position])
            (holder as ChildRecordRVAdapter.ViewHolderRecord).itemView.setOnClickListener{  mItemClickListener.onItemClick(recordList[position])}
        }
    }

    override fun getItemCount(): Int = recordList.size + 1

    inner class ViewHolderRecord(val binding: ItemRecordBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(record: RecordData) {
            @SuppressLint("SimpleDateFormat")
            val sdf = SimpleDateFormat("yyyy.MM.dd")

            binding.itemRecordDateTv.text = record.abuseDate
            binding.itemRecordTimeTv.text = record.abuseTime
            binding.itemRecordLocationTv.text = record.abusePlace
            binding.itemCreateDateTv.text = sdf.format(record.createAt).toString()

        }
    }

    inner class ViewHolderAdd(val binding: ItemAddRecordBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }


}
