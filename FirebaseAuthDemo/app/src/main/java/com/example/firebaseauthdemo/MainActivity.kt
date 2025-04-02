package com.example.firebaseauthdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnShowData = findViewById<Button>(R.id.btnShowData)

        btnRegister.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isNotEmpty() && password.length >= 6) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val userId = auth.currentUser?.uid
                            val userRef = database.reference.child("Users").child(userId!!)
                            userRef.child("email").setValue(email)

                            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Lỗi: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Email không hợp lệ hoặc mật khẩu quá ngắn!", Toast.LENGTH_SHORT).show()
            }
        }

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, SuccessActivity::class.java))
                            finish()

                        } else {
                            Toast.makeText(this, "Lỗi: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
            }
        }

        btnShowData.setOnClickListener {
            val userId = auth.currentUser?.uid
            val userRef = database.reference.child("Users").child(userId!!)

            userRef.get().addOnSuccessListener { dataSnapshot ->
                val email = dataSnapshot.child("email").value.toString()
                Toast.makeText(this, "Email: $email", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Không lấy được dữ liệu!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
