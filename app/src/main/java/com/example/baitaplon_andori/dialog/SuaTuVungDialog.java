package com.example.baitaplon_andori.dialog;

import android.app.Activity; // Dùng trực tiếp Activity thay vì Context
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.baitaplon_andori.R;
import com.example.baitaplon_andori.data.ChuDeDAO;
import com.example.baitaplon_andori.data.TuVungDAO;
import com.example.baitaplon_andori.model.ChuDe;
import com.example.baitaplon_andori.model.TuVung;

import java.util.ArrayList;

public class SuaTuVungDialog extends Dialog {

    private Activity activity; // SỬA: Đổi từ Context thành Activity toàn cục
    private TuVung tv;
    private Runnable reload;
    private String anhMoi = "";

    private ArrayList<ChuDe> listChuDe;
    private ImageView img;

    // SỬA: Khởi tạo nhận vào Activity thay vì Context để gọi lệnh mượt mà trên máy thật
    public SuaTuVungDialog(Activity activity, TuVung tv, Runnable reload) {
        super(activity);
        this.activity = activity;
        this.tv = tv;
        this.reload = reload;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sua_tuvung);

        // 1. Ánh xạ các View từ XML
        EditText edtTu = findViewById(R.id.edtSuaTuAnh);
        EditText edtNghia = findViewById(R.id.edtSuaNghia);
        img = findViewById(R.id.imgSua);
        Button btnLuu = findViewById(R.id.btnLuuSua);
        Spinner spinnerSuaChuDe = findViewById(R.id.spSuaChuDe);

        // 2. Điền dữ liệu văn bản cũ
        edtTu.setText(tv.getTuTiengAnh());
        edtNghia.setText(tv.getNghiaTiengViet());

        // Điền ảnh cũ (Có bọc try-catch tránh crash data mẫu)
        if (tv.getHinhAnh() != null && !tv.getHinhAnh().isEmpty()) {
            try {
                img.setImageURI(Uri.parse(tv.getHinhAnh()));
            } catch (Exception e) {
                img.setImageResource(R.drawable.ic_launcher_background);
            }
        }

        // ĐOẠN SỬA LỖI: Sử dụng trực tiếp đối tượng 'activity' để mở thư viện hình ảnh
        img.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activity.startActivityForResult(intent, 101); // Gọi trực tiếp từ activity gốc không qua ép kiểu
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(activity, "Không mở được bộ sưu tập ảnh!", Toast.LENGTH_SHORT).show();
            }
        });

        // 3. Đổ dữ liệu vào Spinner chủ đề
        ChuDeDAO cdDAO = new ChuDeDAO(activity);
        if (cdDAO.getAll() != null) {
            listChuDe = new ArrayList<>(cdDAO.getAll());
        } else {
            listChuDe = new ArrayList<>();
        }

        ArrayList<String> tenChuDe = new ArrayList<>();
        for (ChuDe cd : listChuDe) {
            tenChuDe.add(cd.getTenChuDe());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, tenChuDe);
        spinnerSuaChuDe.setAdapter(adapter);

        // Tự động trỏ Spinner đến đúng chủ đề hiện tại của từ
        for (int i = 0; i < listChuDe.size(); i++) {
            if (listChuDe.get(i).getMaChuDe() == tv.getMaChuDe()) {
                spinnerSuaChuDe.setSelection(i);
                break;
            }
        }

        // 5. Xử lý sự kiện bấm nút Lưu chỉnh sửa
        btnLuu.setOnClickListener(v -> {
            String tuMoi = edtTu.getText().toString().trim();
            String nghiaMoi = edtNghia.getText().toString().trim();

            if (tuMoi.isEmpty() || nghiaMoi.isEmpty()) {
                Toast.makeText(activity, "Không được để trống thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (listChuDe.isEmpty() || spinnerSuaChuDe.getSelectedItemPosition() == -1) {
                Toast.makeText(activity, "Lỗi: Hệ thống chưa có chủ đề nào!", Toast.LENGTH_SHORT).show();
                return;
            }

            tv.setTuTiengAnh(tuMoi);
            tv.setNghiaTiengViet(nghiaMoi);

            // Gán ảnh mới nếu có thay đổi
            if (!anhMoi.isEmpty()) {
                tv.setHinhAnh(anhMoi);
            }

            int viTriDuocChon = spinnerSuaChuDe.getSelectedItemPosition();
            ChuDe cdSelected = listChuDe.get(viTriDuocChon);
            tv.setMaChuDe(cdSelected.getMaChuDe());

            TuVungDAO dao = new TuVungDAO(activity);
            dao.update(tv);

            reload.run(); // Làm mới RecyclerView ở Activity ngoài
            dismiss();    // Đóng dialog
        });
    }

    // Nhận đường dẫn ảnh mới từ Activity nạp vào hiển thị nội bộ trên Dialog
    public void setAnhMoi(String path) {
        this.anhMoi = path;
        if (img != null && !path.isEmpty()) {
            try {
                img.setImageURI(Uri.parse(path));
            } catch (Exception e) {
                img.setImageResource(R.drawable.ic_launcher_background);
                e.printStackTrace();
            }
        }
    }
}