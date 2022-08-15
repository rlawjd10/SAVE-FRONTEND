package com.umc.save.Locker

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
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
        setRecord(child)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fab : FloatingActionButton = view.findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:112"))
            startActivity(intent)
        }
    }


    @SuppressLint("SimpleDateFormat")
    private fun setInit(child: Child){

        var childInfo: String
        val gender: String = when (child.childGender) {
            "male" -> "남자"
            "female" -> "여자"
            else -> "성별 모름"
        }

        //이미지 랜덤으로 보여지게 하기 (다시 로딩 되어도 그 아동은 그 이미지로)
        if (gender == "남자") {
            val imageSelect = child.childIdx % 15

            when (imageSelect) {
                0 -> binding.childInfoImage.setImageResource(R.drawable.ilst_boy_05_green)
                1 -> binding.childInfoImage.setImageResource(R.drawable.ilst_boy_01_blue)
                2 -> binding.childInfoImage.setImageResource(R.drawable.ilst_boy_02_red)
                3 -> binding.childInfoImage.setImageResource(R.drawable.ilst_boy_03_green)
                4 -> binding.childInfoImage.setImageResource(R.drawable.ilst_boy_04_blue)
                5 -> binding.childInfoImage.setImageResource(R.drawable.ilst_boy_05_red)
                6 -> binding.childInfoImage.setImageResource(R.drawable.ilst_boy_01_green)
                7 -> binding.childInfoImage.setImageResource(R.drawable.ilst_boy_02_blue)
                8 -> binding.childInfoImage.setImageResource(R.drawable.ilst_boy_03_red)
                9 -> binding.childInfoImage.setImageResource(R.drawable.ilst_boy_04_green)
                10 -> binding.childInfoImage.setImageResource(R.drawable.ilst_boy_05_blue)
                11 -> binding.childInfoImage.setImageResource(R.drawable.ilst_boy_01_red)
                12 -> binding.childInfoImage.setImageResource(R.drawable.ilst_boy_02_green)
                13 -> binding.childInfoImage.setImageResource(R.drawable.ilst_boy_03_blue)
                14 -> binding.childInfoImage.setImageResource(R.drawable.ilst_boy_04_red)
            }


        } else if (gender =="여자") {
            val imageSelect = child.childIdx % 15

            when (imageSelect) {
                0 -> binding.childInfoImage.setImageResource(R.drawable.ilst_girl_05_green)
                1 -> binding.childInfoImage.setImageResource(R.drawable.ilst_girl_01_blue)
                2 -> binding.childInfoImage.setImageResource(R.drawable.ilst_girl_02_red)
                3 -> binding.childInfoImage.setImageResource(R.drawable.ilst_girl_03_green)
                4 -> binding.childInfoImage.setImageResource(R.drawable.ilst_girl_04_blue)
                5 -> binding.childInfoImage.setImageResource(R.drawable.ilst_girl_05_red)
                6 -> binding.childInfoImage.setImageResource(R.drawable.ilst_girl_01_green)
                7 -> binding.childInfoImage.setImageResource(R.drawable.ilst_girl_02_blue)
                8 -> binding.childInfoImage.setImageResource(R.drawable.ilst_girl_03_red)
                9 -> binding.childInfoImage.setImageResource(R.drawable.ilst_girl_04_green)
                10 -> binding.childInfoImage.setImageResource(R.drawable.ilst_girl_05_blue)
                11 -> binding.childInfoImage.setImageResource(R.drawable.ilst_girl_01_red)
                12 -> binding.childInfoImage.setImageResource(R.drawable.ilst_girl_02_green)
                13 -> binding.childInfoImage.setImageResource(R.drawable.ilst_girl_03_blue)
                14 -> binding.childInfoImage.setImageResource(R.drawable.ilst_girl_04_red)
            }

        } else {
            binding.childInfoImage.setImageResource(R.drawable.fragment_white_background)
        }






        childInfo = gender + "/" + child.childAge + "/" + child.childAddress

        if(child.childDetailAddress != null) {
            childInfo = childInfo + "/" + child.childDetailAddress
        }

        val sdf = SimpleDateFormat("yyyy.MM.dd")

        binding.childInfoNameTv.text = child.childName
        binding.childInfoSpecificTv.text = childInfo
        binding.childInfoDateTv.text = sdf.format(child.createAt).toString()

    }

    private fun setRecord(child: Child){
        (context as MainActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.locker_frm, RecordChildLockerFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val childJson = gson.toJson(child)
                    putString("child",childJson)
                }
            })
            .commitAllowingStateLoss()
    }

}