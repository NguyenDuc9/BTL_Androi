package com.example.baitaplon_andori.data;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.baitaplon_andori.database.DatabaseHelper;
import com.example.baitaplon_andori.model.CauHoi;

import java.util.ArrayList;


public class CauHoiDAO {


    private DatabaseHelper dbHelper;


    public CauHoiDAO(Context context) {

        dbHelper = new DatabaseHelper(context);

    }



    @SuppressLint("Range")
    public ArrayList<CauHoi> getAll(){


        ArrayList<CauHoi> list = new ArrayList<>();


        SQLiteDatabase db = dbHelper.getReadableDatabase();



        Cursor cursor = db.rawQuery(
                "SELECT * FROM CauHoi",
                null
        );



        if(cursor.moveToFirst()){


            do{


                CauHoi q = new CauHoi();



                q.setMaCauHoi(
                        cursor.getInt(
                                cursor.getColumnIndex("MaCauHoi")
                        )
                );


                q.setNoiDung(
                        cursor.getString(
                                cursor.getColumnIndex("NoiDung")
                        )
                );



                q.setDapAnA(
                        cursor.getString(
                                cursor.getColumnIndex("DapAnA")
                        )
                );


                q.setDapAnB(
                        cursor.getString(
                                cursor.getColumnIndex("DapAnB")
                        )
                );


                q.setDapAnC(
                        cursor.getString(
                                cursor.getColumnIndex("DapAnC")
                        )
                );


                q.setDapAnD(
                        cursor.getString(
                                cursor.getColumnIndex("DapAnD")
                        )
                );


                q.setDapAnDung(
                        cursor.getString(
                                cursor.getColumnIndex("DapAnDung")
                        )
                );


                q.setGiaiThich(
                        cursor.getString(
                                cursor.getColumnIndex("GiaiThich")
                        )
                );





                list.add(q);



            }while(cursor.moveToNext());


        }


        cursor.close();


        return list;

    }
    public long insert(CauHoi q){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("NoiDung", q.getNoiDung());
        values.put("DapAnA", q.getDapAnA());
        values.put("DapAnB", q.getDapAnB());
        values.put("DapAnC", q.getDapAnC());
        values.put("DapAnD", q.getDapAnD());
        values.put("DapAnDung", q.getDapAnDung());
        values.put("GiaiThich", q.getGiaiThich());

        return db.insert("CauHoi", null, values);
    }
    public int update(CauHoi q){

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("NoiDung", q.getNoiDung());
        values.put("DapAnA", q.getDapAnA());
        values.put("DapAnB", q.getDapAnB());
        values.put("DapAnC", q.getDapAnC());
        values.put("DapAnD", q.getDapAnD());
        values.put("DapAnDung", q.getDapAnDung());
        values.put("GiaiThich", q.getGiaiThich());

        return db.update(
                "CauHoi",
                values,
                "MaCauHoi=?",
                new String[]{
                        String.valueOf(q.getMaCauHoi())
                }
        );
    }
    public int delete(int maCauHoi){

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        return db.delete(
                "CauHoi",
                "MaCauHoi=?",
                new String[]{
                        String.valueOf(maCauHoi)
                }
        );
    }

}