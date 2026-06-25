package com.example.baitaplon_andori;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplon_andori.adapter.CauHoiAdapter;
import com.example.baitaplon_andori.adapter.CauHoiListener;
import com.example.baitaplon_andori.data.CauHoiDAO;
import com.example.baitaplon_andori.dialog.SuaCauHoiDialog;
import com.example.baitaplon_andori.dialog.ThemCauHoiDialog;
import com.example.baitaplon_andori.model.CauHoi;

public class CauHoiActivity extends AppCompatActivity
        implements CauHoiListener {

    private RecyclerView rvCauHoi;
    private Button btnThem;

    private CauHoiDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cauhoi);

        rvCauHoi = findViewById(R.id.rvCauHoi);
        btnThem = findViewById(R.id.btnThem);

        dao = new CauHoiDAO(this);

        loadData();

        btnThem.setOnClickListener(v -> {

            ThemCauHoiDialog dialog =
                    new ThemCauHoiDialog(
                            this,
                            () -> loadData()
                    );

            dialog.show();
        });
    }

    private void loadData() {

        rvCauHoi.setLayoutManager(
                new LinearLayoutManager(this)
        );

        rvCauHoi.setAdapter(
                new CauHoiAdapter(
                        this,
                        dao.getAll(),
                        this
                )
        );
    }

    @Override
    public void onEdit(CauHoi cauHoi) {

        SuaCauHoiDialog dialog =
                new SuaCauHoiDialog(
                        this,
                        cauHoi,
                        () -> loadData()
                );

        dialog.show();
    }

    @Override
    public void onDelete(CauHoi cauHoi) {

        new AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa")
                .setMessage(
                        "Bạn có muốn xóa câu hỏi này không?"
                )
                .setPositiveButton(
                        "Xóa",
                        (dialog, which) -> {

                            int result =
                                    dao.delete(
                                            cauHoi.getMaCauHoi()
                                    );

                            if (result > 0) {

                                Toast.makeText(
                                        this,
                                        "Xóa thành công",
                                        Toast.LENGTH_SHORT
                                ).show();

                                loadData();

                            } else {

                                Toast.makeText(
                                        this,
                                        "Xóa thất bại",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        }
                )
                .setNegativeButton(
                        "Hủy",
                        null
                )
                .show();
    }
}