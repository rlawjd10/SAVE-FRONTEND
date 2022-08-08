package com.umc.save.Record.RecordDetail

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.media.ThumbnailUtils
import android.media.tv.TvContract.Channels.CONTENT_TYPE
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.umc.save.R
import com.umc.save.databinding.ActivityDetailEtcBinding
import kotlin.collections.HashMap


class DetailEtcActivity : AppCompatActivity() {

    private var PICK_IMAGE = 1
    private var PICK_VIDEO = 2
    private var PICK_AUDIO = 3

    lateinit var binding: ActivityDetailEtcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailEtcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        binding.pictureBtn.setOnClickListener {
            val status = ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            if (status == PackageManager.PERMISSION_GRANTED) {
                // Permission 허용
                getImage()
                binding.pictureSelectedSpace.isVisible = true
            } else {
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
            val status = ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            if (status == PackageManager.PERMISSION_GRANTED) {
                // Permission 허용
                getVideo()
                binding.videoSelectedSpace.isVisible = true
            } else {
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
            val status = ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            if (status == PackageManager.PERMISSION_GRANTED) {
                // Permission 허용
                getRecord()
                binding.recordSelectedSpace.isVisible = true
            } else {
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

    fun getImage() {
        // val intent = Intent("android.intent.action.GET_CONTENT")
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        intent.type = "image/*" // 모든 이미지
        startActivityForResult(intent, PICK_IMAGE)
    }

    fun getVideo() {
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "video/*" // 모든 이미지
        startActivityForResult(intent, PICK_VIDEO)
    }

    fun getRecord() {
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "audio/*" // 모든 이미지
        startActivityForResult(intent, PICK_AUDIO)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_IMAGE) {
                var currentImageURL : Uri? = data?.data
                var filename = currentImageURL?.lastPathSegment


                binding.pictureSelectedSpace.setText(filename)

                Toast.makeText(this, "사진 첨부", Toast.LENGTH_SHORT).show()
            }

            if (requestCode == PICK_VIDEO) {
                var currentVideoURL : Uri? = data?.data
                var filename = currentVideoURL?.lastPathSegment
                var thumbnail = ThumbnailUtils.createVideoThumbnail(currentVideoURL.toString(), MediaStore.Video.Thumbnails.MICRO_KIND)


                binding.videoSelectedSpace.setText(filename)


                Toast.makeText(this, "영상 첨부", Toast.LENGTH_SHORT).show()
            }

            if (requestCode == PICK_AUDIO) {
                var currentVideoURL : Uri? = data?.data
                var filename = currentVideoURL?.lastPathSegment

                binding.recordSelectedSpace.setText(filename)

                Toast.makeText(this, "녹음 첨부", Toast.LENGTH_SHORT).show()
            }

            else {
                Toast.makeText(this, "오류가 발생하였습니다.", Toast.LENGTH_SHORT).show()
            }

        }

    }

    fun initActionBar() {
        val appBartext = findViewById<TextView>(R.id.appbar_page_name_tv)
        val appBarBtn = findViewById<ImageView>(R.id.appbar_back_btn)
        val appBarComplete = findViewById<TextView>(R.id.appbar_complete_tv)

        appBartext.text= "기록"
        appBartext.visibility= View.VISIBLE
        appBarComplete.text= "완료"
        appBarComplete.visibility= View.INVISIBLE
        appBarBtn.setOnClickListener{onBackPressed()}
    }

    val thumbnailTime = 1
    fun getVideoThumbnail(uri: Uri) : Bitmap? {
        val retriever = MediaMetadataRetriever()

        try {
            retriever.setDataSource(uri.toString(), HashMap<String,String>())
            return retriever.getFrameAtTime((thumbnailTime * 1000000).toLong(), MediaMetadataRetriever.OPTION_CLOSEST)
        } catch (e : IllegalArgumentException){
            e.printStackTrace()
        } catch (e : RuntimeException){
            e.printStackTrace()
        } finally {
            try {
                retriever.release()
            } catch (e : RuntimeException){
                e.printStackTrace()
            }
        }
        return null
    }

}
