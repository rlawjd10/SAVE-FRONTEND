package com.umc.save.Locker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.umc.save.Record.ChildRecordActivity
import com.umc.save.databinding.ActivityLockerDeleteChildBinding
import com.umc.save.databinding.ActivityLockerPictureBinding

class ChildDeleteLockerActivity : AppCompatActivity(), ChildrenView {

    var userIdx = 2
    lateinit var binding : ActivityLockerDeleteChildBinding
    var childList = ArrayList<Child>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLockerDeleteChildBinding.inflate(layoutInflater)

        getChildren()

//        childList =

        binding.deleteBtn.setOnClickListener {
//            finish()
        }

        setContentView(binding.root)
    }

    private fun getChildren() {

        val childrenService = ChildrenService()

        childrenService.setChildrenView(this)
        childrenService.getChildren(userIdx)

    }


    private fun initRecyclerView(result : ArrayList<Child>) {
        val childEditRVAdapter = ChildEditRVAdapter(result)
//        requireContext(),result
        binding.childListRv.adapter = childEditRVAdapter
        binding.childListRv.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)

        childEditRVAdapter.setMyItemClickListener(object: ChildEditRVAdapter.MyItemClickListener {
            override fun onItemClick(child: Child) {
                child.isClicked = !child.isClicked
//                changeRecordChildLockerFragment(child)
            }

            override fun onItemClickAdd() {
                openRecordActivity()
            }
        })

    }


    private fun openRecordActivity() {
        val intent = Intent(this, ChildRecordActivity::class.java)
        startActivity(intent)
    }


    override fun onGetChildSuccess(code: Int, result: ArrayList<Child>) {
        initRecyclerView(result)
        Log.d("GET-SUCCESS","요청에 성공하였습니다.")
    }

    override fun childNotExist(code: Int, message : String) {
        Log.d("GET-NOT-EXIST",message)
    }

    override fun onGetChildFailure(code: Int, message : String) {
        Log.d("GET-FAILURE",message)
    }



}
