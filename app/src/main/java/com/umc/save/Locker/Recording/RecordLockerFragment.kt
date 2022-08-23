package com.umc.save.Locker.Recording

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.save.Locker.Abuse.AbuseService
import com.umc.save.Locker.Abuse.AbuseView
import com.umc.save.Locker.DetailRecordLockerFragment
import com.umc.save.Locker.Record.RecordData
import com.umc.save.Locker.Suspect.SuspectsService
import com.umc.save.Locker.Suspect.SuspectsView
import com.umc.save.Locker.Record.ChildRecordRVAdapter
import com.umc.save.Locker.data.Suspect
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.Record.Auth.ChildRecord.childidx_var
import com.umc.save.Record.OffenderRecordActivity
import com.umc.save.Record.RecordDetail.ChooseOffenderActivity
import com.umc.save.databinding.FragmentLockerChildListBinding
import kotlin.collections.ArrayList

class RecordLockerFragment(child: Int) : Fragment(), AbuseView, SuspectsView {
    lateinit var binding : FragmentLockerChildListBinding
    private var recordList = ArrayList<RecordData>()
    private var childIdx = child
    private var suspectEmpty : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerChildListBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        getSuspects()
        getAbuseCases()

    }



    private fun getAbuseCases() {

        val abuseService = AbuseService()

        abuseService.setAbuseView(this)
        abuseService.getAbuseCases(childIdx)

    }

    private fun getSuspects() {

        val suspectsService = SuspectsService()

        suspectsService.setSuspectsView(this)
        suspectsService.getSuspects(childIdx)

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

    private fun openChooseOffenderActivity() {
        //학대 행위자 등록할 때 childIdx 필요하니까 전달하기
        childidx_var.childIdx.childIdx = childIdx

        Log.d("RECORD-FRAGMENT-CHILDINDEX", childidx_var.childIdx.childIdx.toString())

        val intent = Intent(context, ChooseOffenderActivity::class.java)
        startActivity(intent)
    }

    private fun openOffenderRecordActivity() {
        //학대 행위자 등록할 때 childIdx 필요하니까 전달하기
        childidx_var.childIdx.childIdx = childIdx

        Log.d("RECORD-FRAGMENT-CHILDINDEX", childidx_var.childIdx.childIdx.toString())

        val intent = Intent(context, OffenderRecordActivity::class.java)
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
                if(suspectEmpty) {
                    Toast.makeText(context,"등록된 학대 행위자가 없습니다.\n 학대 행위자를 등록해주세요.",Toast.LENGTH_SHORT).show()
                    openOffenderRecordActivity()
                } else {
                    openChooseOffenderActivity()
                }

            }

        })
    }


    //학대 정황 기록 가져오기 (디테일한 것 제외)
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

    //학대 행위자가 없으면 add 버튼 눌렀을 때 학대 행위자 등록으로 이동해야 함
    //학대 행위자 리스트 서버에서 가져오기
    override fun onGetSuspectsSuccess(code: Int, result: ArrayList<Suspect>) {
        suspectEmpty = result.isEmpty()

        Log.d("GET-SUCCESS",result.toString())
    }

    override fun suspectNotExist(code: Int, message: String) {
        Log.d("GET-NOT-EXIST",message)
    }

    override fun onGetSuspectsFailure(code: Int, message: String) {
        Log.d("GET-FAILURE",message)
    }

}
