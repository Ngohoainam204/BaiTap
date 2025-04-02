package com.example.oursharedpreferences

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.oursharedpreferences.ui.theme.OurSharedPreferencesTheme

class MainActivity : ComponentActivity() {
    //Khai báo
    private lateinit var edtPhone: EditText
    private lateinit var btnSave: Button
    private lateinit var btnLoad: Button
    private lateinit var txtInfo: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.acitivity_main)
        //
        edtPhone = findViewById(R.id.edt_phone)
        btnSave = findViewById(R.id.btn_save)
        btnLoad = findViewById(R.id.btn_load)
        txtInfo = findViewById(R.id.txt_info)
        //
        btnSave.setOnClickListener {
            val phone = edtPhone.text.toString()
            val sharedPreferences = getSharedPreferences("phone_book", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("phone", phone)
            editor.apply()
        }
        btnLoad.setOnClickListener {
            val sharedPreferences = getSharedPreferences("phone_book", MODE_PRIVATE)
            val phone = sharedPreferences.getString("phone", "")
            txtInfo.setText(phone)
        }
    }
}

