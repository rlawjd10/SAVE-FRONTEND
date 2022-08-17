package com.umc.save.Locker

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginStart
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.umc.save.R
import com.umc.save.Record.ChildRecordActivity
import com.umc.save.databinding.FragmentLockerPictureListBinding

class ListPictureLockerFragment: Fragment() {
    lateinit var binding : FragmentLockerPictureListBinding
    var currentPosition = 0
    val gson : Gson =  Gson()
    private var pictureList = ArrayList<Picture>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerPictureListBinding.inflate(inflater,container,false)


        val pictureJson = arguments?.getString("picture")
        val itemType = object : TypeToken<ArrayList<Picture>>() {}.type
        pictureList = gson.fromJson(pictureJson,itemType)

        Log.d("======PICTURELIST",pictureList.toString())

        val pictureRecordRVAdapter = PictureRecordRVAdapter(pictureList)

        binding.pictureListRv.adapter = pictureRecordRVAdapter
        binding.pictureListRv.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL,false)

        pictureRecordRVAdapter.setMyItemClickListener(object: PictureRecordRVAdapter.MyItemClickListener {
            override fun onItemClick(picture: Picture) {
                openPictureActivity(picture)
            }
        })

        return binding.root
    }



    private fun openPictureActivity(picture: Picture) {

        val intent = Intent(context, PictureLockerActivity::class.java)
        intent.putExtra("picture",picture.location)
        startActivity(intent)
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
