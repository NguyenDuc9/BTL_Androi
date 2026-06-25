package com.example.baitaplon_andori.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baitaplon_andori.R;
import com.example.baitaplon_andori.data.CauHoiDAO;
import com.example.baitaplon_andori.model.CauHoi;

public class ThemCauHoiDialog {

    private Context context;
    private Runnable reload;

    public ThemCauHoiDialog(
            Context context,
            Runnable reload
    ) {
        this.context = context;
        this.reload = reload;
    }

    public void show() {

        Dialog dialog = new Dialog(context);

        dialog.requestWindowFeature(
                Window.FEATURE_NO_TITLE
        );

        dialog.setContentView(
                R.layout.dialog_them_cauhoi
        );

        EditText edtNoiDung =
                dialog.findViewById(R.id.edtNoiDung);

        EditText edtA =
                dialog.findViewById(R.id.edtA);

        EditText edtB =
                dialog.findViewById(R.id.edtB);

        EditText edtC =
                dialog.findViewById(R.id.edtC);

        EditText edtD =
                dialog.findViewById(R.id.edtD);

        EditText edtDung =
                dialog.findViewById(R.id.edtDung);

        EditText edtGiaiThich =
                dialog.findViewById(R.id.edtGiaiThich);


        Button btnThem =
                dialog.findViewById(R.id.btnThem);

        btnThem.setOnClickListener(v -> {

            String noiDung =
                    edtNoiDung.getText().toString().trim();

            String a =
                    edtA.getText().toString().trim();

            String b =
                    edtB.getText().toString().trim();

            String c =
                    edtC.getText().toString().trim();

            String d =
                    edtD.getText().toString().trim();

            String dung =
                    edtDung.getText().toString().trim();

            String giaiThich =
                    edtGiaiThich.getText().toString().trim();

            if (noiDung.isEmpty()
                    || a.isEmpty()
                    || b.isEmpty()
                    || c.isEmpty()
                    || d.isEmpty()
                    || dung.isEmpty()) {

                Toast.makeText(
                        context,
                        "Vui lòng nhập đầy đủ dữ liệu",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            CauHoi q = new CauHoi();

            q.setNoiDung(noiDung);
            q.setDapAnA(a);
            q.setDapAnB(b);
            q.setDapAnC(c);
            q.setDapAnD(d);
            q.setDapAnDung(dung);
            q.setGiaiThich(giaiThich);


            CauHoiDAO dao =
                    new CauHoiDAO(context);

            long result =
                    dao.insert(q);

            if (result > 0) {

                Toast.makeText(
                        context,
                        "Thêm câu hỏi thành công",
                        Toast.LENGTH_SHORT
                ).show();

                reload.run();

                dialog.dismiss();

            } else {

                Toast.makeText(
                        context,
                        "Thêm thất bại",
                        Toast.LENGTH_SHORT
                ).show();
            }

        });

        dialog.show();
    }
}