package com.example.recycleview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
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
        Student[] student = {
                new Student("S01", "Ngo Hoai Nam", R.drawable.hinh_nen_cay_xanh_dep_nhat_103613238),
                new Student("S02", "Tran Van An", R.drawable.untitled),
                new Student("S03", "Le Thi Binh", R.drawable.z5644758578633_09494dd427673d00a3824e32ec1d6d73),
                new Student("S04", "Pham Quoc Bao", R.drawable.z5644758603727_d1920d0bbd50c27726f7d48e6e285d92),
                new Student("S05", "Nguyen Hong Ha", R.drawable.z5644758609823_e9290c6ddac7ddeba54f9f545a2cb81d),
                new Student("S06", "Doan Van Khoa", R.drawable.z5644758622142_0a28fef93291a2bdf725eceddfbb69ba),
                new Student("S07", "Vo Minh Tuan", R.drawable.z5644758603727_d1920d0bbd50c27726f7d48e6e285d92),
                new Student("S08", "Bui Thi Lan", R.drawable.untitled),
                new Student("S09", "Cao Van Hieu", R.drawable.hinh_nen_cay_xanh_dep_nhat_103613238),
                new Student("S10", "Dang Thi Phuong", R.drawable.ic_launcher_background)
        };
        recyclerView = (RecyclerView) findViewById(R.id.rcv_students);
        StudentAdapter adapter = new StudentAdapter(student);
        recyclerView.setAdapter(adapter);
    }
}