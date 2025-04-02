package com.example.autoreply

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val PERMISSIONS_REQUEST_CODE = 1
    private lateinit var tvStatus: TextView
    private lateinit var btnToggleService: Button
    private var isAutoReplyEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvStatus = findViewById(R.id.tvStatus)
        btnToggleService = findViewById(R.id.btnToggleService)

        // Khôi phục trạng thái từ SharedPreferences
        val sharedPreferences = getSharedPreferences("AutoReplyPrefs", MODE_PRIVATE)
        isAutoReplyEnabled = sharedPreferences.getBoolean("isAutoReplyEnabled", false)
        updateUI()

        checkAndRequestPermissions()

        btnToggleService.setOnClickListener {
            isAutoReplyEnabled = !isAutoReplyEnabled

            // Lưu trạng thái vào SharedPreferences
            sharedPreferences.edit().putBoolean("isAutoReplyEnabled", isAutoReplyEnabled).apply()

            updateUI()
        }
    }

    private fun checkAndRequestPermissions() {
        val permissions = arrayOf(
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_CALL_LOG
        )

        val permissionsNeeded = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        if (permissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionsNeeded.toTypedArray(), PERMISSIONS_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                Toast.makeText(this, "Quyền đã được cấp!", Toast.LENGTH_SHORT).show()
            } else {
                showPermissionDeniedDialog()
            }
        }
    }

    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(this)
            .setTitle("Quyền bị từ chối")
            .setMessage("Ứng dụng cần quyền để hoạt động đúng. Vui lòng cấp quyền trong Cài đặt.")
            .setPositiveButton("Đi tới Cài đặt") { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data = android.net.Uri.parse("package:$packageName")
                startActivity(intent)
            }
            .setNegativeButton("Hủy", null)
            .show()
    }

    private fun updateUI() {
        if (isAutoReplyEnabled) {
            tvStatus.text = "Trạng thái: Đang bật"
            btnToggleService.text = "Tắt tự động trả lời"
        } else {
            tvStatus.text = "Trạng thái: Đang tắt"
            btnToggleService.text = "Bật tự động trả lời"
        }
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }
}
