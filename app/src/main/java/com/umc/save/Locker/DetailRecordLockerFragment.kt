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

        setInitView()

        return binding.root
    }


    private fun setOnClickListeners() {
        binding.recordVideoNumTv.setOnClickListener {
            (context as MainActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.video_frm,ListPictureLockerFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }

    }

    private fun setInitView() {
        (context as MainActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.picture_frm,ListPictureLockerFragment())
            .commitAllowingStateLoss()
    }


}
