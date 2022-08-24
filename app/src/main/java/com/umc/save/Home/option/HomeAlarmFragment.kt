package com.umc.save.Home.option

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.ToggleButton
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.umc.save.R
import com.umc.save.databinding.FragmentHomeAlarmBinding
import java.text.SimpleDateFormat
import java.util.*


class HomeAlarmFragment : Fragment() {

    lateinit var binding: FragmentHomeAlarmBinding
    val dataList = mutableListOf<AlarmData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeAlarmBinding.inflate(inflater, container, false)

        val switch: SwitchCompat = binding.alarmCheckSwitch
        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                //스위치 on일 때, 리사이클러뷰 보이도록
                binding.alarmNotificationRv.visibility = View.VISIBLE
            } else {
                //스위치 off일 때, 리사이클러뷰가 보이지 않도록
                binding.alarmNotificationRv.visibility = View.INVISIBLE
            }
        }

        initAlarmRecyclerView()
        return binding.root
    }

    //리사이클러뷰
    fun initAlarmRecyclerView() {
        val adapter = AlarmRVAdapter()

        val currentDateTime = Calendar.getInstance().time
        var dateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA).format(currentDateTime)

        adapter.datalist = dataList.apply {
            add(AlarmData(dateFormat))
        }

        //어댑터 연결
        binding.alarmNotificationRv.adapter = adapter
        binding.alarmNotificationRv.layoutManager = LinearLayoutManager(this.context)

    }
}

