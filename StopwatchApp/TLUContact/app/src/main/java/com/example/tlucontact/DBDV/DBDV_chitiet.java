package com.example.tlucontact.DBDV;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tlucontact.R;

public class DBDV_chitiet extends AppCompatActivity {
    private ImageView imvAvatar;
    private TextView txtName, txtPhoneNumber, txtAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbdv_chitiet);

        // Kích hoạt nút Back trên ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Chi tiết Đơn vị");
        }

        // Ánh xạ các thành phần giao diện
        imvAvatar = findViewById(R.id.imv_dbdv_chitiet);
        txtName = findViewById(R.id.txt_name_dbdv_chitiet);
        txtPhoneNumber = findViewById(R.id.txt_phonenumber_dbdv_chitiet);
        txtAddress = findViewById(R.id.txt_address_dbdv_chitiet);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        if (intent != null) {
            txtName.setText(intent.getStringExtra("NAME"));
            txtPhoneNumber.setText(intent.getStringExtra("PHONE_NUMBER"));
            txtAddress.setText(intent.getStringExtra("ADDRESS"));
            imvAvatar.setImageResource(intent.getIntExtra("AVATAR", R.id.imv_dbdv_chitiet));
        }
    }

    // Xử lý sự kiện khi nhấn nút Back trên thanh công cụ
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
