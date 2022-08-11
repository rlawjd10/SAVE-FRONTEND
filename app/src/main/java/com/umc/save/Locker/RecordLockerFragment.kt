package com.umc.save.Locker

import android.os.Bundle
import android.util.Log
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

class RecordLockerFragment(child: Int) : Fragment(), AbuseView {
    lateinit var binding : FragmentLockerChildListBinding
    var currentPosition = 0
    private var recordList = ArrayList<RecordData>()
    var childIdx = child

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerChildListBinding.inflate(inflater,container,false)

//        recordList.apply {
//            add(RecordData(1,Date(2022,7,5), Time(2,3,0),"인천광역시 연수구 송도동",Date(2022,3,2)))
//            add(RecordData(2,Date(2021,4,3), Time(3,3,0),"인천광역시 연수구 동춘동",Date(2021,3,12)))
//        }

        getAbuseCases()

        return binding.root
    }

    private fun getAbuseCases() {

        val abuseService = AbuseService()

        abuseService.setAbuseView(this)
        abuseService.getAbuseCases(childIdx)

    }

    private fun changeDetailRecordLockerFragment(recordData: RecordData) {
        (context as MainActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.locker_frm, DetailRecordLockerFragment().apply {
                arguments = Bundle().apply {

                    val abuseIdx = putInt("abuseIdx",recordData.abuseIdx)

//                    = gson.toJson(recordData.abuseIdx)
//                    val gson = Gson()
//
//                    putString("abuseIdx",abuseIdxJson)
                }
            })
            .addToBackStack(null)
            .commitAllowingStateLoss()

    }

    private fun initRecyclerView(result : ArrayList<RecordData>){
        val childRecordRVAdapter = ChildRecordRVAdapter(result)
        binding.recordListRv.adapter = childRecordRVAdapter
        binding.recordListRv.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL,false)

        childRecordRVAdapter.setMyItemClickListener(object: ChildRecordRVAdapter.MyItemClickListener {
            override fun onItemClick(recordData: RecordData) {
                changeDetailRecordLockerFragment(recordData)
            }
        })
    }


    override fun onGetAbuseSuccess(code: Int, result: ArrayList<RecordData>) {
        initRecyclerView(result)
        Log.d("GET-SUCCESS","요청에 성공하였습니다.")
        Log.d("RECORD-DATA",result.toString())
    }

    override fun abuseNotExist(code: Int, message: String) {
        Log.d("GET-NOT-EXIST",message)
    }

    override fun onGetAbuseFailure(code: Int, message: String) {
        Log.d("GET-FAILURE",message)
    }
}
