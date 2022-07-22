package com.umc.save.Record

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.save.R
import com.umc.save.databinding.ActivityChildRecordBinding
import com.umc.save.databinding.ActivityRecordMainBinding

class ChildRecordActivity : AppCompatActivity() {
    lateinit var binding: ActivityChildRecordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChildRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recordDone.setOnClickListener{
            startActivity(Intent(this, OffenderRecordActivity::class.java))
        }

    }
}