package com.umc.save.Home

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.provider.CalendarContract
import android.text.Html
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
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
import androidx.viewpager2.widget.ViewPager2
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
    val data = mutableListOf<BannerData>()

    //자동 스크롤
    val bannerPAdapter = BannerVPAdapter()
    private var currentPosition = Int.MAX_VALUE / 2
    // 몇초 간격으로 페이지를 넘길것인지 (2000 = 2초)
    private val intervalTime = 2000.toLong()
    //handler
    private val myHandler = MyHandler()

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


       /* val spannable = SpannableStringBuilder(binding.homeChildAnswerTv.text)
        spannable.apply {
            setSpan(R.color.dark_red, 0, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }*/

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
        initBannerRecyclerView()
        return binding.root
    }

    //배너
    fun initBannerRecyclerView() {
        val bannerPAdapter = BannerVPAdapter()

        bannerPAdapter.itemList = data.apply {
            add(BannerData(R.drawable.banner1_green, "https://www.childfund.or.kr/main.do"))
            add(BannerData(R.drawable.banner2_our, "https://www.bokji.net/"))
            add(BannerData(R.drawable.banner3_community, "https://www.icareinfo.go.kr/"))
            add(BannerData(R.drawable.banner4_health, "http://www.mohw.go.kr/react/index.jsp"))
            add(BannerData(R.drawable.banner5_protect, "https://www.ncrc.or.kr/ncrc/main.do"))
        }

        binding.homeBannerVp.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    when (state) {
                        //뷰페이지에서 손을 떼었을 때, 뷰페이지에서 멈췄을 때
                        ViewPager2.SCROLL_STATE_IDLE -> autoScrollStart(intervalTime)
                        //뷰페이지 움직이는 중
                        ViewPager2.SCROLL_STATE_DRAGGING -> autoScrollStop()
                    }
                }
            })
        }

        //어댑터 생성해주고, 가로로 스크롤되도록
        binding.homeBannerVp.adapter = bannerPAdapter
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        //현재 위치를 지정
        binding.homeBannerVp.setCurrentItem(currentPosition, false)
    }

    private fun autoScrollStart(intervalTime: Long) {
        myHandler.removeMessages(0) // 이거 안하면 핸들러가 1개, 2개, 3개 ... n개 만큼 계속 늘어남
        myHandler.sendEmptyMessageDelayed(0, intervalTime) // intervalTime 만큼 반복해서 핸들러를 실행하게 함
    }

    private fun autoScrollStop(){
        myHandler.removeMessages(0) // 핸들러를 중지시킴
    }

    private inner class MyHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            if(msg.what == 0) {
                binding.homeBannerVp.setCurrentItem(++currentPosition, true) // 다음 페이지로 이동
                autoScrollStart(intervalTime) // 스크롤을 계속 이어서 한다.
            }
        }
    }

    // 다른 페이지 갔다가 돌아오면 다시 스크롤 시작
    override fun onResume() {
        super.onResume()
        autoScrollStart(intervalTime)
    }

    // 다른 페이지로 떠나있는 동안 스크롤이 동작할 필요는 없음. 정지
    override fun onPause() {
        super.onPause()
        autoScrollStop()
    }

    //유저 정보 조회
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
