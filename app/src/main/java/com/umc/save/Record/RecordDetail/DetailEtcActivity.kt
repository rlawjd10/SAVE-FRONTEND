package com.umc.save.Record.RecordDetail

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.umc.save.databinding.ActivityDetailEtcBinding
import java.util.jar.Manifest

class DetailEtcActivity : AppCompatActivity() {

    private var PICK_IMAGE = 0
    private var PICK_VIDEO = 0
    private var PICK_AUDIO = 0

    private var uri : Uri? = null

    lateinit var binding: ActivityDetailEtcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailEtcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pictureBtn.setOnClickListener{
            val status = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
            if(status == PackageManager.PERMISSION_GRANTED){
                // Permission 허용
                getImage()
            } else{
                // Permission 허용
                Toast.makeText(this, "접근 불가", Toast.LENGTH_LONG).show()

                // 허용 요청
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf<String>(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    100
                )
            }
        }

        binding.videoBtn.setOnClickListener {
            val status = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
            if(status == PackageManager.PERMISSION_GRANTED){
                // Permission 허용
                getVideo()
            } else{
                // Permission 허용
                Toast.makeText(this, "접근 불가", Toast.LENGTH_LONG).show()

                // 허용 요청
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf<String>(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    100
                )
            }
        }

        binding.recordBtn.setOnClickListener {
            val status = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
            if(status == PackageManager.PERMISSION_GRANTED){
                // Permission 허용
                getRecord()
            } else{
                // Permission 허용
                Toast.makeText(this, "접근 불가", Toast.LENGTH_LONG).show()

                // 허용 요청
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf<String>(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    100
                )
            }
        }
    }

    fun getImage(){
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*" // 모든 이미지
        startActivityForResult(intent, PICK_IMAGE)
    }

    fun getVideo(){
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "video/*" // 모든 이미지
        startActivityForResult(intent, PICK_VIDEO)
    }

    fun getRecord(){
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "audio/*" // 모든 이미지
        startActivityForResult(intent, PICK_AUDIO)
    }

}