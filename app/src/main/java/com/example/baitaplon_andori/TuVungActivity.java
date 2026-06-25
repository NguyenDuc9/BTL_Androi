package com.example.baitaplon_andori;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplon_andori.adapter.TuVungAdapter;
import com.example.baitaplon_andori.adapter.TuVungListener;
import com.example.baitaplon_andori.data.ChuDeDAO;
import com.example.baitaplon_andori.data.TuVungDAO;
import com.example.baitaplon_andori.dialog.SuaTuVungDialog;
import com.example.baitaplon_andori.model.ChuDe;
import com.example.baitaplon_andori.model.TuVung;

import java.util.ArrayList;

public class TuVungActivity extends AppCompatActivity implements TuVungListener {

    private EditText edtTuAnh;
    private EditText edtNghia;
    private Spinner spinnerChuDe;
    private Button btnThem;
    private Button btnChonAnh;
    private ImageView imgPreview;
    private RecyclerView rvTuVung;

    private TuVungDAO dao;
    private ArrayList<ChuDe> listChuDe;
    private String imagePath = "";

    // BỔ SUNG: Biến toàn cục để quản lý Dialog đang mở (giúp đẩy ảnh mới vào Dialog khi sửa)
    private SuaTuVungDialog đangMoDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuvung);

        // Ánh xạ
        edtTuAnh = findViewById(R.id.edtTuAnh);
        edtNghia = findViewById(R.id.edtNghia);
        spinnerChuDe = findViewById(R.id.spinnerChuDe);
        btnThem = findViewById(R.id.btnThem);
        btnChonAnh = findViewById(R.id.btnChonAnh);
        imgPreview = findViewById(R.id.imgPreview);
        rvTuVung = findViewById(R.id.rvTuVung);

        dao = new TuVungDAO(this);

        loadChuDe();
        loadData();

        // Chọn ảnh khi THÊM từ vựng
        btnChonAnh.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 100);
        });

        // Thêm từ vựng
        btnThem.setOnClickListener(v -> {
            String tu = edtTuAnh.getText().toString().trim();
            String nghia = edtNghia.getText().toString().trim();

            if (tu.isEmpty() || nghia.isEmpty()) {
                Toast.makeText(this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (listChuDe == null || listChuDe.isEmpty() || spinnerChuDe.getSelectedItemPosition() == AdapterView.INVALID_POSITION) {
                Toast.makeText(this, "Vui lòng thêm chủ đề trước!", Toast.LENGTH_SHORT).show();
                return;
            }

            TuVung tv = new TuVung();
            tv.setTuTiengAnh(tu);
            tv.setNghiaTiengViet(nghia);
            tv.setHinhAnh(imagePath);

            ChuDe cd = listChuDe.get(spinnerChuDe.getSelectedItemPosition());
            tv.setMaChuDe(cd.getMaChuDe());

            long result = dao.insert(tv);
            if (result > 0) {
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                edtTuAnh.setText("");
                edtNghia.setText("");
                imgPreview.setImageResource(R.drawable.ic_launcher_background);
                imagePath = "";
                loadData();
            }
        });
    }

    private void loadChuDe() {
        ChuDeDAO cdDAO = new ChuDeDAO(this);

        if (cdDAO.getAll() != null) {
            listChuDe = new ArrayList<>(cdDAO.getAll());
        } else {
            listChuDe = new ArrayList<>();
        }

        ArrayList<String> ten = new ArrayList<>();
        for (ChuDe cd : listChuDe) {
            ten.add(cd.getTenChuDe());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                ten
        );
        spinnerChuDe.setAdapter(adapter);
    }

    private void loadData() {
        rvTuVung.setLayoutManager(new LinearLayoutManager(this));
        rvTuVung.setAdapter(new TuVungAdapter(this, dao.getAll(), this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 1. Nhận ảnh cho chức năng THÊM (RequestCode = 100)
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            if (uri != null) {

                try {
                    getContentResolver().takePersistableUriPermission(
                            uri,
                            Intent.FLAG_GRANT_READ_URI_PERMISSION
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }

                imagePath = uri.toString();
                imgPreview.setImageURI(uri);

                Toast.makeText(this, "Đã chọn ảnh", Toast.LENGTH_SHORT).show();
            }
        }

        // 2. BỔ SUNG: Nhận ảnh cho chức năng SỬA (RequestCode = 101)
        if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            if (uri != null && đangMoDialog != null && đangMoDialog.isShowing()) {
                // Đẩy chuỗi URI ảnh mới chạy thẳng vào trong Dialog để cập nhật giao diện hiển thị
                đangMoDialog.setAnhMoi(uri.toString());
                Toast.makeText(this, "Đã thay đổi ảnh sửa", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onEdit(TuVung tv) {
        // SỬA: Gán Dialog vào biến toàn cục để hàm onActivityResult ở trên có thể gọi tới
        đangMoDialog = new SuaTuVungDialog(this, tv, () -> loadData());
        đangMoDialog.show();
    }

    @Override
    public void onDelete(TuVung tv) {
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa")
                .setMessage("Bạn muốn xóa từ: " + tv.getTuTiengAnh())
                .setPositiveButton("Xóa", (dialog, which) -> {
                    dao.delete(tv.getMaTu());
                    loadData();
                    Toast.makeText(this, "Đã xóa", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Hủy", null)
                .show();
    }
}