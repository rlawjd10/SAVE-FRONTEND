package com.umc.save.Record.RecordDetail

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Base64.NO_WRAP
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.umc.save.R
import com.umc.save.databinding.ActivityDetailEtcBinding
import java.io.ByteArrayOutputStream
import java.io.InputStream


class DetailEtcActivity : AppCompatActivity() {

    private var PICK_IMAGE = 1
    private var PICK_VIDEO = 2
    private var PICK_AUDIO = 3

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
                binding.pictureSelectedSpace.isVisible = true
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
                binding.videoSelectedSpace.isVisible = true
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
                binding.recordSelectedSpace.isVisible = true
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

    var currentImageURL: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Activity.RESULT_OK){
            if(requestCode == PICK_IMAGE){

                currentImageURL = intent?.data
                val ins : InputStream? = currentImageURL?.let {
                    applicationContext.contentResolver.openInputStream(
                        it
                    )
                }
                val img: Bitmap = BitmapFactory.decodeStream(ins)
                ins?.close()
                val resized = Bitmap.createScaledBitmap(img, 256, 256, true)
                val byteArrayOutputStream = ByteArrayOutputStream()
                resized.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream)
                val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
                val outStream = ByteArrayOutputStream()
                val res: Resources = resources
                var profileImageBase64 = Base64.encodeToString(byteArray, NO_WRAP)
                // 여기까지 인코딩 끝

                // 이미지 뷰에 선택한 이미지 출력
                val imageview: ImageView = findViewById(R.id.picture)
                imageview.setImageURI(currentImageURL)
                /*
                try {
                    //이미지 선택 후 처리
                }catch (e: Exception){
                    e.printStackTrace()
                }
            } else{
                Log.d("ActivityResult", "something wrong")
            }*/

                Toast.makeText(this,"사진 첨부",Toast.LENGTH_SHORT).show()

            } else{
                Toast.makeText(this,"사진을 가져오지 못했습니다",Toast.LENGTH_SHORT).show()
            }

            if(requestCode == PICK_VIDEO){
                Toast.makeText(this,"영상 첨부",Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this,"영상을 가져오지 못했습니다",Toast.LENGTH_SHORT).show()
            }

            if(requestCode == PICK_AUDIO){
                Toast.makeText(this,"녹음 첨부",Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this,"녹음을 가져오지 못했습니다",Toast.LENGTH_SHORT).show()
            }
        }
    }

}