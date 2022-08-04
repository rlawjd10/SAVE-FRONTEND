package com.umc.save.Locker

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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

//        pictureList.apply {
//            add(Picture(1,"~~~~~"))
//            add(Picture(2,"~~~~~"))
//            add(Picture(3,"~~~~~"))
//            add(Picture(4,"~~~~~"))
//            add(Picture(5,"~~~~~"))
//            add(Picture(6,"~~~~~"))
//            add(Picture(7,"~~~~~"))
//            add(Picture(8,"~~~~~"))
//        }

        pictureList.apply {
            add(Picture(1,"",R.drawable.fragment_child_blue_background))
            add(Picture(2,"",R.drawable.fragment_child_red_off_background))
            add(Picture(3,"",R.drawable.fragment_child_red_on_background))
            add(Picture(1,"",R.drawable.fragment_red_background))
            add(Picture(2,"",R.drawable.fragment_child_blue_background))
            add(Picture(3,"",R.drawable.fragment_child_blue_background))
            add(Picture(1,"", R.drawable.fragment_child_red_on_background))
            add(Picture(2,"",R.drawable.fragment_child_blue_background))
            add(Picture(3,"",R.drawable.fragment_offender_off_background))
        }

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
        intent.putExtra("picture",picture.image)
        //다음에 picture.location으로 바꿔놓기
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
