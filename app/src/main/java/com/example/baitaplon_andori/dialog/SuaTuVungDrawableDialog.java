package com.example.baitaplon_andori.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class SuaTuVungDrawableDialog extends Dialog {

    private Activity activity;
    private TuVung tv;
    private Runnable reload;

    private ArrayList<ChuDe> listChuDe;
    private ImageView img;
    private Spinner spinnerSuaAnh; // Khai báo Spinner chọn ảnh mới bổ sung

    // ĐỒNG BỘ: Mảng danh sách tên file ảnh trong thư mục res/drawable (Đức nhớ để trùng khớp với mảng bên TuVungDrawable.java nhé)
    private final String[] mangTenAnh = {
            "ic_launcher_background",
            "chicken",
            "car",
            "dog",
            "apple",
            "cat",
            "banana",
            "moto"
    };

    public SuaTuVungDrawableDialog(Activity activity, TuVung tv, Runnable reload) {
        super(activity);
        this.activity = activity;
        this.tv = tv;
        this.reload = reload;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sua_tuvung);

        // 1. Ánh xạ các thành phần giao diện
        EditText edtTu = findViewById(R.id.edtSuaTuAnh);
        EditText edtNghia = findViewById(R.id.edtSuaNghia);
        img = findViewById(R.id.imgSua);
        Button btnLuu = findViewById(R.id.btnLuuSua);
        Spinner spinnerSuaChuDe = findViewById(R.id.spSuaChuDe);
        spinnerSuaAnh = findViewById(R.id.spSuaAnh); // Ánh xạ Spinner ảnh từ XML mới của Đức

        // 2. Điền dữ liệu text cũ vào Form
        edtTu.setText(tv.getTuTiengAnh());
        edtNghia.setText(tv.getNghiaTiengViet());

        // 3. Cài đặt dữ liệu cho Spinner Chọn hình ảnh mới
        ArrayAdapter<String> adapterAnh = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, mangTenAnh);
        spinnerSuaAnh.setAdapter(adapterAnh);

        // Đặt vị trí Spinner trỏ sẵn vào đúng tên ảnh cũ của từ vựng này
        if (tv.getHinhAnh() != null) {
            for (int i = 0; i < mangTenAnh.length; i++) {
                if (mangTenAnh[i].equals(tv.getHinhAnh())) {
                    spinnerSuaAnh.setSelection(i);
                    break;
                }
            }
        }

        // Lắng nghe sự kiện chọn hình ảnh trên Spinner để thay đổi ảnh hiển thị xem trước tức thì
        spinnerSuaAnh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tenAnhDuocChon = mangTenAnh[position];
                int resId = activity.getResources().getIdentifier(tenAnhDuocChon, "drawable", activity.getPackageName());
                if (resId != 0) {
                    img.setImageResource(resId);
                } else {
                    img.setImageResource(R.drawable.ic_launcher_background);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // 4. Cấu hình danh mục dữ liệu cho Spinner Chọn chủ đề
        ChuDeDAO cdDAO = new ChuDeDAO(activity);
        listChuDe = (cdDAO.getAll() != null) ? new ArrayList<>(cdDAO.getAll()) : new ArrayList<>();

        ArrayList<String> tenChuDe = new ArrayList<>();
        for (ChuDe cd : listChuDe) {
            tenChuDe.add(cd.getTenChuDe());
        }

        ArrayAdapter<String> adapterChuDe = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, tenChuDe);
        spinnerSuaChuDe.setAdapter(adapterChuDe);

        // Đặt mặc định chọn mục tương ứng với mã chủ đề cũ
        for (int i = 0; i < listChuDe.size(); i++) {
            if (listChuDe.get(i).getMaChuDe() == tv.getMaChuDe()) {
                spinnerSuaChuDe.setSelection(i);
                break;
            }
        }

        // 5. Nghiệp vụ lưu dữ liệu sau khi sửa đổi
        btnLuu.setOnClickListener(v -> {
            String tuMoi = edtTu.getText().toString().trim();
            String nghiaMoi = edtNghia.getText().toString().trim();

            if (tuMoi.isEmpty() || nghiaMoi.isEmpty()) {
                Toast.makeText(activity, "Không được để trống thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (listChuDe.isEmpty() || spinnerSuaChuDe.getSelectedItemPosition() == -1) {
                Toast.makeText(activity, "Lỗi: Danh mục hệ thống trống!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Gán dữ liệu mới cập nhật
            tv.setTuTiengAnh(tuMoi);
            tv.setNghiaTiengViet(nghiaMoi);

            // CƠ CHẾ ĐỒNG BỘ: Lấy chính xác tên ảnh đang chọn từ Spinner sửa đổi để lưu vào database
            String tenAnhMoi = spinnerSuaAnh.getSelectedItem().toString();
            tv.setHinhAnh(tenAnhMoi);

            int viTriDuocChon = spinnerSuaChuDe.getSelectedItemPosition();
            ChuDe cdSelected = listChuDe.get(viTriDuocChon);
            tv.setMaChuDe(cdSelected.getMaChuDe());

            // Thực thi lưu xuống SQLite
            TuVungDAO dao = new TuVungDAO(activity);
            dao.update(tv);

            reload.run(); // Làm mới RecyclerView danh sách ngoài màn hình chính
            dismiss();    // Đóng hẳn hộp thoại popup
            Toast.makeText(activity, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
        });
    }
}