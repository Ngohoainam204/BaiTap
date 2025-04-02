package com.example.tlucontact.CBNV;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlucontact.R;

public class CBNV_Activity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private Button btn_search_cbnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cbnv);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Danh sách CBNV");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Thêm nút back

        }
        recyclerView = findViewById(R.id.rcv_cbnv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btn_search_cbnv = findViewById(R.id.btn_search_cbnv);

        //

        CBNV[] cbnvs = {
                new CBNV(R.drawable.nam,
                        "Ngô Hoài Nam",
                        "0388802767",
                        "Đại ca",
                        "ngonam2k4@gmail.com",
                        "Ha Noi"),
                new CBNV(R.drawable.hieu,
                        "Đặng Bá Hiệu",
                        "0375380927",
                        "Rót nước",
                        "Bahieu204@gmail.com",
                        "Ha Dong"),
                new CBNV(R.drawable.hai,
                        "Nguyễn Xuân Hải",
                        "0327772970",
                        "Bổ cam",
                        "NXHai2k4@gmail.com",
                        "Soc Son")
        };

        CBNV_Adapter adapter = new CBNV_Adapter(cbnvs);
        recyclerView.setAdapter(adapter);

        // Bắt sự kiện click
        adapter.setOnItemClickListener(cbnv -> {
            Intent intent = new Intent(CBNV_Activity.this, CBNV_chitiet.class);
            intent.putExtra("AVATAR", cbnv.getImv_cbnv_avatar());
            intent.putExtra("NAME", cbnv.getName());
            intent.putExtra("POSITION", cbnv.getPosition());
            intent.putExtra("PHONE_NUMBER", cbnv.getPhone_number());
            intent.putExtra("EMAIL", cbnv.getEmail());
            intent.putExtra("DON_VI", cbnv.getDonvi());
            startActivity(intent);
        });
        //
        btn_search_cbnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popupView = getLayoutInflater().inflate(R.layout.popup_search_cbnv, null);

                // Tạo PopupWindow
                PopupWindow popupWindow = new PopupWindow(popupView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        true);

                // Hiển thị popup phía trên nút tìm kiếm
                popupWindow.showAsDropDown(v, 60, -v.getHeight() - 750);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) { // Nút back mặc định
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
