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
import com.umc.save.Home.option.HomeDialogFragment
import com.umc.save.R
import com.umc.save.Record.ChildRecordActivity
import com.umc.save.databinding.ActivityLockerDeleteChildBinding

class ChildDeleteLockerActivity : AppCompatActivity(), ChildrenView {


    var userIdx = 2
    lateinit var binding : ActivityLockerDeleteChildBinding
    private var selectedItem = 0
    var selectedList = ArrayList<Child>()
    var childList = ArrayList<Child>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLockerDeleteChildBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()
        getChildren()

        binding.deleteBtn.setOnClickListener {

            if(selectedItem == 0) {
                Toast.makeText(this,"아이를 선택해주세요.", Toast.LENGTH_SHORT).show()
            } else if (selectedItem > 1) {
                Toast.makeText(this,"아이를 한 명만 선택해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                //정상 작동
                //test

                ClickViewEvents()
            }

            Log.d("======selectedItem", selectedItem.toString())


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
        appBarBtn.setOnClickListener{onBackPressed()}
    }

    private fun ClickViewEvents() {

        val dialog = HomeDialogFragment()


        dialog.arguments = bundleOf(
            "bodyContext" to "보관함에서 아동 정보를 삭제하시겠습니까?",
            "btnOk" to "확인",
            "btnCancel" to "취소"
        )
        dialog.setButtonClickListener(object: HomeDialogFragment.onButtonClickListerner {
            override fun onButtonNoClicked() {

            }
            override fun onButtonOkClicked() {
                //삭제하는 작업 childIdx 넣고
            }
        })
        dialog.show(this.supportFragmentManager, "HomeDialog")

    }


    //Action Bar


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

                if(child.isClicked) {
                    //하나라도 클릭하면
                    selectedList.add(child)
                    selectedItem++
                } else {
                    selectedList.remove(child)
                    selectedItem--
                }

                Log.d("selectedList", selectedList.toString())
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
