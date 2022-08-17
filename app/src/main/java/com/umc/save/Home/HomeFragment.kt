package com.umc.save.Home

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract
import android.text.Html
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.umc.save.Home.option.*
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.Sign.Auth.userIdx_var
import com.umc.save.Sign.User.userIdx.userIdx.userIdx
import com.umc.save.databinding.FragmentHomeBinding
import org.w3c.dom.Text


class HomeFragment : Fragment(), UserInfoView {

    val userIdx = userIdx_var.UserIdx.UserIdx
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater,container,false)

        //서버에서 userInfo 가져오기
        getUserInfo()

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

        /*//text.setTextColor(ContextCompat.getColor(applicationContext, R.color.dark_red))
        //binding.loginSignInBtn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(binding.root.context, R.color.dark_gray))
        //아동학대란? 문자열 색
        val answerText = binding.homeChildAnswerTv
        answerText.setTextColor(ContextCompat.getColor(Context, R.color.dark_red))
        val ssb = SpannableStringBuilder(binding.homeChildAnswerTv.text.toString())

        ssb.setSpan(ForegroundColorSpan(Color.RED), 0, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
*/

       /* val spannable = SpannableStringBuilder(binding.homeChildAnswerTv.text.toString())
        spannable.setSpan(
            ForegroundColorSpan(Color.RED),
            1,
            11,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
*/
        initActionBar()
        return binding.root


    }

    private fun getUserInfo() {

        val userInfoService = UserInfoService()
        userInfoService.setUserInfoView(this)
        userInfoService.getUserInfo(userIdx)
    }

    //fragment to fragment method
    //main_frm은 fragment부분아다. MainActivity확인
    private fun changeFragment(fragment: Fragment) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun initActionBar() {
        binding.mainActionbar.actionMainHomeIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
        binding.mainActionbar.actionMainAlarmIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeAlarmFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
        binding.mainActionbar.actionMainSettingsIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeSettingsFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
    }

    override fun onGetUserSuccess(code: Int, result: UserInfo) {
        Log.d("GET-USER-SUCCESS",result.toString())
        binding.homeUserNameTv.text = result.name
    }

    override fun userNotExist(code: Int, message: String) {
        TODO("Not yet implemented")
    }

    override fun onGetUserFailure(code: Int, message: String) {
        TODO("Not yet implemented")
    }

}
