package com.umc.save.Record

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.save.Home.HomeFragment
import com.umc.save.Locker.LockerFragment
import com.umc.save.R
import com.umc.save.databinding.ActivityMainBinding
import com.umc.save.databinding.ActivityRecordDoneBinding

class RecordDoneActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecordDoneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordDoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, RecordDoneFragment())
            .commitAllowingStateLoss()

        binding.mainBnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.tab_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.tab_storage -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LockerFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.tab_add -> {
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