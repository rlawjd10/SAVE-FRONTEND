package com.umc.save.Locker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.umc.save.Home.option.HomeDialogFragment
import com.umc.save.R
import com.umc.save.Record.Auth.ChildRecord.childidx_var
import com.umc.save.Record.ChildRecordActivity
import com.umc.save.Record.OffenderRecordActivity
import com.umc.save.databinding.ActivityLockerDeleteChildBinding
import com.umc.save.databinding.ActivityLockerDeleteSuspectBinding
import com.umc.save.databinding.ActivityLockerPictureBinding
import kotlin.properties.Delegates

class SuspectDeleteLockerActivity : AppCompatActivity(), SuspectsView, DeleteSuspectView {


    lateinit var binding : ActivityLockerDeleteSuspectBinding
    private var selectedItem = 0
    var selectedList = ArrayList<Suspect>()
    private var gson : Gson = Gson()
    private var childIdx : Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLockerDeleteSuspectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        childIdx = intent.getIntExtra("childIdx",-1)

        getSuspects(childIdx)

        binding.deleteBtn.setOnClickListener {

            if(selectedItem == 0) {
                Toast.makeText(this,"학대 행위자를 선택해주세요.", Toast.LENGTH_SHORT).show()
            } else if (selectedItem > 1) {
                Toast.makeText(this,"학대 행위자를 한 명만 선택해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                //해당 아동 삭제하기
                ClickViewEvents(selectedList[0].suspectIdx)

                Log.d("======deletedSuspectIdx", selectedList[0].suspectIdx.toString())
            }

        }


    }


    //setContentView(binding.root) 아래에서 initActionBar() 불러야 함
    fun initActionBar() {
        val appBartext = findViewById<TextView>(R.id.appbar_page_name_tv)
        val appBarBtn = findViewById<ImageView>(R.id.appbar_back_btn)
        val appBarComplete = findViewById<TextView>(R.id.appbar_complete_tv)

        appBartext.text= "편집"
        appBartext.visibility= View.VISIBLE
        appBarComplete.text= "완료"
        appBarComplete.visibility= View.VISIBLE
        appBarBtn.setOnClickListener{ onBackPressed() }
        appBarComplete.setOnClickListener{ finish() }
    }

    private fun ClickViewEvents(deleteSuspectIdx : Int) {

        val dialog = HomeDialogFragment()


        dialog.arguments = bundleOf(
            "bodyContext" to "보관함에서 학대 행위자 정보를\n 삭제하시겠습니까?",
            "btnOk" to "확인",
            "btnCancel" to "취소"
        )
        dialog.setButtonClickListener(object: HomeDialogFragment.onButtonClickListerner {
            override fun onButtonNoClicked() {

            }
            override fun onButtonOkClicked() {
                deleteSuspect(deleteSuspectIdx)
            }
        })
        dialog.show(this.supportFragmentManager, "HomeDialog")

    }


    private fun deleteSuspect(deleteSuspectIdx : Int) {
        val deleteSuspectService = DeleteSuspectService()

        deleteSuspectService.setDeleteSuspectView(this)
        deleteSuspectService.deleteSuspect(deleteSuspectIdx)

        recreate()
    }


    private fun getSuspects(childIdx : Int) {
        val suspectsService = SuspectsService()

        suspectsService.setSuspectsView(this)
        suspectsService.getSuspects(childIdx)

    }


    private fun initRecyclerView(result : ArrayList<Suspect>) {
        val suspectEditRVAdapter = SuspectEditRVAdapter(result)

        binding.suspectListRv.adapter = suspectEditRVAdapter
        binding.suspectListRv.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)

        suspectEditRVAdapter.setMyItemClickListener(object: SuspectEditRVAdapter.MyItemClickListener {
            override fun onItemClick(suspect: Suspect) {
                suspect.isClicked = !suspect.isClicked

                if(suspect.isClicked) {
                    //하나라도 클릭하면
                    selectedList.add(suspect)
                    selectedItem++
                } else {
                    selectedList.remove(suspect)
                    selectedItem--
                }

                Log.d("selectedList", selectedList.toString())
            }

            override fun onItemClickAdd() {
                openRecordActivity()
            }
        })

    }


    private fun openRecordActivity() {
        childidx_var.childIdx.childIdx = childIdx

        val intent = Intent(this, OffenderRecordActivity::class.java)
        startActivity(intent)
    }

    //학대 행위자 삭제 결과
    override fun onDeleteSuspectSuccess(code: Int, result: String) {
        Log.d("DELETE-SUCCESS",result)
    }

    override fun onDeleteSuspectFailure(code: Int, message: String) {
        Log.d("DELETE-FAILURE",message)
    }


    //학대 행위자 리스트 가져오기 결과
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
