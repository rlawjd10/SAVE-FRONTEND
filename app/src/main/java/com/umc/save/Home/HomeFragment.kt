package com.umc.save.Home

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
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

        //toolbar
        //(activity as AppCompatActivity).setSupportActionBar(binding.mainToolbar)
        binding.mainToolbar.inflateMenu(R.menu.toolbar_menu)


//        action fragment바꾸기 -> 현재는 설정으로 감
        binding.homeActionBtn.setOnClickListener {
            changeFragment(HomeSettingsFragment())
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

    //메뉴 확장
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
    }


    //클릭 이벤트 처리
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_setting -> {
                changeFragment(HomeSettingsFragment())
                true
            }
            R.id.action_alarm -> {
                changeFragment(HomeAlarmFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    //fragment to fragment method
    private fun changeFragment(fragment: Fragment) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, fragment)
            .disallowAddToBackStack()
            .commit()
    }

}
