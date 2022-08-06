package com.umc.save.Locker

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.umc.save.databinding.ActivityLockerPlayerBinding

class PlayerLockerActivity : AppCompatActivity() {

    lateinit var binding: ActivityLockerPlayerBinding
    private var player : ExoPlayer? = null
    var url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLockerPlayerBinding.inflate(layoutInflater)

        initPlayer()
        play(url)

        binding.closeIv.setOnClickListener {
            player?.release()
            player = null
            finish()
        }

        setContentView(binding.root)

    }

    private fun initPlayer() {
        player = ExoPlayer.Builder(this).build()
        binding.videoPlayerPv.player = player

    }

    override fun onStop() {
        super.onStop()
        player?.release()
        player = null
    }

//    url : String으로 인수 바꿔주기!!

// MediaSource: 영상에 출력할 미디어 정보를 가져오는 클래스
    private fun play(url : String) {
        val dataSourceFactory = DefaultDataSource.Factory(this)
        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(Uri.parse(url)))
        player?.setMediaSource(mediaSource)
        player?.prepare()
        player?.play()

    }

//    private fun play(video : Int) {
//        val mediaItem =
//        player.addMediaItem(mediaItem)
//        player.play()
//    }

}