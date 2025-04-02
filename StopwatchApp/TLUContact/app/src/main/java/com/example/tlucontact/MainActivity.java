package com.example.tlucontact;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tlucontact.CBNV.CBNV_Activity;
import com.example.tlucontact.DBDV.DBNV_Activity;

public class MainActivity extends AppCompatActivity {
    private Button btn_dbdv;
    private Button btn_cbnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide(); // Ẩn Action Bar
        }
        btn_dbdv = findViewById(R.id.btn_dbdv);
        btn_cbnv = findViewById(R.id.btn_cbnv);
        //
        btn_dbdv.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DBNV_Activity.class);
            startActivity(intent);
        });
        btn_cbnv.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CBNV_Activity.class);
            startActivity(intent);
        });
        }
    }

