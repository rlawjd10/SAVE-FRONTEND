package com.umc.save.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.databinding.FragmentHomeStatisBinding

class StatisHomeFragment : Fragment() {

   lateinit var binding: FragmentHomeStatisBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeStatisBinding.inflate(layoutInflater)


        // Inflate the layout for this fragment
        return binding.root
    }

}