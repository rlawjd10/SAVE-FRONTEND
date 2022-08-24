package com.umc.save.Home.option

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.save.databinding.ItemAlarmBinding

class AlarmRVAdapter : RecyclerView.Adapter<AlarmRVAdapter.ViewHolder>() {

    var datalist = mutableListOf<AlarmData>()

    inner class ViewHolder(private val binding: ItemAlarmBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(alarmData: AlarmData) {
            //binding.homeAlarmTitleTv.text = alarmData.title
            binding.homeAlarmDateTv.text = alarmData.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemAlarmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datalist[position])
    }

    override fun getItemCount(): Int = datalist.size
}