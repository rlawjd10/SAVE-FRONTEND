package com.umc.save.Locker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.save.databinding.ItemRecordBinding
import java.text.SimpleDateFormat

class ChildRecordRVAdapter (private val recordList : ArrayList<RecordData>) : RecyclerView.Adapter<ChildRecordRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemRecordBinding = ItemRecordBinding.inflate(LayoutInflater.from(viewGroup.context),
            viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recordList[position])
//        holder.itemView.setOnClickListener{ }
    }

    override fun getItemCount(): Int = recordList.size

    inner class ViewHolder(val binding: ItemRecordBinding) : RecyclerView.ViewHolder(binding.root) {
        val sdf = SimpleDateFormat("yyyy.MM.dd")

        fun bind(record: RecordData) {
            binding.itemRecordDateTv.text = record.abuseDate.toString()
            binding.itemRecordTimeTv.text = record.abuseTime.toString()
            binding.itemRecordLocationTv.text = record.abusePlace
            binding.itemCreateDateTv.text = record.createAt.toString()
        }
    }
}
