package com.example.baitaplon_andori.dialog;


import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;


import com.example.baitaplon_andori.R;
import com.example.baitaplon_andori.data.ChuDeDAO;
import com.example.baitaplon_andori.model.ChuDe;



public class SuaChuDeDialog {


    private Context context;


    private ChuDe chuDe;


    private Runnable reload;



    public SuaChuDeDialog(
            Context context,
            ChuDe chuDe,
            Runnable reload
    ){

        this.context = context;

        this.chuDe = chuDe;

        this.reload = reload;

    }




    public void show(){


        Dialog dialog = new Dialog(context);


        dialog.requestWindowFeature(
                Window.FEATURE_NO_TITLE
        );


        dialog.setContentView(
                R.layout.dialog_sua_chude
        );



        EditText edt =
                dialog.findViewById(
                        R.id.edtSuaTenChuDe
                );


        Button btn =
                dialog.findViewById(
                        R.id.btnLuu
                );



        edt.setText(
                chuDe.getTenChuDe()
        );




        btn.setOnClickListener(v -> {


            String tenMoi =
                    edt.getText()
                            .toString()
                            .trim();



            if(!tenMoi.isEmpty()){


                chuDe.setTenChuDe(
                        tenMoi
                );



                ChuDeDAO dao =
                        new ChuDeDAO(context);



                dao.update(chuDe);



                reload.run();



                dialog.dismiss();

            }



        });



        dialog.show();


    }


}