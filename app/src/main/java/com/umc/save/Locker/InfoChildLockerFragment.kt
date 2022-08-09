package com.umc.save.Locker

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.databinding.FragmentLockerChildExpandBinding
import com.umc.save.databinding.FragmentLockerChildInfoBinding
import java.text.SimpleDateFormat

class InfoChildLockerFragment : Fragment() {
    lateinit var binding : FragmentLockerChildInfoBinding
    private var gson : Gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerChildInfoBinding.inflate(inflater,container,false)

        val childJson = arguments?.getString("child")
        val child = gson.fromJson(childJson,Child::class.java)

        setInit(child)
        setRecord()

        return binding.root
    }


    @SuppressLint("SimpleDateFormat")
    private fun setInit(child: Child){
        val childInfo : String = child.childGender + "/" + child.childAge.toString() + "/" + child.childAddress
        val sdf = SimpleDateFormat("yyyy.MM.dd")

        binding.childInfoNameTv.text = child.childName
        binding.childInfoSpecificTv.text = childInfo
//        binding.childInfoDateTv.text = sdf.format(child.createAt).toString()

    }

    private fun setRecord(){
        (context as MainActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.locker_frm, RecordChildLockerFragment())
            .commitAllowingStateLoss()
    }

}