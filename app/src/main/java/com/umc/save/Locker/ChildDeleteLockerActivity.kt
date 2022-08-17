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
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.Record.Auth.ChildRecord.childidx_var
import com.umc.save.Record.ChildRecordActivity
import com.umc.save.Sign.Auth.userIdx_var
import com.umc.save.databinding.ActivityLockerDeleteChildBinding

class ChildDeleteLockerActivity : AppCompatActivity(), ChildrenView, DeleteChildView {
    //아이들 가져오는 것은 ChildrenView가 한 아이를 지우는 것을 DeleteChildView가 담당

//    var userIdx = 2
    val userIdx = userIdx_var.UserIdx.UserIdx
    lateinit var binding : ActivityLockerDeleteChildBinding
    private var selectedItem = 0
    var selectedList = ArrayList<Child>()

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
                //해당 아동 삭제하기
                ClickViewEvents(selectedList[0].childIdx)
                Log.d("======deletedChildIdx", selectedList[0].childIdx.toString())
            }

        }


    }

    override fun onDeleteChildSuccess(code: Int, result: DeleteChild) {
        Log.d("GET-SUCCESS",result.childDeleteMessage.toString())
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
        appBarComplete.setOnClickListener {
            finish()
        }
    }

    private fun ClickViewEvents(deleteChildIdx : Int) {

        val dialog = HomeDialogFragment()

        dialog.arguments = bundleOf(
            "bodyContext" to "보관함에서 아동 정보를\n 삭제하시겠습니까?",
            "btnOk" to "확인",
            "btnCancel" to "취소"
        )
        dialog.setButtonClickListener(object: HomeDialogFragment.onButtonClickListerner {
            override fun onButtonNoClicked() {

            }
            override fun onButtonOkClicked() {
                deleteChild(deleteChildIdx)

            }
        })
        dialog.show(this.supportFragmentManager, "HomeDialog")

    }


    private fun deleteChild(deleteChildIdx : Int) {
        val deleteChildService = DeleteChildService()
        deleteChildService.setDeleteChildView(this)
        deleteChildService.deleteChild(deleteChildIdx)

        //지우고 나면 삭제되고 아동 리스트가 재로딩이 되야 한다
        recreate()
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
