package com.umc.save.Locker

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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
    lateinit var timer : Timer
    private var mediaPlayer : MediaPlayer? = null
    lateinit var record : RecordData
    lateinit var recordDetail : RecordDetailData
    lateinit var suspect : Suspect
    lateinit var recording : Recording
    private var recordingList = ArrayList<Recording>()

    var nowPos = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerRecordDetailBinding.inflate(inflater,container,false)

        recordingList.apply {
            add(Recording(1,"test_audio","음성녹음1",0,180,0,false))
            add(Recording(2,"test_audio2","음성녹음2",0,120,0,false))
            add(Recording(3,"test_audio","음성녹음3",0,200,0,false))
            add(Recording(4,"test_audio2","음성녹음4",0,180,0,false))
        }


//        recording = Recording(1,"~~~","음성녹음1",10,100,false)
        record = RecordData(1,Date(2022,7,5), Time(2,3,0),"인천광역시 연수구 송도동",Date(2022,3,2))
        recordDetail = RecordDetailData("신체학대","아파트 복도에서 목격했음","엄마와 아빠 둘다 아이에게 학대를 가하는 것 같음")
        suspect = Suspect(1,"홍길동","여",
            "32","","","모", Date(2022-1900,1,2))



        val recordingRVAdapter = RecordingRecordRVAdapter(recordingList)
        binding.recordingListRv.adapter = recordingRVAdapter
        binding.recordingListRv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        recordingRVAdapter.setMyItemClickListener(object: RecordingRecordRVAdapter.MyItemClickListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onItemClick(recording:Recording) {

                recording.isPlaying = !recording.isPlaying

                setPlayer(recording)

            }

        })

        setInitView()
        setOnClickListeners()


        return binding.root
    }


    //                startTimer()

//                activity?.runOnUiThread {
//                    recordingRVAdapter.notifyDataSetChanged()
//                }

//                Log.d("playing button clicked","True")
//                changeRecordChildLockerFragment(child)


    //                timer = Timer(recording)

//                Log.d("recording",recording.toString())


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

//    private fun setPlayer(recording: Recording) {
//        val music = resources.getIdentifier(recording.location,"raw",activity?.packageName)
//        mediaPlayer = MediaPlayer.create(context,music)
//
//        setPlayerStatus(recording.isPlaying)
//    }

//    private fun startTimer() {
////        timer = Timer(recording)
//        timer.start()
//    }



    //고치기
//    private fun setPlayerStatus(isPlaying : Boolean) {
//        if(isPlaying) {
//            mediaPlayer?.start()
//        }
//        else {
//            if(mediaPlayer?.isPlaying == true){
//                mediaPlayer?.pause()
//            }
//        }
//    }


//    inner class Timer(var recording: Recording):Thread(){
////        private val length: Int, var isPlaying: Boolean = true
//        private var second : Int = 0
//        private var mills : Float = 0f
//        private val recordingRVAdapter = RecordingRecordRVAdapter(recordingList)
//
//
//        @SuppressLint("NotifyDataSetChanged")
//        override fun run() {
//            super.run()
//            try {
//                while (true) {
//                    if (second >= recording.length){
//                        break
//                    }
//
//                    if (recording.isPlaying){
//                        sleep(100)
//                        mills += 100
//
//                        activity?.runOnUiThread {
//                            recording.progress = (mills/(recording.length*1000)*100).toInt()
//                            recordingRVAdapter.notifyDataSetChanged()
//                            Log.d("recording thread start",recording.toString())
////                            binding.recordingProgress.progress = ((mills/playTime)*100).toInt()
//                        }
//                        if (mills % 1000 == 0f){
//                            activity?.runOnUiThread{
//                                recording.second = second
////                                binding.songStartTimeTv.text = String.format("%02d:%02d",second / 60, second % 60)
//                                recordingRVAdapter.notifyDataSetChanged()
//                                Log.d("1second added",recording.toString())
//                            }
//                            second++
//                        }
//                    }
//                }
//            } catch (e : InterruptedException) {
//                Log.d("Recording","스레드가 죽었습니다. ${e.message}")
//            }
//
//        }
//    }



    private fun setPlayer(recording: Recording) {
        val music = resources.getIdentifier(recording.location,"raw",activity?.packageName)
        mediaPlayer = MediaPlayer.create(context,music)


        setPlayerStatus(recording.isPlaying)
    }


    private fun setPlayerStatus(isPlaying : Boolean) {
        if(isPlaying) {
            mediaPlayer?.start()
        }
        else {
//            mediaPlayer?.release()
//            mediaPlayer = null
            if(mediaPlayer?.isPlaying == true){
                mediaPlayer?.pause()
            }
        }
    }




}






