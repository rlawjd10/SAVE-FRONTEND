package com.umc.save.Locker.Recording

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.umc.save.databinding.ItemRecordingBinding
import java.util.concurrent.TimeUnit

class RecordingLockerActivity : AppCompatActivity() {

    lateinit var binding : ItemRecordingBinding
    private var player : ExoPlayer? = null
    private val timeRunnable = Runnable {
        updateTime()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ItemRecordingBinding.inflate(layoutInflater)

        val recordingTitle = intent.getStringExtra("recordingTitle")
        binding.recordingTitleTv.text = recordingTitle

        binding.exoPlay.setOnClickListener {
            play()
        }

        binding.exoPause.setOnClickListener {
            pause()
        }

        binding.closeIv.setOnClickListener {
            player?.release()
            player = null
            finish()
        }

        setContentView(binding.root)

    }


    override fun onStart() {
        super.onStart()
        initPlayer()
        initAudio()
        play()

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
                        binding.exoDuration.setTextColor(Color.parseColor("#FF000000"))
                        binding.exoPosition.setTextColor(Color.parseColor("#B5B5B5"))
                        binding.recordingTitleTv.setTextColor(Color.parseColor("#FF000000"))

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

    private fun initAudio() {

        val recordingUrl = intent.getStringExtra("recordingUrl")

        val dataSourceFactory = DefaultDataSource.Factory(this)
        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(Uri.parse(recordingUrl)))
        player?.setMediaSource(mediaSource)

    }


    private fun play() {
        player?.prepare()
        player?.play()
    }


    private fun pause() {
        player?.pause()
    }


}

