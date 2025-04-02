package com.example.firebaseauthdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SuccessActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_success_activity)

        auth = FirebaseAuth.getInstance()

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val tvUserInfo = findViewById<TextView>(R.id.tvUserInfo)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // Lấy thông tin người dùng hiện tại
        val user = auth.currentUser
        if (user != null) {
            tvWelcome.text = "Chào mừng, ${user.email}!"
            tvUserInfo.text = "Email: ${user.email}"
        }

        // Xử lý đăng xuất
        btnLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
