package com.example.baitaplon_andori.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.baitaplon_andori.database.DatabaseHelper;

public class TheGhiNhoDAO {

    private DatabaseHelper dbHelper;


    public TheGhiNhoDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }


    public int capNhatTrangThai(int maThe, int daNho){

        SQLiteDatabase db = dbHelper.getWritableDatabase();


        return db.update(
                "TheGhiNho",
                null,
                "MaThe = ?",
                new String[]{
                        String.valueOf(maThe)
                }
        );

    }
}