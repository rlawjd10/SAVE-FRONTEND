package com.umc.save.Locker

import android.content.Intent
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
import com.umc.save.Record.Auth.ChildRecord.childidx_var
import com.umc.save.Record.OffenderRecordActivity
import com.umc.save.Record.RecordDetail.ChooseOffenderActivity
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

                }
            })
            .addToBackStack(null)
            .commitAllowingStateLoss()

    }

    private fun openRecordActivity() {
        //학대 행위자 등록할 때 childIdx 필요하니까 전달하기
        childidx_var.childIdx.childIdx = childIdx

        Log.d("RECORD-FRAGMENT-CHILDINDEX", childidx_var.childIdx.childIdx.toString())

        val intent = Intent(context, ChooseOffenderActivity::class.java)
        startActivity(intent)
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

            override fun onItemClickAdd() {
                openRecordActivity()
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
