package com.example.baitaplon_andori;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplon_andori.adapter.TuVungDrawableAdapter;
import com.example.baitaplon_andori.adapter.TuVungListener;
import com.example.baitaplon_andori.data.ChuDeDAO;
import com.example.baitaplon_andori.data.TuVungDAO;
import com.example.baitaplon_andori.dialog.SuaTuVungDrawableDialog;
import com.example.baitaplon_andori.model.ChuDe;
import com.example.baitaplon_andori.model.TuVung;

import java.util.ArrayList;

public class TuVungDrawable extends AppCompatActivity implements TuVungListener {

    private EditText edtTuAnh, edtNghia;
    private Spinner spinnerChuDe, spinnerAnh; // BỔ SUNG: spinnerAnh để chọn hình
    private Button btnThem;
    private ImageView imgPreview;
    private RecyclerView rvTuVung;

    private TuVungDAO dao;
    private ArrayList<ChuDe> listChuDe;

    // BỔ SUNG: Mảng danh sách các tên file ảnh Đức có sẵn trong thư mục res/drawable
    // Đức hãy sửa hoặc thêm bớt các tên file ảnh của bạn vào mảng này nhé (Lưu ý: Viết thường, không dấu, không cách)
    private final String[] mangTenAnh = {
            "ic_launcher_background", // Ảnh mặc định phòng hờ
            "chicken",
            "car",
            "dog",
            "apple",
            "cat",
            "banana",
            "moto"
    };

    private SuaTuVungDrawableDialog đangMoDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tu_vung);

        // 1. Ánh xạ View (Tái sử dụng luôn Spinner cũ của Đức trong XML)
        edtTuAnh = findViewById(R.id.edtTuAnh);
        edtNghia = findViewById(R.id.edtNghia);
        spinnerChuDe = findViewById(R.id.spinnerChuDe);

        // Mẹo: Đức mở file activity_tuvung.xml ra, đổi ID của ô nhập tên ảnh (hoặc nút chọn ảnh cũ) thành Spinner với ID là @+id/spinnerAnh nhé
        spinnerAnh = findViewById(R.id.spinnerAnh);

        btnThem = findViewById(R.id.btnThem);
        imgPreview = findViewById(R.id.imgPreview);
        rvTuVung = findViewById(R.id.rvTuVung);

        dao = new TuVungDAO(this);

        // 2. Tải dữ liệu các Spinner và RecyclerView
        loadChuDe();
        loadSpinnerAnh(); // Khởi tạo danh sách chọn ảnhrvTuVung
        loadData();

        // 3. XỬ LÝ LẮNG NGHE SPINNER ẢNH: Khi chọn tên ảnh nào trên Spinner, ImageView tự cập nhật hình đó luôn
        spinnerAnh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tenAnhDuocChon = mangTenAnh[position];
                int resId = getResources().getIdentifier(tenAnhDuocChon, "drawable", getPackageName());
                if (resId != 0) {
                    imgPreview.setImageResource(resId);
                } else {
                    imgPreview.setImageResource(R.drawable.ic_launcher_background);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // 4. Nghiệp vụ thêm từ vựng mới (C trong CRUD)
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

            // CƠ CHẾ MỚI: Lấy trực tiếp tên ảnh đang được chọn từ Spinner hình ảnh
            String tenAnhLuu = spinnerAnh.getSelectedItem().toString();
            tv.setHinhAnh(tenAnhLuu);

            ChuDe cd = listChuDe.get(spinnerChuDe.getSelectedItemPosition());
            tv.setMaChuDe(cd.getMaChuDe());

            long result = dao.insert(tv);
            if (result > 0) {
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                // Reset form
                edtTuAnh.setText("");
                edtNghia.setText("");
                spinnerAnh.setSelection(0); // Đưa spinner ảnh về mặc định đầu tiên
                imgPreview.setImageResource(R.drawable.ic_launcher_background);
                loadData();
            } else {
                Toast.makeText(this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Hàm thiết lập danh sách chữ cho Spinner Chọn ảnh
    private void loadSpinnerAnh() {
        ArrayAdapter<String> adapterAnh = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                mangTenAnh
        );
        spinnerAnh.setAdapter(adapterAnh);
    }

    private void loadChuDe() {
        ChuDeDAO cdDAO = new ChuDeDAO(this);
        listChuDe = (cdDAO.getAll() != null) ? new ArrayList<>(cdDAO.getAll()) : new ArrayList<>();

        ArrayList<String> ten = new ArrayList<>();
        for (ChuDe cd : listChuDe) {
            ten.add(cd.getTenChuDe());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ten);
        spinnerChuDe.setAdapter(adapter);
    }

    private void loadData() {
        try {
            rvTuVung.setLayoutManager(new LinearLayoutManager(this));
            rvTuVung.setAdapter(new TuVungDrawableAdapter(this, dao.getAll(), this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEdit(TuVung tv) {
        đangMoDialog = new SuaTuVungDrawableDialog(TuVungDrawable.this, tv, () -> loadData());
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