package com.umc.save.Locker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.common.reflect.TypeToken
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
    val gson : Gson =  Gson()
    private var videoList = ArrayList<Video>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerVideoListBinding.inflate(inflater,container,false)

//        videoList.apply {
//            add(Video("https://save-bucket.s3.ap-northeast-2.amazonaws.com/static/video/a9526453-7daf-4fc6-aae7-cba4eb6f4188.mp4","https://save-bucket.s3.ap-northeast-2.amazonaws.com/static/video/a9526453-7daf-4fc6-aae7-cba4eb6f4188.mp4"))
//            add(Video("https://save-bucket.s3.ap-northeast-2.amazonaws.com/static/video/a9526453-7daf-4fc6-aae7-cba4eb6f4188.mp4","https://save-bucket.s3.ap-northeast-2.amazonaws.com/static/video/a9526453-7daf-4fc6-aae7-cba4eb6f4188.mp4"))
//            add(Video("https://save-bucket.s3.ap-northeast-2.amazonaws.com/static/video/a9526453-7daf-4fc6-aae7-cba4eb6f4188.mp4","https://save-bucket.s3.ap-northeast-2.amazonaws.com/static/video/a9526453-7daf-4fc6-aae7-cba4eb6f4188.mp4"))
//        }

        val videoJson = arguments?.getString("video")
        val itemType = object : TypeToken<ArrayList<Video>>() {}.type
        videoList = gson.fromJson(videoJson,itemType)

        Log.d("======VIDEOELIST",videoList.toString())


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

        val intent = Intent(context, PlayerLockerActivity::class.java)
        intent.putExtra("video",video.location)
        //다음에 picture.location으로 바꿔놓기
        startActivity(intent)
    }



//    private fun getVideoList() {
//
//    }


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
