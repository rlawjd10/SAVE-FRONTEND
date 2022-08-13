package com.umc.save.Locker

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.gson.Gson
import com.umc.save.Home.option.HomeDialogFragment
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.databinding.FragmentLockerRecordDetailBinding
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList

class DetailRecordLockerFragment : Fragment(), AbuseDetailView, DeleteRecordView {
    lateinit var binding : FragmentLockerRecordDetailBinding
    private var gson : Gson = Gson()
    private var pictureList = ArrayList<Picture>()
    private var videoList = ArrayList<Video>()
    private var recordingList = ArrayList<Recording>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerRecordDetailBinding.inflate(inflater,container,false)

        val abuseIdx = arguments?.getInt("abuseIdx")

        //서버에서 데이터 받아오기
        getDetail(abuseIdx!!)

        setOnClickListeners()

        binding.deleteBtn.setOnClickListener {
            ClickViewEvents(abuseIdx)
        }

        Log.d("abuseIdx",abuseIdx.toString())


        return binding.root
    }


    private fun ClickViewEvents(deleteAbuseIdx : Int) {

        val dialog = HomeDialogFragment()

        dialog.arguments = bundleOf(
            "bodyContext" to "작성하신 내용이 사라집니다.\n 삭제하시겠습니까?",
            "btnOk" to "확인",
            "btnCancel" to "취소"
        )
        dialog.setButtonClickListener(object: HomeDialogFragment.onButtonClickListerner {
            override fun onButtonNoClicked() {

            }
            override fun onButtonOkClicked() {
                deleteRecord(deleteAbuseIdx)

            }
        })
        dialog.show(this.childFragmentManager, "HomeDialog")

    }



    private fun deleteRecord(abuseIdx: Int) {

        val deleteRecordService = DeleteRecordService()
        deleteRecordService.setDeleteRecordView(this)
        deleteRecordService.deleteRecord(abuseIdx)

        (context as MainActivity).supportFragmentManager
            .popBackStack()
//            .beginTransaction()
//            .remove(this)

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

        var suspectInfo: String

        val gender: String = when (result.suspect.suspectGender) {
            "male" -> "남자"
            "female" -> "여자"
            else -> "성별 모름"
        }

        suspectInfo = gender + "/" + result.suspect.suspectAge

        if(result.suspect.suspectAddress != null) {
            suspectInfo = suspectInfo + "/" + result.suspect.suspectAddress
        }

        if(result.suspect.suspectDetailAddress != null) {
            suspectInfo = suspectInfo + "/" + result.suspect.suspectDetailAddress
        }

        //아동과의 관계 입력은 필수값 -> null 일 경우가 없음
//        if(result.suspect.relationship==null) {
//            binding.suspectInfoRelationshipView.visibility = View.GONE
//        }

        if (result.suspect.suspectName == null) {
            binding.suspectInfoNameTv.text = unknown
        } else {
            binding.suspectInfoNameTv.text = result.suspect.suspectName
        }

        binding.suspectInfoRelationshipTv.text = result.suspect.relationship
        binding.suspectInfoSpecificTv.text = suspectInfo

        binding.recordInfoDateTv.text = result.abuseDate
        binding.recordInfoTimeTv.text = result.abuseTime
        binding.recordInfoLocationTv.text = result.abusePlace

        //아동학대 유형에 따른 다른 이미지 로드
        if(result.abuseType == "신체 학대") {
            binding.recordAbuseTypeIv.setImageResource(R.drawable.icn_category_physical)
        } else if (result.abuseType == "정서 학대") {
            binding.recordAbuseTypeIv.setImageResource(R.drawable.icn_category_emotional)
        } else if (result.abuseType == "성 학대") {
            binding.recordAbuseTypeIv.setImageResource(R.drawable.icn_category_sexual)
        } else {
            binding.recordAbuseTypeIv.setImageResource(R.drawable.icn_category_neglect)
        }

        binding.recordAbuseTypeTv.text = result.abuseType
        binding.recordTextTv.text = result.detailDescription
        binding.recordTextEtcTv.text = result.detailEtcDescription

        if (result.detailEtcDescription==null) {
            binding.recordTextEtcTv.visibility = View.GONE
        } else {
            binding.recordTextEtcTv.visibility = View.VISIBLE
        }

    }


    override fun onDeleteRecordSuccess(code: Int, result: DeleteRecord) {
        Log.d("DELETE-SUCCESS",result.deleteAbuseMessage)
//        Toast.makeText(context,result.deleteAbuseMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onDeleteRecordFailure(code: Int, message: String) {
        Log.d("DELETE-FAILURE",message)
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

        if(result.pictureList.isEmpty() && result.videoList.isEmpty()) {
            binding.pictureVideoFrm.visibility = View.GONE
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






