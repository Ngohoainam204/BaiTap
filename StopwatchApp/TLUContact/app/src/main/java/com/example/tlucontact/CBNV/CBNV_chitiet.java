package com.example.tlucontact.CBNV;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tlucontact.R;

public class CBNV_chitiet extends AppCompatActivity {

    private ImageView imvAvatar;
    private TextView txtName, txtPosition, txtPhoneNumber, txtEmail, txtDonVi;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbnv_chitiet);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Kích hoạt nút back
            getSupportActionBar().setTitle("Chi tiết CBNV"); // Tiêu đề trên Action Bar
        }

        // Ánh xạ view
        imvAvatar = findViewById(R.id.imv_cbnv_chitiet);
        txtName = findViewById(R.id.txt_name_cbnv_chitiet);
        txtPosition = findViewById(R.id.txt_position_cbnv_chitiet);
        txtPhoneNumber = findViewById(R.id.txt_phonenumber_cbnv_chitiet);
        txtEmail = findViewById(R.id.txt_email_cbnv_chitiet);
        txtDonVi = findViewById(R.id.txt_donvi_cbnv_chitiet);
        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("NAME");
            String position = intent.getStringExtra("POSITION");
            String phoneNumber = intent.getStringExtra("PHONE_NUMBER");
            String email = intent.getStringExtra("EMAIL");
            String donVi = intent.getStringExtra("DON_VI");
            int avatarResId = intent.getIntExtra("AVATAR", R.id.imv_cbnv_chitiet);

            // Hiển thị dữ liệu lên UI
            imvAvatar.setImageResource(avatarResId);
            txtName.setText(name);
            txtPosition.setText(position);
            txtPhoneNumber.setText(phoneNumber);
            txtEmail.setText(email);
            txtDonVi.setText(donVi);
        }
    }

    // Xử lý sự kiện nhấn nút back trên Action Bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Đóng activity và quay về trang trước
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
