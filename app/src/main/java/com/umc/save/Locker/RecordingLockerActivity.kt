package com.umc.save.Locker

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.umc.save.R
import com.umc.save.databinding.ActivityLockerPictureBinding
import com.umc.save.databinding.ActivityLockerPlayerBinding
import com.umc.save.databinding.ItemRecordingBinding
import java.util.concurrent.TimeUnit

class RecordingLockerActivity : AppCompatActivity() {

    lateinit var binding : ItemRecordingBinding

    private var player : ExoPlayer? = null
    var url = "https://dby56rj67jahx.cloudfront.net/recording/192470e0-2fcd-49d1-b2a0-6105b864a930.m4a"
//    var url="https://dby56rj67jahx.cloudfront.net/recording/958e5d70-91d2-402e-8a0e-f1fe49d45683.mp3"

    private val timeRunnable = Runnable {
        updateTime()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ItemRecordingBinding.inflate(layoutInflater)





//        initAudio(url)
//        play()

        binding.exoPlay.setOnClickListener {
            initAudio(url)
            play()
        }

        binding.exoPause.setOnClickListener {
            pause()
        }

        setContentView(binding.root)

    }


    override fun onStart() {
        super.onStart()
        initPlayer()

    }

    override fun onStop() {
        super.onStop()
        player?.release()
        player = null
    }

    private fun initPlayer() {

        player = ExoPlayer.Builder(this).build()
        binding.audioPlayerPv.player = player

        binding.let { binding ->
            player?.addListener(object: Player.Listener {
                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    super.onIsPlayingChanged(isPlaying)
                    if(isPlaying) {
                        binding.exoPlay.visibility = View.GONE
                        binding.exoPause.visibility = View.VISIBLE
                        binding.exoPosition.visibility = View.VISIBLE
                        binding.exoDuration.setTextColor(Color.parseColor("#D9D9D9"))
                        binding.exoPosition.setTextColor(Color.parseColor("#FFFFFFFF"))
                        binding.recordingTitleTv.setTextColor(Color.parseColor("#FFFFFFFF"))

                    } else {
                        binding.exoPlay.visibility = View.VISIBLE
                        binding.exoPause.visibility = View.GONE
                        binding.exoPosition.visibility = View.GONE
                        binding.exoDuration.setTextColor(Color.parseColor("#FF000000"))
                        binding.recordingTitleTv.setTextColor(Color.parseColor("#FF000000"))
                    }

                }

                override fun onPlaybackStateChanged(playbackState: Int) {
                    super.onPlaybackStateChanged(playbackState)
                    updateTime()
                }

            })
        }

    }

    private fun updateTime() {
        val player = this.player ?: return
        val duration = if(player.duration >= 0) player.duration else 0
        val position = player.currentPosition

        updateTimeUi(duration,position)

        val state = player.playbackState

//        this.runOnUiThread()
        binding.root.removeCallbacks(timeRunnable)
        if (state != Player.STATE_IDLE && state != Player.STATE_ENDED) {
            binding.root.postDelayed(timeRunnable,1000)
        }
    }

    private fun updateTimeUi(duration:Long, position: Long) {
        binding?.let { binding ->
            binding.exoDuration.text = String.format("%02d:%02d",
                TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS),
                (duration / 1000) % 60)
            binding.exoPosition.text = String.format("%02d:%02d",
                TimeUnit.MINUTES.convert(position, TimeUnit.MILLISECONDS),
                (position / 1000) % 60)
        }
    }

    private fun initAudio(url: String) {
        val dataSourceFactory = DefaultDataSource.Factory(this)
        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(Uri.parse(url)))
        player?.setMediaSource(mediaSource)

    }


    private fun play() {
        player?.prepare()
        player?.play()
    }


    private fun pause() {
        player?.pause()
//        player?.release()
//        player = null
    }


}

