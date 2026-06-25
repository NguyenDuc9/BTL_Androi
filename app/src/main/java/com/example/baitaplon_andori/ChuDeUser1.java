package com.example.baitaplon_andori;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChuDeUser1 extends AppCompatActivity {

    // Chỉ khai báo 4 nút bấm chính thức, đã bỏ hoàn toàn btnAnimal
    private Button btnFood, btnVehicle, btnJob, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chu_de);

        // CHỐNG SẬP: Kiểm tra an toàn tính năng tràn viền (EdgeToEdge)
        // Nếu trong file XML chưa đặt id="main", ứng dụng vẫn chạy tiếp mượt mà
        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // Ánh xạ chính xác 4 nút bấm còn lại
        btnFood = findViewById(R.id.btnFood);
        btnVehicle = findViewById(R.id.btnVehicle);
        btnJob = findViewById(R.id.btnJob);
        btnHome = findViewById(R.id.btnHome);

        // Sự kiện click nút Đồ ăn (Food)
        if (btnFood != null) {
            btnFood.setOnClickListener(v -> {
                Intent intent = new Intent(ChuDeUser1.this, DoAnActivity.class);
                startActivity(intent);
            });
        }

        // Sự kiện click nút Phương tiện (Vehicle)
        if (btnVehicle != null) {
            btnVehicle.setOnClickListener(v -> {
                Intent intent = new Intent(ChuDeUser1.this, PhuongTienActivity.class);
                startActivity(intent);
            });
        }

        // Sự kiện click nút Việc làm (Job)
        if (btnJob != null) {
            btnJob.setOnClickListener(v -> {
                Intent intent = new Intent(ChuDeUser1.this, ViecLamActivity.class);
                startActivity(intent);
            });
        }

        // Sự kiện click nút Quay lại trang chủ (Home)
        if (btnHome != null) {
            btnHome.setOnClickListener(v -> {
                Intent intent = new Intent(ChuDeUser1.this, HomeActivity.class);
                startActivity(intent);
                finish(); // Đóng hẳn màn hình ChuDeUser1 để giải phóng RAM
            });
        }
    }
}