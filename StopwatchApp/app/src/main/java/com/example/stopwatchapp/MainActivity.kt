package com.example.stopwatchapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvTimer: TextView
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button

    private var time = 0 // Biến lưu số giây đã trôi qua
    private var isRunning = false // Trạng thái của timer
    private val handler = Handler(Looper.getMainLooper()) // Handler để cập nhật UI

    private val runnable = object : Runnable {
        override fun run() {
            if (isRunning) {
                time++
                tvTimer.text = time.toString()
                handler.postDelayed(this, 1000) // Cập nhật sau mỗi giây
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ View
        tvTimer = findViewById(R.id.tvTimer)
        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnStop)

        // Xử lý sự kiện nút Start
        btnStart.setOnClickListener {
            if (!isRunning) {
                isRunning = true
                handler.post(runnable) // Bắt đầu cập nhật UI
            }
        }

        // Xử lý sự kiện nút Stop
        btnStop.setOnClickListener {
            isRunning = false
            handler.removeCallbacks(runnable) // Dừng cập nhật UI
        }
    }
}
