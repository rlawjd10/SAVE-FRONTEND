package com.umc.save.Locker

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.umc.save.databinding.FragmentLockerRecordDetailBinding
import java.text.SimpleDateFormat

class DetailRecordLockerFragment : Fragment() {
    lateinit var binding : FragmentLockerRecordDetailBinding
    private var gson : Gson = Gson()
//    var currentPosition = 0
    private var recordList = ArrayList<RecordData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerRecordDetailBinding.inflate(inflater,container,false)

        val childJson = arguments?.getString("child")
        val child = gson.fromJson(childJson,Child::class.java)

//        setInit(child)

        return binding.root
    }

//    @SuppressLint("SimpleDateFormat")
//    private fun setInit(child: Child){
//        val childInfo : String = child.childGender + "/" + child.childAge.toString() + "/" + child.childAddress
//        val sdf = SimpleDateFormat("yyyy.MM.dd")
//
//        binding.childInfoNameTv.text = child.childName
//        binding.childInfoSpecificTv.text = childInfo
//
//    }

}