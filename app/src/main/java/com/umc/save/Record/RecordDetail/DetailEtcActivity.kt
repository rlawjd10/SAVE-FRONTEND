package com.umc.save.Record.RecordDetail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Color
import android.media.MediaMetadataRetriever
import android.media.ThumbnailUtils
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.Record.Auth.AbuseSituation.AbuseResult
import com.umc.save.Record.Auth.AbuseSituation.AbuseService
import com.umc.save.Record.Auth.AbuseSituation.AbuseSituation
import com.umc.save.Record.Auth.AbuseSituation.Result
import com.umc.save.Record.Auth.AbuseSituation.abuse_var
import com.umc.save.Record.Auth.ChildRecord.childidx_var
import com.umc.save.Record.Auth.Picture.PictureResult
import com.umc.save.Record.Auth.Picture.PictureService
import com.umc.save.Record.Auth.RealPathUtil
import com.umc.save.Record.Auth.Recording.RecordingPostService
import com.umc.save.Record.Auth.Recording.RecordingResult
import com.umc.save.Record.Auth.SuspectRecord.suspectIdx_var
import com.umc.save.Record.Auth.Video.VideoResult
import com.umc.save.Record.Auth.Video.VideoService
import com.umc.save.databinding.ActivityDetailEtcBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import java.io.*
import kotlin.collections.HashMap

