package com.umc.save.Locker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.save.databinding.FragmentLockerBinding
import java.util.*
import kotlin.collections.ArrayList

class LockerFragment : Fragment() {
    lateinit var binding: FragmentLockerBinding
    var currentPosition = 0
    private var childList= ArrayList<Child>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerBinding.inflate(inflater,container,false)


        childList.apply {
            add(Child(1,"양현진",true,
                "여","10","인천광역시 연수구 송도동",
                "1000-1202", Date(2022,1,2)))
            add(Child(2,"울랄라",false,
                "여","10","서울시 광진구",
                "902-1002", Date(2022,1,4)))
            add(Child(3,"크크크",false,
                "여","10","부산",
                "1000-1202", Date(2021,2,3)))
        }


        val childRVAdapter = ChildRVAdapter(childList)
        binding.childListRv.adapter = childRVAdapter
        binding.childListRv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        return binding.root

//        albumRVAdapter.setMyItemClickListener(object: AlbumRVAdapter.MyItemClickListener{
//            override fun onItemClick(album: Album) {
//                changeAlbumFragment(album)
//            }
//
//            override fun onRemoveAlbum(position: Int) {
//                albumRVAdapter.removeItem(position)
//            }
//
//        })

    }
}
