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

class DetailRecordLockerFragment : Fragment() {
    lateinit var binding : FragmentLockerRecordDetailBinding
    private var gson : Gson = Gson()
    lateinit var record : RecordData
    lateinit var recordDetail : RecordDetailData
    lateinit var suspect : Suspect
    lateinit var recording : Recording
    private var recordingList = ArrayList<Recording>()
    private var player : ExoPlayer? = null

    var url = "https://dby56rj67jahx.cloudfront.net/recording/192470e0-2fcd-49d1-b2a0-6105b864a930.m4a"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerRecordDetailBinding.inflate(inflater,container,false)

        recordingList.apply {
            add(Recording("https://dby56rj67jahx.cloudfront.net/recording/192470e0-2fcd-49d1-b2a0-6105b864a930.m4a","음성녹음1",false))
            add(Recording("https://dby56rj67jahx.cloudfront.net/recording/958e5d70-91d2-402e-8a0e-f1fe49d45683.mp3","음성녹음2",false))
            add(Recording("https://dby56rj67jahx.cloudfront.net/recording/192470e0-2fcd-49d1-b2a0-6105b864a930.m4a","음성녹음3",false))
            add(Recording("https://dby56rj67jahx.cloudfront.net/recording/958e5d70-91d2-402e-8a0e-f1fe49d45683.mp3","음성녹음4",false))
        }


//        recording = Recording(1,"~~~","음성녹음1",10,100,false)
        record = RecordData(1,Date(2022,7,5), Time(2,3,0),"인천광역시 연수구 송도동",Date(2022,3,2))
        recordDetail = RecordDetailData("신체학대","아파트 복도에서 목격했음","엄마와 아빠 둘다 아이에게 학대를 가하는 것 같음")
        suspect = Suspect(1,"홍길동","여",
            "32","","","모")




        val recordingRVAdapter = RecordingRecordRVAdapter(recordingList)
        binding.recordingListRv.adapter = recordingRVAdapter
        binding.recordingListRv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        recordingRVAdapter.setMyItemClickListener(object : RecordingRecordRVAdapter.MyItemClickListener {
            override fun onItemClick(recording: Recording) {
                openRecordingActivity(recording)
//                recording.isPlaying = !recording.isPlaying
            }
        })

        setInitView()
        setOnClickListeners()


        return binding.root
    }

//    private fun initPlayer() {
//        context?.let {
//            player = ExoPlayer.Builder(it).build()
//        }
//        binding.audioPlayerPv.player = player
//        binding.let { binding ->
//            player?.addListener(object: Player.Listener {
//                override fun onIsPlayingChanged(isPlaying: Boolean) {
//                    super.onIsPlayingChanged(isPlaying)
//                    if(isPlaying) {
//                        binding.exoPlay.visibility = View.GONE
//                        binding.exoPause.visibility = View.VISIBLE
//                        binding.exoPosition.visibility = View.VISIBLE
//                        binding.exoDuration.setTextColor(ContextCompat.getColor(requireContext(), R.color.light_light_gray))
//                        binding.exoPosition.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
//                        binding.recordingTitleTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
//
//                    } else {
//                        binding.exoPlay.visibility = View.VISIBLE
//                        binding.exoPause.visibility = View.GONE
//                        binding.exoPosition.visibility = View.GONE
//                        binding.exoDuration.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
//                        binding.recordingTitleTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
//                    }
//
//                }
//
//                override fun onPlaybackStateChanged(playbackState: Int) {
//                    super.onPlaybackStateChanged(playbackState)
//                    updateTime()
//
//                }
//
//            })
//        }
//
//    }




    private fun setOnClickListeners() {
        binding.recordVideoNumTv.setOnClickListener {

            (context as MainActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.picture_video_frm,ListVideoLockerFragment())
                .commitAllowingStateLoss()
        }


        binding.recordPictureNumTv.setOnClickListener {

            (context as MainActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.picture_video_frm,ListPictureLockerFragment())
                .commitAllowingStateLoss()

        }

    }

    private fun setInitView() {

        val unknown = "-"
        val suspectInfo : String = suspect.suspectGender + "/" + suspect.suspectAge.toString()
        if (suspect.suspectName == "") {
            binding.suspectInfoNameTv.text = unknown
        } else {
            binding.suspectInfoNameTv.text = suspect.suspectName
        }
        binding.suspectInfoRelationshipTv.text = suspect.relationship
        binding.suspectInfoSpecificTv.text = suspectInfo

        binding.recordInfoDateTv.text = record.abuseDate.toString()
        binding.recordInfoTimeTv.text = record.abuseTime.toString()
        binding.recordInfoLocationTv.text = record.abusePlace

        binding.recordAbuseTypeTv.text = recordDetail.abuseType
        binding.recordTextTv.text = recordDetail.detailDescription
        binding.recordTextEtcTv.text = recordDetail.detailEtcDescription

        //default 값은 이미지 fragment
        (context as MainActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.picture_video_frm,ListPictureLockerFragment())
            .commitAllowingStateLoss()
    }


    private fun openRecordingActivity(recording: Recording) {

        val intent = Intent(context, RecordingLockerActivity::class.java)
        intent.putExtra("recording",recording.location)
        //다음에 picture.location으로 바꿔놓기
        startActivity(intent)
    }





}






