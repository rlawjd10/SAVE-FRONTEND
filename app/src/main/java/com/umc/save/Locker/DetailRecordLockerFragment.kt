package com.umc.save.Locker

import android.annotation.SuppressLint
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
import java.text.SimpleDateFormat

class DetailRecordLockerFragment : Fragment() {
    lateinit var binding : FragmentLockerRecordDetailBinding
    private var gson : Gson = Gson()
//    var currentPosition = 0
    private var recordList = ArrayList<RecordData>()
    private var recordingList = ArrayList<Recording>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerRecordDetailBinding.inflate(inflater,container,false)

        recordingList.apply {
            add(Recording(1,"~~~","음성녹음1",0,100,false))
            add(Recording(2,"~~~","음성녹음2",0,100,false))
            add(Recording(3,"~~~","음성녹음3",0,100,false))
            add(Recording(4,"~~~","음성녹음4",0,100,false))
        }


        setInitView()

        val recordingRVAdapter = RecordingRecordRVAdapter(recordingList)
        binding.recordingListRv.adapter = recordingRVAdapter
        binding.recordingListRv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)


        recordingRVAdapter.setMyItemClickListener(object: RecordingRecordRVAdapter.MyItemClickListener {
            override fun onItemClick(recording:Recording) {
                Log.d("playing button clicked","True")

//                changeRecordChildLockerFragment(child)
            }

        })


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






