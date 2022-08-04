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
import com.umc.save.R
import com.umc.save.databinding.ActivityLockerPlayerBinding

class TestPlayerLockerActivity : AppCompatActivity() {

    lateinit var binding: ActivityLockerPlayerBinding
    private var player : ExoPlayer? = null
    var url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLockerPlayerBinding.inflate(layoutInflater)

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

    private fun playVideo(url : String) {
        val dataSourceFactory = DefaultDataSource.Factory(this)
        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(Uri.parse(url)))

        player?.setMediaSource(mediaSource)
        player?.prepare()
        player?.play()
    }

    override fun onStart() {
        super.onStart()
        initPlayer()
        playVideo(url)
    }

    override fun onStop() {
        super.onStop()
        player?.release()
        player = null
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
        player = null
    }

//    url : String으로 인수 바꿔주기!!

}