package com.umc.save.Locker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.Record.ChildRecordActivity
import com.umc.save.databinding.FragmentLockerBinding
import com.umc.save.getRetrofit
import retrofit2.Callback
import retrofit2.adapter.rxjava2.Result.response
import java.util.*
import kotlin.collections.ArrayList

class LockerFragment : Fragment() {
//    , ChildrenView

    var userIdx = 1
    lateinit var binding: FragmentLockerBinding
    var currentPosition = 0
    private var childList= ArrayList<Child>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerBinding.inflate(inflater,container,false)

        childList.apply {
            add(Child(1,"양현진",true,
                "여","10","인천광역시 연수구 송도동",
                "1000-1202", Date(2022-1900,1,2)))
            add(Child(2,"울랄라",false,
                "여","10","서울시 광진구",
                "902-1002", Date(2022-1900,1,4)))
            add(Child(3,"양땡땡",false,
                "여","10","부산",
                "1000-1202", Date(2021-1900,2,3)))
            add(Child(1,"양현진",true,
                "여","10","인천광역시 연수구 송도동",
                "1000-1202", Date(2022-1900,1,2)))
            add(Child(2,"울랄라",false,
                "여","10","서울시 광진구",
                "902-1002", Date(2022-1900,1,4)))
            add(Child(3,"양땡땡",false,
                "여","10","부산",
                "1000-1202", Date(2021-1900,2,3)))
        }

        val childRVAdapter = ChildRVAdapter(childList)
        binding.childListRv.adapter = childRVAdapter
        binding.childListRv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        childRVAdapter.setMyItemClickListener(object: ChildRVAdapter.MyItemClickListener {
            override fun onItemClick(child: Child) {
                changeRecordChildLockerFragment(child)
            }

            override fun onItemClickAdd() {
                openRecordActivity()
            }
        })

        Log.d("childList",childList.toString())

        return binding.root
    }

    override fun onStart() {
        super.onStart()
//        getChildren()
    }

//    private fun getChildren() {
//
//        val childrenService = ChildrenService()
//
//        childrenService.setChildrenView(this)
//        childrenService.getChildren(userIdx)
//
//    }


//    private fun initRecyclerView(result : ArrayList<Child>) {
//        val childRVAdapter = ChildRVAdapter(requireContext(),result)
//        binding.childListRv.adapter = childRVAdapter
//        binding.childListRv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
//
//        childRVAdapter.setMyItemClickListener(object: ChildRVAdapter.MyItemClickListener {
//            override fun onItemClick(child: Child) {
//                changeRecordChildLockerFragment(child)
//            }
//
//            override fun onItemClickAdd() {
//                openRecordActivity()
//            }
//        })
//
//    }

    private fun changeRecordChildLockerFragment(child: Child) {
        (context as MainActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frm, InfoChildLockerFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val childJson = gson.toJson(child)
                    putString("child",childJson)
                }
            })
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    private fun openRecordActivity() {
        val intent = Intent(context, ChildRecordActivity::class.java)
        startActivity(intent)
    }

//    override fun onGetChildSuccess(code: Int, result: ArrayList<Child>) {
//        initRecyclerView(result)
//        Log.d("GET-SUCCESS","요청에 성공하였습니다.")
//    }
//
//    override fun childNotExist(code: Int, message : String) {
//        Log.d("GET-NOT_EXIST",message)
//    }
//
//    override fun onGetChildFailure(code: Int, message : String) {
//        Log.d("GET-FAILURE",message)
//    }


}
