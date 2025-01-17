package com.umc.save.Locker.Suspect

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.umc.save.Home.option.HomeAlarmFragment
import com.umc.save.Home.option.HomeSettingsFragment
import com.umc.save.Locker.DeleteSuspect.SuspectDeleteLockerActivity
import com.umc.save.Locker.data.Child
import com.umc.save.Locker.data.Suspect
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.Record.Auth.ChildRecord.childidx_var
import com.umc.save.Record.OffenderRecordActivity
import com.umc.save.databinding.FragmentLockerSuspectListBinding
import kotlin.collections.ArrayList
import kotlin.Int as Int

class ListSuspectLockerFragment : Fragment(), SuspectsView {

    lateinit var binding: FragmentLockerSuspectListBinding
    var currentPosition = 0
    private var suspectList= ArrayList<Suspect>()
    private var gson : Gson = Gson()
    lateinit var child : Child

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerSuspectListBinding.inflate(inflater,container,false)

        val childJson = arguments?.getString("child")
        child = gson.fromJson(childJson, Child::class.java)

        initActionBar()

        binding.suspectEditTv.setOnClickListener {
            openDeleteActivity(child.childIdx)
        }

        return binding.root
    }


    override fun onResume() {
        super.onResume()

        getSuspects(child.childIdx)
    }


    private fun initActionBar() {

        binding.mainActionbar.appbarPageNameTv.text = "보관함"

        binding.mainActionbar.appbarBackBtn.setOnClickListener {
            (context as MainActivity).supportFragmentManager
                .popBackStack()
        }
        binding.mainActionbar.actionMainAlarmIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeAlarmFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
        binding.mainActionbar.actionMainSettingsIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeSettingsFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
    }

    private fun openDeleteActivity(childIdx: Int) {
        val intent = Intent(context, SuspectDeleteLockerActivity::class.java)
        intent.putExtra("childIdx",childIdx)
        startActivity(intent)
    }


    private fun openRecordActivity() {
        //학대 행위자 등록할 때 childIdx 필요하니까 전달하기
        childidx_var.childIdx.childIdx = child.childIdx

        Log.d("CHILDINDEX",childidx_var.childIdx.childIdx.toString())

        val intent = Intent(context, OffenderRecordActivity::class.java)
        startActivity(intent)
    }

    private fun getSuspects(childIdx : Int) {

        val suspectsService = SuspectsService()

        suspectsService.setSuspectsView(this)
        suspectsService.getSuspects(childIdx)

    }


    private fun initRecyclerView(result : ArrayList<Suspect>) {
        val suspectRVAdapter = SuspectRVAdapter(result)
        binding.suspectListRv.adapter = suspectRVAdapter
        binding.suspectListRv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        suspectRVAdapter.setMyItemClickListener(object: SuspectRVAdapter.MyItemClickListener {
            override fun onItemClick(suspect: Suspect) {

            }

            override fun onItemClickAdd() {
                openRecordActivity()
            }
        })

    }

    override fun onGetSuspectsSuccess(code: Int, result: ArrayList<Suspect>) {
        initRecyclerView(result)


        Log.d("GET-SUCCESS",result.toString())
    }

    override fun suspectNotExist(code: Int, message: String) {
        Log.d("GET-NOT-EXIST",message)
    }

    override fun onGetSuspectsFailure(code: Int, message: String) {
        Log.d("GET-FAILURE",message)
    }
}
