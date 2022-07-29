package com.umc.save.Locker

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.AlphabeticIndex
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.databinding.FragmentLockerChildListBinding
import com.umc.save.databinding.FragmentLockerChildRecordBinding
import java.text.SimpleDateFormat

class RecordChildLockerFragment() : Fragment(){
    lateinit var binding: FragmentLockerChildRecordBinding
    private val method  = arrayListOf("목록에서 보기","달력에서 보기")
    private var gson : Gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerChildRecordBinding.inflate(inflater,container,false)

        val childJson = arguments?.getString("child")
        val child = gson.fromJson(childJson,Child::class.java)


        setInit(child)


        val recordAdapter = RecordVPAdapter(this)
        binding.recordContentVp.adapter = recordAdapter
        binding.recordContentVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(binding.recordContentTb,binding.recordContentVp) {
            tab, position -> tab.text = method[position]
        }.attach()




        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    private fun setInit(child: Child){
        val childInfo : String = child.childGender + "/" + child.childAge.toString() + "/" + child.childAddress
        val sdf = SimpleDateFormat("yyyy.MM.dd")

        binding.childInfoNameTv.text = child.childName
        binding.childInfoSpecificTv.text = childInfo
        binding.childInfoDateTv.text = sdf.format(child.createAt).toString()

    }

//    private fun sendData(child: Child) {
//        (context as MainActivity).supportFragmentManager
//            .beginTransaction()
//            .add(R.id.main_frm, ListChildLockerFragment().apply {
//                arguments = Bundle().apply {
//                    val gson = Gson()
//                    val childJson = gson.toJson(child)
//                    putString("child", childJson)
//                }
//            })
//            .addToBackStack(null)
//            .commitAllowingStateLoss()
//    }



}