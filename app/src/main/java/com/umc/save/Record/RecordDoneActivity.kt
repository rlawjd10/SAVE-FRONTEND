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
    }

}