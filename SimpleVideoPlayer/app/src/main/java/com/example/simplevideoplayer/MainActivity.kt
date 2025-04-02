package com.example.simplevideoplayer

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.VideoView
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var edtVideoUrl: EditText
    private lateinit var btnPlayUrl: Button
    private lateinit var btnPickVideo: Button

    private val PICK_VIDEO_REQUEST = 1 // Request code để chọn video

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ view
        videoView = findViewById(R.id.videoView)
        edtVideoUrl = findViewById(R.id.edtVideoUrl)
        btnPlayUrl = findViewById(R.id.btnPlayUrl)
        btnPickVideo = findViewById(R.id.btnPickVideo)

        // Thêm MediaController để điều khiển video
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        // Phát video từ URL
        btnPlayUrl.setOnClickListener {
            val videoUrl = edtVideoUrl.text.toString()
            if (videoUrl.isNotEmpty()) {
                playVideo(Uri.parse(videoUrl))
            }
        }

        // Chọn video từ thiết bị
        btnPickVideo.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_VIDEO_REQUEST)
        }
    }

    // Nhận kết quả khi chọn video từ thiết bị
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_VIDEO_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedVideoUri = data.data
            if (selectedVideoUri != null) {
                playVideo(selectedVideoUri)
            }
        }
    }

    // Phát video
    private fun playVideo(videoUri: Uri) {
        videoView.setVideoURI(videoUri)
        videoView.start()
    }
}