//, VideoResult, RecordingResult
class DetailEtcActivity : AppCompatActivity(), AbuseResult, PictureResult, VideoResult,
    RecordingResult {

    private var PICK_IMAGE = 1
    private var PICK_VIDEO = 2
    private var PICK_AUDIO = 3

    var pictureList = ArrayList<MultipartBody.Part>()
    var pictureNameList = ArrayList<String>()
    var videoList = ArrayList<MultipartBody.Part>()
    var videoNameList = ArrayList<String>()
    var audioList = ArrayList<MultipartBody.Part>()
    var audioNameList = ArrayList<String>()

    var thumbnail : MultipartBody.Part? = null

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

                // 허용 요청
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf<String>(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    100
                )
            }
        }

        binding.recordDetail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if(!binding.recordDetail.text.equals(""))
                    binding.btnNext.setBackgroundResource(R.drawable.fragment_dark_red_background)
                else{
                    binding.btnNext.setBackgroundResource(R.drawable.fragment_dark_gray_background)
                }
            }

        })



        binding.btnNext.setOnClickListener {
            abuse_save()

            startActivity(Intent(this, MainActivity::class.java))
        }
    }



    // 절대경로 변환
    fun absolutelyPath(path: Uri?, context: Context): String {
        var proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        var c: Cursor? = context.contentResolver.query(path!!, proj, null, null, null)
        var index = c?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c?.moveToFirst()

        var result = c?.getString(index!!)

        return result!!
    }

    private fun getAbuseSituation(): AbuseSituation {
        var childIdx = childidx_var.childIdx.childIdx
        var suspectIdx = suspectIdx_var.suspectIdx.suspectIdx
        var date = abuse_var.abuse.a_date
        var time = abuse_var.abuse.a_time
        var place = abuse_var.abuse.place
        var detail = binding.recordDetail.text.toString()
        var etc = binding.recordEtc.text.toString()
        var type = abuse_var.abuse.a_type

        return AbuseSituation(childIdx, suspectIdx, date, time, place, detail, etc, type)
    }


    /////////////////////////////////////// 파일 요청 ///////////////////////////////////


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
                val imagePath = data!!.data

                val file = File(absolutelyPath(imagePath, this))
                val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
                val body = MultipartBody.Part.createFormData("picture", file.name, requestFile)

                pictureNameList.addAll(listOf(file.name))

                binding.pictureSelectedSpace.setText(setFileName(pictureNameList))

                Log.d("파일 생성!! ======== ", file.name)

                pictureList.addAll(listOf(body))


                Toast.makeText(this, "사진 첨부", Toast.LENGTH_SHORT).show()
            }

            if (requestCode == PICK_VIDEO) {
                val videoPath = data!!.data
                val realPathUtil = RealPathUtil()
                val file = File(realPathUtil.getRealPath(this, videoPath!!))
                val requestFile = RequestBody.create("video/*".toMediaTypeOrNull(), file)
                val body = MultipartBody.Part.createFormData("video", file.name, requestFile)

                binding.videoSelectedSpace.setText(file.name)

                Log.d("파일 생성!! ======== ", file.name)
                videoList.addAll(listOf(body))

                try {
                    var path : String = videoPath.path!!
                    var mMMR : MediaMetadataRetriever = MediaMetadataRetriever()
                    mMMR.setDataSource(this, videoPath)
                    var bmp = mMMR.frameAtTime

                    Log.d("tlqkf ================================================= ", bmp.toString())
                    val thumbnail_file: File? = convertBitmapToFile("thumbnail", bmp!!)


                    val requestfile_thumbnail =
                        RequestBody.create("image/*".toMediaTypeOrNull(), thumbnail_file!!)
                    val body_thumbnail = MultipartBody.Part.createFormData(
                        "thumbnail",
                        thumbnail_file.name,
                        requestfile_thumbnail
                    )
                    thumbnail = body_thumbnail
                }catch (e : NullPointerException){
                    e.printStackTrace()
                }

                Log.d("썸네일 생성!! ======== ", thumbnail.toString() )

                Toast.makeText(this, "영상 첨부", Toast.LENGTH_SHORT).show()
            }

            if (requestCode == PICK_AUDIO) {
                val audioPath = data!!.data
                val realPathUtil = RealPathUtil()
                val file = File(realPathUtil.getRealPath(this, audioPath!!))
                val requestFile = RequestBody.create("audio/*".toMediaTypeOrNull(), file)
                val body = MultipartBody.Part.createFormData("recording", file.name, requestFile)

                binding.recordSelectedSpace.setText(file.name)

                Log.d("파일 생성!! ======== ", file.name)

                audioList.addAll(listOf(body))

                Toast.makeText(this, "음성 첨부", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "오류가 발생하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    fun setFileName(list : ArrayList<String>) : String{
        var fileName = ""
        for(i in 0 .. list.size-1 step (1)) {
            if(i == list.size - 1)
                fileName = fileName + list.get(i)
            else {
                fileName = fileName + list.get(i) + ", "
            }
        }

        return fileName
    }

    fun initActionBar() {
        val appBartext = findViewById<TextView>(R.id.appbar_page_name_tv)
        val appBarBtn = findViewById<ImageView>(R.id.appbar_back_btn)
        val appBarComplete = findViewById<TextView>(R.id.appbar_complete_tv)

        appBartext.text = "기록"
        appBartext.visibility = View.VISIBLE
        appBarComplete.text = "완료"
        appBarComplete.visibility = View.INVISIBLE
        appBarBtn.setOnClickListener { onBackPressed() }
    }


    fun convertBitmapToFile(fileName: String, bitmap: Bitmap): File {
        //create a file to write bitmap data
        val file = File(cacheDir, fileName)
        file.createNewFile()

        //Convert bitmap to byte array
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0 /*ignored for PNG*/, bos)
        val bitMapData = bos.toByteArray()

        //write the bytes in file
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(file)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        try {
            fos?.write(bitMapData)
            fos?.flush()
            fos?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return file
    }


    ///////////////////////////////// 학대정황 전송 //////////////////////////////////////
    private fun abuse_save() {
        val abuseService = AbuseService()
        abuseService.setRecordResult(this)
        abuseService.record(getAbuseSituation())
    }

    override fun recordSuccess(result: Result) {
        abuse_var.abuse.abuseIdx = result.abuseIdx
        Log.d(
            "저장한 거 ============================= ", "날짜 : " + abuse_var.abuse.a_date +
                    "\n 시간 :" + abuse_var.abuse.a_time + "\n abuseIdx : " + abuse_var.abuse.abuseIdx.toString()
        )
        Toast.makeText(this, "학대 정황 기록 성공.", Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", "학대 정황 기록 성공.")

        if (!binding.pictureSelectedSpace.text.equals("")) {
            // 저장된 이미지 파일 있으면
            Image_save()
        }

        if(!binding.videoSelectedSpace.text.equals(""))
            Video_save()

        if(!binding.recordSelectedSpace.text.equals(""))
            Recording_save()
    }

    override fun recordFailure() {
        Toast.makeText(this, "학대 정황 기록 실패", Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", "학대 정황 기록 실패")
    }


    /////////////////////////////// 이미지 전송 ///////////////////////////////////////////
    private fun Image_save() {
        Log.d(
            "RECORD/FAILURE ==============================================================================================",
            "Image_save 실행"
        )

        Log.d("RECORD/FAILURE ==================================", pictureList[0].toString())
        val pictureService = PictureService()
        pictureService.setPicturedResult(this)
        pictureService.sendPicture(
            pictureList,
            abuse_var.abuse.abuseIdx,
            childidx_var.childIdx.childIdx
        )
    }

    override fun postPictureSuccess(code: Int, result: com.umc.save.Record.Auth.Picture.Result) {
        Toast.makeText(this, "이미지 기록 성공.", Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", "이미지 기록 성공.")

//        if(!binding.videoSelectedSpace.text.equals(""))
//            Video_save()
//        else{
//            if(!binding.recordSelectedSpace.text.equals(""))
//                Recording_save()
//        }

    }

    override fun postPictureFailure(code: Int, message: String) {
        Toast.makeText(this, "이미지 기록 실패.", Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", "이미지 기록 실패.")
    }


    ////////////////////////////////////// 비디오 전송 ///////////////////////////////////////
    private fun Video_save() {
        Log.d(
            "RECORD/FAILURE ==============================================================================================",
            "Video_save 실행"
        )
        Log.d("RECORD/FAILURE ==================================", videoList[0].toString())
        val videoService = VideoService()
        videoService.setVideoResult(this)

        videoService.sendVideo(
            videoList,
            thumbnail!!,
            abuse_var.abuse.abuseIdx,
            childidx_var.childIdx.childIdx
        )
    }

    override fun postVideoSuccess(code: Int, result: com.umc.save.Record.Auth.Video.Result) {
        Toast.makeText(this, "영상 기록 성공.", Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", "영상 기록 성공.")

//        if(!binding.recordSelectedSpace.text.equals(""))
//            Recording_save()
    }

    override fun postVideoFailure(code: Int, message: String) {
        Toast.makeText(this, "영상 기록 실패.", Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", "영상 기록 실패.")
    }


    //////////////////////////////////// 오디오 전송 /////////////////////////////////////
    private fun Recording_save() {
        Log.d(
            "RECORD/FAILURE ==============================================================================================",
            "Recording_save 실행"
        )
        Log.d("RECORD/FAILURE ==================================", audioList[0].toString())
        val recordingPostService = RecordingPostService()
        recordingPostService.setRecordingResult(this)

        recordingPostService.sendRecording(
            audioList,
            abuse_var.abuse.abuseIdx,
            childidx_var.childIdx.childIdx
        )
    }


    override fun postRecordingSuccess(
        code: Int,
        result: com.umc.save.Record.Auth.Recording.Result
    ) {
        Toast.makeText(this, "녹음 기록 성공.", Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", "녹음 기록 성공.")
    }

    override fun NeedFile(code: Int, message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", message)
    }

    override fun postRecordingFailure(code: Int, message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", message)
    }

}
