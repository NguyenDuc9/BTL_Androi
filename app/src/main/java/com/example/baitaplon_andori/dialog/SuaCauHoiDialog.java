package com.example.baitaplon_andori.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baitaplon_andori.MainActivity;
import com.example.baitaplon_andori.R;
import com.example.baitaplon_andori.data.CauHoiDAO;
import com.example.baitaplon_andori.model.CauHoi;

public class SuaCauHoiDialog {

    private Context context;
    private CauHoi cauHoi;
    private Runnable reload;

    public SuaCauHoiDialog(
            Context context,
            CauHoi cauHoi,
            Runnable reload
    ) {
        this.context = context;
        this.cauHoi = cauHoi;
        this.reload = reload;
    }

    public void show() {


        Dialog dialog = new Dialog(context);

        dialog.requestWindowFeature(
                Window.FEATURE_NO_TITLE
        );

        dialog.setContentView(
                R.layout.dialog_sua_cauhoi
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

        Button btnLuu =
                dialog.findViewById(R.id.btnLuu);

        edtNoiDung.setText(
                cauHoi.getNoiDung()
        );

        edtA.setText(
                cauHoi.getDapAnA()
        );

        edtB.setText(
                cauHoi.getDapAnB()
        );

        edtC.setText(
                cauHoi.getDapAnC()
        );

        edtD.setText(
                cauHoi.getDapAnD()
        );

        edtDung.setText(
                cauHoi.getDapAnDung()
        );

        edtGiaiThich.setText(
                cauHoi.getGiaiThich()
        );



        btnLuu.setOnClickListener(v -> {



            cauHoi.setNoiDung(
                    edtNoiDung.getText().toString()
            );

            cauHoi.setDapAnA(
                    edtA.getText().toString()
            );

            cauHoi.setDapAnB(
                    edtB.getText().toString()
            );

            cauHoi.setDapAnC(
                    edtC.getText().toString()
            );

            cauHoi.setDapAnD(
                    edtD.getText().toString()
            );

            cauHoi.setDapAnDung(
                    edtDung.getText().toString()
            );

            cauHoi.setGiaiThich(
                    edtGiaiThich.getText().toString()
            );

            CauHoiDAO dao =
                    new CauHoiDAO(context);

            dao.update(cauHoi);

            reload.run();

            dialog.dismiss();
        });

        dialog.show();
    }
}