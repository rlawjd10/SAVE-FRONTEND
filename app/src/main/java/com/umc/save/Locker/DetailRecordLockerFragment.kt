package com.umc.save.Locker

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.gson.Gson
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.databinding.FragmentLockerRecordDetailBinding
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList

class DetailRecordLockerFragment : Fragment(),AbuseDetailView {
    lateinit var binding : FragmentLockerRecordDetailBinding
    private var gson : Gson = Gson()
//    lateinit var recordDetail : RecordDetailData
//    lateinit var recording : Recording
    private var pictureList = ArrayList<Picture>()
    private var videoList = ArrayList<Video>()
    private var recordingList = ArrayList<Recording>()

//    var url = "https://dby56rj67jahx.cloudfront.net/recording/192470e0-2fcd-49d1-b2a0-6105b864a930.m4a"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerRecordDetailBinding.inflate(inflater,container,false)

        val abuseIdx = arguments?.getInt("abuseIdx")

        getDetail(abuseIdx!!)


//        recordingList.apply {
//            add(Recording("https://dby56rj67jahx.cloudfront.net/recording/192470e0-2fcd-49d1-b2a0-6105b864a930.m4a","음성녹음1",false))
//            add(Recording("https://dby56rj67jahx.cloudfront.net/recording/958e5d70-91d2-402e-8a0e-f1fe49d45683.mp3","음성녹음2",false))
//            add(Recording("https://dby56rj67jahx.cloudfront.net/recording/192470e0-2fcd-49d1-b2a0-6105b864a930.m4a","음성녹음3",false))
//            add(Recording("https://dby56rj67jahx.cloudfront.net/recording/958e5d70-91d2-402e-8a0e-f1fe49d45683.mp3","음성녹음4",false))
//        }


//        recording = Recording(1,"~~~","음성녹음1",10,100,false)
//        record = RecordData(1,Date(2022,7,5), Time(2,3,0),"인천광역시 연수구 송도동",Date(2022,3,2))
//        recordDetail = RecordDetailData("신체학대","아파트 복도에서 목격했음","엄마와 아빠 둘다 아이에게 학대를 가하는 것 같음")
//        suspect = Suspect(1,"홍길동","여",
//            "32","","","모")



        setOnClickListeners()

        Log.d("abuseIdx",abuseIdx.toString())


        return binding.root
    }


    private fun getDetail(abuseIdx: Int) {
        val abuseDetailService = AbuseDetailService()

        abuseDetailService.setAbuseDetailView(this)
        abuseDetailService.getAbuseDetailCase(abuseIdx)
    }



    private fun setPicture() {
        (context as MainActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.picture_video_frm,ListPictureLockerFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val pictureJson = gson.toJson(pictureList)
                    putString("picture",pictureJson)
                }
            })
            .commitAllowingStateLoss()
    }


    private fun setVideo() {
        (context as MainActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.picture_video_frm,ListVideoLockerFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val videoJson = gson.toJson(videoList)
                    putString("video",videoJson)
                }
            })
            .commitAllowingStateLoss()
    }

    private fun setRecording() {
        val recordingRVAdapter = RecordingRecordRVAdapter(recordingList)
        binding.recordingListRv.adapter = recordingRVAdapter
        binding.recordingListRv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        recordingRVAdapter.setMyItemClickListener(object : RecordingRecordRVAdapter.MyItemClickListener {
            override fun onItemClick(recording: Recording) {
                openRecordingActivity(recording)
            }
        })
    }


    private fun setOnClickListeners() {
        binding.recordVideoNumTv.setOnClickListener {
            setVideo()
        }


        binding.recordPictureNumTv.setOnClickListener {
            setPicture()
        }

    }

    private fun openRecordingActivity(recording: Recording) {
        val intent = Intent(context, RecordingLockerActivity::class.java)
        intent.putExtra("recordingTitle",recording.recordingTitle)
        intent.putExtra("recordingUrl",recording.location)
        startActivity(intent)
    }

    private fun initView(result: RecordDetailData) {
        val unknown = "-"
        val suspectInfo : String = result.suspect.suspectAge + "/" + result.suspect.suspectAge
        if (result.suspect.suspectName == "") {
            binding.suspectInfoNameTv.text = unknown
        } else {
            binding.suspectInfoNameTv.text = result.suspect.suspectName
        }
        binding.suspectInfoRelationshipTv.text = result.suspect.relationship
        binding.suspectInfoSpecificTv.text = suspectInfo

        binding.recordInfoDateTv.text = result.abuseDate
        binding.recordInfoTimeTv.text = result.abuseTime
        binding.recordInfoLocationTv.text = result.abusePlace

        binding.recordAbuseTypeTv.text = result.abuseType
        binding.recordTextTv.text = result.detailDescription
        binding.recordTextEtcTv.text = result.detailEtcDescription

        if (result.detailEtcDescription=="") {
            binding.recordTextEtcTv.visibility = View.GONE
        } else {
            binding.recordTextEtcTv.visibility = View.VISIBLE
        }

    }


    override fun onGetAbuseDetailSuccess(code: Int, result: RecordDetailData) {
        initView(result)
        Log.d("=====DETAIL",result.toString())

        pictureList = result.pictureList
        videoList = result.videoList
        recordingList = result.recordingList

        if (result.videoList.isEmpty()) {
            binding.recordVideoIv.visibility = View.GONE
            binding.recordVideoNumTv.visibility = View.GONE
        } else {
            binding.recordVideoIv.visibility = View.VISIBLE
            binding.recordVideoNumTv.visibility = View.VISIBLE
            binding.recordVideoNumTv.text = result.videoList.size.toString()
            setVideo()
        }


        if (result.pictureList.isEmpty()) {
            binding.recordPictureIv.visibility = View.GONE
            binding.recordPictureNumTv.visibility = View.GONE
        } else {
            binding.recordPictureIv.visibility = View.VISIBLE
            binding.recordPictureNumTv.visibility = View.VISIBLE
            binding.recordPictureNumTv.text = result.pictureList.size.toString()
            setPicture()
        }


        if (result.recordingList.isEmpty()) {
            binding.recordRecordingIv.visibility = View.GONE
            binding.recordRecordingNumTv.visibility = View.GONE
        } else {
            binding.recordRecordingIv.visibility = View.VISIBLE
            binding.recordRecordingNumTv.visibility = View.VISIBLE
            binding.recordRecordingNumTv.text = result.recordingList.size.toString()
            setRecording()
        }

        Log.d("GET-SUCCESS","요청에 성공하였습니다.")
    }

    override fun abuseDetailNotExist(code: Int, message: String) {
        Log.d("GET-NOT-EXIST",message)
    }

    override fun onGetAbuseDetailFailure(code: Int, message: String) {
        Log.d("GET-FAILURE",message)
    }


}






