package com.umc.save.Locker

import android.content.Intent
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
            add(Video(1,"test_video","icn_add_default",R.drawable.fragment_child_blue_background,R.raw.test_video))
            add(Video(2,"test_video2","icn_add_default",R.drawable.fragment_child_red_on_background,R.raw.test_video2))
            add(Video(3,"test_video","icn_close_normal",R.drawable.fragment_dark_red_background,R.raw.test_video))
            add(Video(4,"test_video2","icn_close_normal",R.drawable.fragment_dark_gray_background,R.raw.test_video2))
            add(Video(5,"test_video","icn_close_normal",R.drawable.fragment_offender_off_background,R.raw.test_video2))
        }

        val videoRecordRVAdapter = VideoRecordRVAdapter(videoList)

        binding.videoListRv.adapter = videoRecordRVAdapter
        binding.videoListRv.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL,false)

        videoRecordRVAdapter.setMyItemClickListener(object: VideoRecordRVAdapter.MyItemClickListener {
            override fun onItemClick(video: Video) {
               openPlayerActivity(video)
            }
        })

        return binding.root
    }

    private fun openPlayerActivity(video: Video) {

        val intent = Intent(context, PictureLockerActivity::class.java)
        intent.putExtra("video",video.video)
        //다음에 picture.location으로 바꿔놓기
        startActivity(intent)
    }



    private fun getVideoList() {

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
