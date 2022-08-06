package com.umc.save

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.umc.save.Home.HomeFragment
import com.umc.save.Home.option.HomeAlarmFragment
import com.umc.save.Home.option.HomeSettingsFragment
import com.umc.save.Locker.LockerFragment
import com.umc.save.Record.RecordFragment
import com.umc.save.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initBottomNavigation()
        initActionBar()
    }

    // 키보드를 다른곳 터치 시 내려주는 메소드
    /*override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val view = currentFocus
        if (view != null && (ev!!.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_MOVE) && view is EditText && !view.javaClass.name.startsWith("android.webkit.")) {
            val scrcoords = IntArray(2)
            view.getLocationOnScreen(scrcoords)
            val x = ev.rawX + view.getLeft() - scrcoords[0]
            val y = ev.rawY + view.getTop() - scrcoords[1]
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom()) (this.getSystemService(
                Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(this.window.decorView.applicationWindowToken, 0)
        }

        return super.dispatchTouchEvent(ev)
    }*/

    /*//Listener역할을 할 Interface 생성
    interface onBackPressedListener {
        fun onBackPressed()
    }*/

    /*override fun onBackPressed() {
        //아래와 같은 코드를 추가하도록 한다
        //해당 엑티비티에서 띄운 프래그먼트에서 뒤로가기를 누르게 되면 프래그먼트에서 구현한 onBackPressed 함수가 실행되게 된다.
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if (fragment is onBackPressedListener) {
                (fragment as onBackPressedListener).onBackPressed()
                return
            }
        }
    }*/

    //action bar 알림&설정 아이콘 클릭하면 to fragment
    private fun initActionBar() {
        binding.mainActionbar.actionMainAlarmIv.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeAlarmFragment())
                .commitAllowingStateLoss()
        }
        binding.mainActionbar.actionMainSettingsIv.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeSettingsFragment())
                .commitAllowingStateLoss()
        }

    }

    private fun initBottomNavigation() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.tab_home -> {
                    binding.mainActionbar.actionMainHomeIv.visibility = View.VISIBLE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.tab_storage -> {
                    binding.mainActionbar.actionMainHomeIv.visibility = View.VISIBLE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LockerFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.tab_add -> {
                    binding.mainActionbar.actionMainHomeIv.visibility = View.VISIBLE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, RecordFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false

//        binding.button.setOnClickListener{
//            startActivity(Intent(this, RecordActivity::class.java))

        }

    }
}

