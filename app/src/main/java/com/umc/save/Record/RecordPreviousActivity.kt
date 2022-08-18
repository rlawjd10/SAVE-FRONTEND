package com.umc.save.Record

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.save.Record.Auth.ChildGet.Child
import com.umc.save.R
import com.umc.save.Record.Auth.ChildGet.ChildGetResult
import com.umc.save.Record.Auth.ChildGet.ChildGetService
import com.umc.save.Record.Auth.ChildRecord.childidx_var
import com.umc.save.Record.RecordDetail.ChooseOffenderActivity
import com.umc.save.Record.RecordDetail.selectedItem_suspect
import com.umc.save.Record.RecordDetail.selectedList_suspect
import com.umc.save.Sign.Auth.userIdx_var
import com.umc.save.databinding.ActivityRecordPreviousBinding
import java.util.*
import kotlin.collections.ArrayList


var selectedList_child = ArrayList<Child>()
var selectedItem_child = 0


class RecordPreviousActivity : AppCompatActivity(), ChildGetResult {
    lateinit var binding: ActivityRecordPreviousBinding

    private var childList= ArrayList<Child>()
    var emptyList = ArrayList<Child>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordPreviousBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        getPreviousChild()

        binding.nextBtn.setOnClickListener {

            if(selectedItem_child == 0) {
                Toast.makeText(this,"아이를 선택해주세요.", Toast.LENGTH_SHORT).show()
            } else if (selectedItem_child > 1) {
                Toast.makeText(this,"아이를 한 명만 선택해주세요.", Toast.LENGTH_SHORT).show()
            } else {
//                Log.d("======deletedChildIdx", selectedList[0].childIdx.toString())
                childidx_var.childIdx.childIdx = selectedList_child[0].childIdx
                selectedItem_child = 0 // 다시 초기화
                selectedList_child = emptyList
                startActivity(Intent(this, ChooseOffenderActivity::class.java))
            }

        }

    }

    private fun getPreviousChild(){
        val childGetService = ChildGetService()
        childGetService.setChildGetResult(this)
        childGetService.getChild(userIdx_var.UserIdx.UserIdx)
    }

    fun initActionBar() {
        val appBartext = findViewById<TextView>(R.id.appbar_page_name_tv)
        val appBarBtn = findViewById<ImageView>(R.id.appbar_back_btn)
        val appBarComplete = findViewById<TextView>(R.id.appbar_complete_tv)

        appBartext.text= "기록"
        appBartext.visibility= View.VISIBLE
        appBarComplete.text= "완료"
        appBarComplete.visibility= View.INVISIBLE
        appBarBtn.setOnClickListener{onBackPressed()}
    }



    override fun getChildSuccess(
        code: Int,
        result: ArrayList<Child>
    ) {
        childList.addAll(result)


        val RecordPreRVAdapter = RecordPreRVAdapter(childList)

        binding.childListRv.adapter = RecordPreRVAdapter
        binding.childListRv.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)

        RecordPreRVAdapter.setMyItemClickListener(object: RecordPreRVAdapter.MyItemClickListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onItemClick(child: Child) {
                child.isSelected = !child.isSelected

                Log.d("======deletedChildIdx", selectedList_child[0].childIdx.toString())

                if(child.isSelected) {
                    selectedList_child.add(child)
                    selectedItem_child++
//                    childidx_var.childIdx.childIdx = RecordPreRVAdapter.get_childIdx
                }
                else {
                    selectedList_child.remove(child)
                    selectedItem_child--
                }

                Log.d("======deletedChildIdx", selectedList_child[0].childIdx.toString())
            }
        })
        Toast.makeText(this, "아동 불러오기 성공", Toast.LENGTH_SHORT).show()
    }

    override fun getChildFailure(code: Int, message: String) {
        TODO("Not yet implemented")
    }
}