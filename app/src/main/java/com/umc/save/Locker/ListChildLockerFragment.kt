package com.umc.save.Locker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.databinding.FragmentLockerChildListBinding
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList

class ListChildLockerFragment() : Fragment() {
    lateinit var binding : FragmentLockerChildListBinding
    var currentPosition = 0
    private var recordList = ArrayList<RecordData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerChildListBinding.inflate(inflater,container,false)

        recordList.apply {
            add(RecordData(1,Date(2022,7,5), Time(2,3,0),"인천광역시 연수구 송도동",Date(2022,3,2)))
            add(RecordData(1,Date(2021,4,3), Time(3,3,0),"인천광역시 연수구 동춘동",Date(2021,3,12)))
            add(RecordData(1,Date(2022,7,5), Time(2,3,0),"인천광역시 연수구 송도동",Date(2022,3,2)))
            add(RecordData(1,Date(2022,7,5), Time(2,3,0),"인천광역시 연수구 송도동",Date(2022,3,2)))
            add(RecordData(1,Date(2022,7,5), Time(2,3,0),"인천광역시 연수구 송도동",Date(2022,3,2)))
            add(RecordData(1,Date(2022,7,5), Time(2,3,0),"인천광역시 연수구 송도동",Date(2022,3,2)))
            add(RecordData(1,Date(2022,7,5), Time(2,3,0),"인천광역시 연수구 송도동",Date(2022,3,2)))
        }

        val childRecordRVAdapter = ChildRecordRVAdapter(recordList)
        binding.recordListRv.adapter = childRecordRVAdapter
        binding.recordListRv.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL,false)

        childRecordRVAdapter.setMyItemClickListener(object: ChildRecordRVAdapter.MyItemClickListener {
            override fun onItemClick(recordData: RecordData) {
                changeDetailRecordLockerFragment(recordData)
            }
        })

        return binding.root
    }

    private fun changeDetailRecordLockerFragment(recordData: RecordData) {
        (context as MainActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.locker_frm, DetailRecordLockerFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val recordDataJson = gson.toJson(recordData)
                    putString("recordData",recordDataJson)
                }
            })
            .addToBackStack(null)
            .commitAllowingStateLoss()

    }

}
