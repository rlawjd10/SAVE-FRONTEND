package com.umc.save.Record.RecordDetail

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
import com.umc.save.R
import com.umc.save.Record.Auth.ChildGet.ChildGetService
import com.umc.save.Record.Auth.ChildRecord.childidx_var
import com.umc.save.Record.Auth.SuspectGet.Suspect
import com.umc.save.Record.Auth.SuspectGet.SuspectGetResult
import com.umc.save.Record.Auth.SuspectGet.SuspectGetService
import com.umc.save.Record.Auth.SuspectRecord.suspectIdx_var
import com.umc.save.Record.SuspectRVAdapter
import com.umc.save.Record.selectedItem_child
import com.umc.save.Record.selectedList_child
import com.umc.save.databinding.ActivityChooseOffenderBinding
import java.util.*
import kotlin.collections.ArrayList

var selectedList_suspect = ArrayList<Suspect>()
var selectedItem_suspect = 0

class ChooseOffenderActivity : AppCompatActivity(), SuspectGetResult {
    lateinit var binding : ActivityChooseOffenderBinding
    private var suspectList= ArrayList<Suspect>()
    var emptyList = ArrayList<Suspect>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseOffenderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        chooseSuspect()

        binding.nextBtn.setOnClickListener{
            Log.d("nextBtn  selectedItem ==================", selectedItem_suspect.toString())
            if(selectedItem_suspect == 0) {
                Toast.makeText(this,"학대 행위자를 선택해주세요.", Toast.LENGTH_SHORT).show()
            } else if (selectedItem_suspect > 1) {
                Toast.makeText(this,"학대 행위자는 한 명만 선택해주세요.", Toast.LENGTH_SHORT).show()
            } else {

                suspectIdx_var.suspectIdx.suspectIdx = selectedList_suspect[0].suspectIdx
                selectedItem_suspect = 0 // 다시 초기화
                selectedList_suspect = emptyList
                startActivity(Intent(this, AbuseTypeActivity::class.java))
            }


        }
    }

    private fun chooseSuspect(){
        val suspectGetService = SuspectGetService()
        suspectGetService.setSuspectGetResult(this)
        suspectGetService.getSuspect(childidx_var.childIdx.childIdx)
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

    override fun getSuspectSuccess(
        code: Int,
        result: java.util.ArrayList<Suspect>
    ) {
        suspectList.addAll(result)

        val suspectRVAdapter = SuspectRVAdapter(suspectList)
        binding.offenderListRv.adapter = suspectRVAdapter
        binding.offenderListRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        suspectRVAdapter.setMyItemClickListener(object:SuspectRVAdapter.MyItemClickListener{
            @SuppressLint("NotifyDataSetChanged")
            override fun onItemClick(suspect: Suspect) {
                suspect.isSelected = !suspect.isSelected

                Log.d("======deletedChildIdx", selectedList_suspect[0].suspectIdx.toString())

                if(suspect.isSelected) {
                    selectedList_suspect.add(suspect)
                    selectedItem_suspect++
//                    childidx_var.childIdx.childIdx = RecordPreRVAdapter.get_childIdx
                }
                else {
                    selectedList_suspect.remove(suspect)
                    selectedItem_suspect--
                }

                Log.d("======deletedChildIdx", selectedList_suspect[0].suspectIdx.toString())
            }
        } )
    }

    override fun getSuspectFailure(code: Int, message: String) {
    }
}
