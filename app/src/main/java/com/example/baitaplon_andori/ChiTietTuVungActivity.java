package com.example.baitaplon_andori;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baitaplon_andori.data.TuVungDAO;
import com.example.baitaplon_andori.model.TuVung;

import java.util.List;

public class ChiTietTuVungActivity extends AppCompatActivity {

    private ImageView imgVocabulary;
    private TextView tvEnglish, tvVietnamese;
    private Button btnShowMeaning, btnPrevious, btnNext;

    private List<TuVung> listTuVung;
    private int currentIndex = 0;
    private TuVungDAO tuVungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_tuvung);

        // Ánh xạ các thành phần giao diện từ XML có sẵn của bạn
        imgVocabulary = findViewById(R.id.imgVocabulary);
        tvEnglish = findViewById(R.id.tvEnglish);
        tvVietnamese = findViewById(R.id.tvVietnamese);
        btnShowMeaning = findViewById(R.id.btnShowMeaning);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        // Lấy mã đề tài được gửi từ ChuDeUserAdapter sang
        int maChuDe = getIntent().getIntExtra("MA_CHU_DE", -1);

        tuVungDAO = new TuVungDAO(this);
        // Gọi hàm lọc theo mã chủ đề vừa thêm ở bước 1
        listTuVung = tuVungDAO.getByMaChuDe(maChuDe);

        // Kiểm tra dữ liệu danh sách list học
        if (listTuVung != null && !listTuVung.isEmpty()) {
            // Mặc định hiển thị từ đầu tiên trong mảng list (vị trí 0)
            updateWordUI();
        } else {
            Toast.makeText(this, "Chủ đề này chưa có từ vựng nào!", Toast.LENGTH_SHORT).show();
            finish();
        }

        // Click hiện nghĩa tiếng Việt
        btnShowMeaning.setOnClickListener(v -> {
            tvVietnamese.setVisibility(View.VISIBLE);
        });

        // Click chuyển sang từ tiếp theo
        btnNext.setOnClickListener(v -> {
            if (currentIndex < listTuVung.size() - 1) {
                currentIndex++;
                updateWordUI();
            } else {
                Toast.makeText(this, "Đã đến từ cuối cùng của đề tài!", Toast.LENGTH_SHORT).show();
            }
        });

        // Click quay lại từ trước đó
        btnPrevious.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                updateWordUI();
            } else {
                Toast.makeText(this, "Đây đã là từ đầu tiên!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Hàm cập nhật trạng thái flashcard dựa vào currentIndex
    private void updateWordUI() {
        TuVung currentWord = listTuVung.get(currentIndex);

        // Set text sử dụng chính xác hàm trong model TuVung của bạn
        tvEnglish.setText(currentWord.getTuTiengAnh());
        tvVietnamese.setText(currentWord.getNghiaTiengViet());

        // Yêu cầu: Ẩn nghĩa tiếng Việt đi khi mới lật sang từ mới
        tvVietnamese.setVisibility(View.GONE);

        // Hiển thị ảnh từ thư mục res/drawable thông qua chuỗi string tên ảnh lưu trong DB
        int resId = getResources().getIdentifier(currentWord.getHinhAnh(), "drawable", getPackageName());
        if (resId != 0) {
            imgVocabulary.setImageResource(resId);
        } else {
            imgVocabulary.setImageResource(R.mipmap.ic_launcher); // Ảnh mặc định dự phòng
        }
    }
}