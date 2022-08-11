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
import com.umc.save.Record.ChildRecordActivity
import com.umc.save.databinding.FragmentLockerBinding
import kotlin.collections.ArrayList

class LockerFragment : Fragment(), ChildrenView {

    var userIdx = 2
    lateinit var binding: FragmentLockerBinding
    var currentPosition = 0
    var childList = ArrayList<Child>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerBinding.inflate(inflater,container,false)

        getChildren()

        binding.lockerChildEditTv.setOnClickListener {
            openDeleteActivity()
        }

    //TEST용 (지우기)
        binding.lockerTitleTv.setOnClickListener {
            openRecordingActivity()
//            (context as MainActivity).supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.main_frm, TestRecordingFragment())
//                .addToBackStack(null)
//                .commitAllowingStateLoss()
        }


        return binding.root
    }

    //TEST용 (지우기)
    private fun openRecordingActivity() {

        val intent = Intent(context, RecordingLockerActivity::class.java)
//        intent.putExtra("recording",recording.location)
        //다음에 picture.location으로 바꿔놓기
        startActivity(intent)
    }


    private fun getChildren() {

        val childrenService = ChildrenService()

        childrenService.setChildrenView(this)
        childrenService.getChildren(userIdx)

    }

    private fun initRecyclerView(result : ArrayList<Child>) {
        val childRVAdapter = ChildRVAdapter(result)
//        requireContext(),result
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

    }

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

    //delete에 arraylist 넘길지 check
    private fun openDeleteActivity() {
        val intent = Intent(context, ChildDeleteLockerActivity::class.java)
//        intent.putExtra("childList",childList)
        startActivity(intent)
    }


    override fun onGetChildSuccess(code: Int, result: ArrayList<Child>) {
        initRecyclerView(result)
        childList = result
        Log.d("GET-SUCCESS","요청에 성공하였습니다.")
        Log.d("check recieve", result.toString())
    }

    override fun childNotExist(code: Int, message : String) {
        Log.d("GET-NOT-EXIST",message)
    }

    override fun onGetChildFailure(code: Int, message : String) {
        Log.d("GET-FAILURE",message)
    }


}
