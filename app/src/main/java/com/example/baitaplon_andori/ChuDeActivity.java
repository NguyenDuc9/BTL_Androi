package com.example.baitaplon_andori;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.baitaplon_andori.adapter.ChuDeAdapter;
import com.example.baitaplon_andori.adapter.ChuDeListener;
import com.example.baitaplon_andori.data.ChuDeDAO;
import com.example.baitaplon_andori.dialog.SuaChuDeDialog;
import com.example.baitaplon_andori.model.ChuDe;



public class ChuDeActivity extends AppCompatActivity
        implements ChuDeListener {


    private RecyclerView rvChuDe;

    private EditText edtTenChuDe;

    private Button btnThem;


    private ChuDeDAO dao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_chude);



        // ánh xạ

        rvChuDe = findViewById(R.id.rvChuDe);

        edtTenChuDe = findViewById(R.id.edtTenChuDe);

        btnThem = findViewById(R.id.btnThem);



        dao = new ChuDeDAO(this);



        loadData();




        // thêm chủ đề

        btnThem.setOnClickListener(v -> {


            String ten = edtTenChuDe
                    .getText()
                    .toString()
                    .trim();

            if(ten.isEmpty()){
                Toast.makeText(
                        this,
                        "Nhập tên chủ đề",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }
            ChuDe cd = new ChuDe();
            cd.setTenChuDe(ten);
            long result = dao.insert(cd);
            if(result > 0){
                Toast.makeText(
                        this,
                        "Thêm thành công",
                        Toast.LENGTH_SHORT
                ).show();
                edtTenChuDe.setText("");
                loadData();
            }else{
                Toast.makeText(
                        this,
                        "Thêm thất bại",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
    private void loadData(){
        rvChuDe.setLayoutManager(
                new LinearLayoutManager(this)
        );
        rvChuDe.setAdapter(
                new ChuDeAdapter(
                        this,
                        dao.getAll(),
                        this
                )
        );
    }
    // ========================
    // Sửa bằng Dialog
    // ========================
    @Override
    public void onEdit(ChuDe chuDe) {
        SuaChuDeDialog dialog =
                new SuaChuDeDialog(
                        this,
                        chuDe,
                        () -> loadData()
                );
        dialog.show();
    }
    // ========================
    // Xóa
    // =======================
    @Override
    public void onDelete(ChuDe chuDe) {


        new android.app.AlertDialog.Builder(this)

                .setTitle("Xác nhận xóa")

                .setMessage(
                        "Bạn có chắc muốn xóa chủ đề: "
                                + chuDe.getTenChuDe()
                                + " ?"
                )


                .setPositiveButton(
                        "Xóa",
                        (dialog, which) -> {


                            int result = dao.delete(
                                    chuDe.getMaChuDe()
                            );


                            if(result > 0){


                                Toast.makeText(
                                        this,
                                        "Xóa thành công",
                                        Toast.LENGTH_SHORT
                                ).show();


                                loadData();


                            }else{


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