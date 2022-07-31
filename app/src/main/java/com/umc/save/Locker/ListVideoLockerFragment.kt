package com.umc.save.Locker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.databinding.FragmentLockerChildListBinding
import com.umc.save.databinding.FragmentLockerVideoListBinding
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList

class ListVideoLockerFragment() : Fragment() {
    lateinit var binding : FragmentLockerVideoListBinding
    var currentPosition = 0
    private var videoList = ArrayList<Video>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerVideoListBinding.inflate(inflater,container,false)

        videoList.apply {
            add(Video(1,"~~~~~"))
            add(Video(2,"~~~~~"))
            add(Video(3,"~~~~~"))
            add(Video(4,"~~~~~"))
            add(Video(5,"~~~~~"))
            add(Video(6,"~~~~~"))
            add(Video(7,"~~~~~"))
            add(Video(8,"~~~~~"))
        }

//        val videoRecordRVAdapter = VideoRecordRVAdapter(videoList)

//        binding.videoListRv.adapter = videoRecordRVAdapter
//        binding.videoListRv.layoutManager = LinearLayoutManager(context,
//            LinearLayoutManager.HORIZONTAL,false)

//        videoRecordRVAdapter.setMyItemClickListener(object: VideoRecordRVAdapter.MyItemClickListener {
//            override fun onItemClick(recordData: RecordData) {
////                changeDetailRecordLockerFragment(recordData)
//            }
//        })

        return binding.root
    }

//    private fun changeDetailRecordLockerFragment(recordData: RecordData) {
//        (context as MainActivity).supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.locker_frm, DetailRecordLockerFragment().apply {
//                arguments = Bundle().apply {
//                    val gson = Gson()
//                    val recordDataJson = gson.toJson(recordData)
//                    putString("recordData",recordDataJson)
//                }
//            })
//            .addToBackStack(null)
//            .commitAllowingStateLoss()
//
//    }

}
