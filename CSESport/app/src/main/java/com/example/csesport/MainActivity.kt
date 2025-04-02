package com.example.csesport

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import com.example.csesport.ui.theme.CSESportTheme
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Tham chieu
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val frameMain = findViewById<FrameLayout>(R.id.frame_main)
        //
        // Set fragment mặc định khi mở app
        if (savedInstanceState == null) {
            LoadFragment(CyclingFragment()) // Load fragment bạn muốn làm mặc định
            bottomNavigationView.selectedItemId = R.id.menu_item_cycling // Chọn item tương ứng
        }
        //Xu ly su kien
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menu_item_running -> {
                    Toast.makeText(this, "Running", Toast.LENGTH_SHORT).show()
                    LoadFragment(RunningFragment())
                    true
                }

                R.id.menu_item_cycling -> {
                    Toast.makeText(this, "Cycling", Toast.LENGTH_SHORT).show()
                    LoadFragment(CyclingFragment())
                    true
                }
                }

                true
            }
    }
    private fun LoadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_main, fragment)
            .commit()

    }
}