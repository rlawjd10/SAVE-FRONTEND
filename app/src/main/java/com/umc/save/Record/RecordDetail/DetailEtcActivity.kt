package com.umc.save.Record.RecordDetail

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.media.ThumbnailUtils
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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
import com.umc.save.Record.Auth.FileUtil
import com.umc.save.Record.Auth.Picture.PictureResult
import com.umc.save.Record.Auth.Picture.PictureService
import com.umc.save.Record.Auth.SuspectRecord.suspectIdx_var
import com.umc.save.databinding.ActivityDetailEtcBinding
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import kotlin.collections.HashMap

//, VideoResult, RecordingResult
class DetailEtcActivity : AppCompatActivity(), AbuseResult, PictureResult {

    private var PICK_IMAGE = 1
    private var PICK_VIDEO = 2
    private var PICK_AUDIO = 3

    var pictureList = ArrayList<MultipartBody.Part>()
//    lateinit var picture : MultipartBody.Part
    var videoList = ArrayList<MultipartBody.Part>()
    var audioList = ArrayList<MultipartBody.Part>()

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

        binding.btnNext.setOnClickListener {
//            abuse_save()
//            if(binding.pictureSelectedSpace.text != null)
            Image_save()
//            val lockerFragment = LockerFragment()
//            val fragment : Fragment? = supportFragmentManager.findFragmentByTag(LockerFragment::class.java.simpleName)
//            if(fragment !is LockerFragment){
//                supportFragmentManager.beginTransaction()
//                    .add(R.id.layout_content, lockerFragment, lockerFragment::class.java.simpleName)
//                    .commit()
//            }
//            binding.content.visibility = View.GONE
//            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    //////////////////////// 각 파일 생성 ////////////////////////

//    fun makeImageFile(path : Uri){
//        val file = File(path.toString())
//        var fileName = path.lastPathSegment
//        fileName = fileName + "png"
//
//        var requestBody : RequestBody = RequestBody.create("image/*".toMediaTypeOrNull(), file)
//
//        var body : MultipartBody.Part = MultipartBody.Part.create(requestBody)
//
//        pictureList.addAll(listOf(body))
//    }

    // 절대경로 변환
    fun absolutelyPath(path: Uri?, context : Context): String {
        var proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        var c: Cursor? = context.contentResolver.query(path!!, proj, null, null, null)
        var index = c?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c?.moveToFirst()

        var result = c?.getString(index!!)

        return result!!
    }

    private fun getAbuseSituation() : AbuseSituation {
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
//                var currentImageURL : Uri? = data?.data
//                var filename = currentImageURL?.lastPathSegment
//
//
//                binding.pictureSelectedSpace.setText(filename)
//
//                if (currentImageURL != null) {
//                    makeImageFile(currentImageURL)
//                    Toast.makeText(this, "사진 파일 생성", Toast.LENGTH_SHORT).show()
//                }

                val imagePath = data!!.data

                val file = File(absolutelyPath(imagePath, this))
                val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
                val body = MultipartBody.Part.createFormData("picture", file.name, requestFile)

                binding.pictureSelectedSpace.setText(file.name)

                Log.d("파일 생성!! ======== ", file.name)

                pictureList.addAll(listOf(body))

                Toast.makeText(this, "사진 첨부", Toast.LENGTH_SHORT).show()
            }

            if (requestCode == PICK_VIDEO) {
                var currentVideoURL : Uri? = data?.data
                var filename = currentVideoURL?.lastPathSegment
                var thumbnail = ThumbnailUtils.createVideoThumbnail(currentVideoURL.toString(), MediaStore.Video.Thumbnails.MICRO_KIND)

                binding.videoSelectedSpace.setText(filename)

                if(currentVideoURL != null)
//                    makeVideoFile(currentVideoURL)


                Toast.makeText(this, "영상 첨부", Toast.LENGTH_SHORT).show()
            }

            if (requestCode == PICK_AUDIO) {
                var currentAudioURL : Uri? = data?.data
                var filename = currentAudioURL?.lastPathSegment

                binding.recordSelectedSpace.setText(filename)

                if(currentAudioURL != null)
//                    makeAudioFile(currentAudioURL)

                Toast.makeText(this, "녹음 첨부", Toast.LENGTH_SHORT).show()
            }
        }
        else {
            Toast.makeText(this, "오류가 발생하였습니다.", Toast.LENGTH_SHORT).show()
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

    ///////////////////////////////////////////// 썸네일 생성 ///////////////////////////////////////
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

    ///////////////////////////////// 학대정황 전송 //////////////////////////////////////
    private fun abuse_save() {
        val abuseService = AbuseService()
        abuseService.setRecordResult(this)
        abuseService.record(getAbuseSituation())
    }

    override fun recordSuccess(result: Result) {
        abuse_var.abuse.abuseIdx = result.abuseIdx
        Log.d("저장한 거 ============================= ","날짜 : " + abuse_var.abuse.a_date +
                "\n 시간 :" + abuse_var.abuse.a_time + "\n abuseIdx : " + abuse_var.abuse.abuseIdx.toString())
        Toast.makeText(this, "학대 정황 기록 성공.", Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", "학대 정황 기록 성공.")

    }

    override fun recordFailure() {
        Toast.makeText(this, "학대 정황 기록 실패", Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", "학대 정황 기록 실패")
    }


    /////////////////////////////// 이미지 전송 ///////////////////////////////////////////
    private fun Image_save(){
        Log.d("RECORD/FAILURE ==============================================================================================", "Image_save 실행")
        Log.d("RECORD/FAILURE ==================================", pictureList[0].toString())
        val pictureService = PictureService()
        pictureService.setPicturedResult(this)
        pictureService.sendPicture(pictureList, abuse_var.abuse.abuseIdx, childidx_var.childIdx.childIdx)
    }

//    private fun getPictureSituation() : RequestBody{
//        var picAbuseIdx = abuse_var.abuse.abuseIdx
//        var picChildIdx = childidx_var.childIdx.childIdx
//
//        val jsonObject = JSONObject("{\"picAbuseIdx\" :\"${picAbuseIdx}\", \"picChildIdx\" :\"${picChildIdx}\"}").toString()
//        val jsonBody = RequestBody.create("application/json".toMediaTypeOrNull(),jsonObject)
//        return jsonBody
//    }

    override fun postPictureSuccess(code: Int, result: com.umc.save.Record.Auth.Picture.Result) {
        Toast.makeText(this, "이미지 기록 성공.", Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", "이미지 기록 성공.")
    }

    override fun postPictureFailure(code: Int, message: String) {
        Toast.makeText(this, "이미지 기록 실패.", Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", "이미지 기록 실패.")
    }

//
//    ////////////////////////////////////// 비디오 전송 ///////////////////////////////////////
//    private fun Video_save(){
//        val videoService = VideoService()
//        videoService.setVideodResult(this)
//        videoService.postPicture(getVideoSituation())
//    }
//
//    private fun getVideoSituation() : Video {
//        var video = videoList
//        var videoAbuseIdx = abuse_var.abuse.abuseIdx
//        var videoChildIdx = childidx_var.childIdx.childIdx
//
//        return Video(video, videoAbuseIdx, videoChildIdx)
//    }
//
//    override fun postVideoSuccess(code: Int, result: com.umc.save.Record.Auth.Video.Result) {
//        Toast.makeText(this, "비디오 기록 성공.", Toast.LENGTH_SHORT).show()
//        Log.d("RECORD/FAILURE", "비디오 기록 성공.")
//    }
//
//    override fun postVideoFailure(code: Int, message: String) {
//        Toast.makeText(this, "비디오 기록 성공.", Toast.LENGTH_SHORT).show()
//        Log.d("RECORD/FAILURE", "비디오 기록 성공.")
//    }
//
//    //////////////////////////////////// 오디오 전송 /////////////////////////////////////
//    private fun Recording_save(){
//        val recordingPostService = RecordingPostService()
//        recordingPostService.setRecordingResult(this)
//        recordingPostService.postRecording(getRecordingSituation())
//    }
//
//    private fun getRecordingSituation() : Recording {
//        var recording = audioList
//        var recordingAbuseIdx = abuse_var.abuse.abuseIdx
//        var recordingChildIdx = childidx_var.childIdx.childIdx
//
//        return Recording(recording, recordingAbuseIdx, recordingChildIdx)
//    }
//
//    override fun postRecordingSuccess(
//        code: Int,
//        result: com.umc.save.Record.Auth.Recording.Result
//    ) {
//        Toast.makeText(this, "녹음 기록 성공.", Toast.LENGTH_SHORT).show()
//        Log.d("RECORD/FAILURE", "녹음 기록 성공.")
//    }
//
//    override fun postRecordingFailure(code: Int, message: String) {
//        Toast.makeText(this, "녹음 기록 성공.", Toast.LENGTH_SHORT).show()
//        Log.d("RECORD/FAILURE", "녹음 기록 성공.")
//    }

}
