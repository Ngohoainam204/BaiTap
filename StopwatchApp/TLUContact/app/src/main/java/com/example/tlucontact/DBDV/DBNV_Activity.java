package com.example.tlucontact.DBDV;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlucontact.R;

public class DBNV_Activity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dbdv);

        recyclerView = findViewById(R.id.rcv_dbdv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Hiện Action Bar và thêm nút back
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Danh sách DBDV");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Nút back
        }

        DBDV[] dbdvs = {
                new DBDV(R.drawable.co_so_vat_chat_dai_hoc_thuy_loi_2, "Khoa CNTT", "0388802767", "C5"),
                new DBDV(R.drawable.co_so_vat_chat_dai_hoc_thuy_loi_2, "Khoa CNT", "0388802767", "A2"),
                new DBDV(R.drawable.co_so_vat_chat_dai_hoc_thuy_loi_2, "Khoa Kinh Tế", "0388802767", "A4"),
                new DBDV(R.drawable.co_so_vat_chat_dai_hoc_thuy_loi_2, "Khoa Cơ khí", "0388802767", "B5")
        };

        DBDV_Adapter adapter = new DBDV_Adapter(dbdvs);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(dbdv -> {
            Intent intent = new Intent(DBNV_Activity.this, DBDV_chitiet.class);
            intent.putExtra("AVATAR", dbdv.getImv_dbdv_avatar());
            intent.putExtra("NAME", dbdv.getName());
            intent.putExtra("PHONE_NUMBER", dbdv.getPhone_number());
            intent.putExtra("ADDRESS", dbdv.getAddress());
            startActivity(intent);
        });
    }

    // Xử lý sự kiện click nút back
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) { // Nút back mặc định
            finish(); // Đóng Activity hiện tại
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
