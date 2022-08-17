package com.umc.save.Home.option

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.databinding.FragmentHomeEditProfileBinding
import com.umc.save.databinding.FragmentHomeSettingsBinding


class HomeEditProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private lateinit var binding: FragmentHomeEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeEditProfileBinding.inflate(layoutInflater)

        // 비밀번호 변경 버튼 to fragment
        binding.passwordChangeBtn.setOnClickListener {
            changeFragment(HomePasswordChangeFragment())
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    //fragment to fragment method
    private fun changeFragment(fragment: Fragment) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, fragment)
            .addToBackStack(null)
            .commit()
    }
}