package com.umc.save.Home

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ImageView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.umc.save.Home.option.HomeAlarmFragment
import com.umc.save.Home.option.HomeSettingsFragment
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeBinding.inflate(layoutInflater)


//        home 화면 버튼 클릭했을 때 fragment to fragment
        binding.homeActionBtn.setOnClickListener {
            changeFragment(ActionHomeFragment())
        }
        binding.homeStatsBtn.setOnClickListener {
            changeFragment(StatisHomeFragment())
        }
        binding.homeGuideBtn.setOnClickListener {
            changeFragment(GuideHomeFragment())
        }
        binding.homeNewsBtn.setOnClickListener {
            changeFragment(NewsHomeFragment())
        }


        return binding.root
    }



    //fragment to fragment method
    //main_frm은 fragment부분아다. MainActivity확인
    private fun changeFragment(fragment: Fragment) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, fragment)
            .disallowAddToBackStack()
            .commit()
    }

}
