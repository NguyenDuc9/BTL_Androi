package com.example.baitaplon_andori.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.baitaplon_andori.database.DatabaseHelper;
import com.example.baitaplon_andori.model.BaiTest;

import java.util.ArrayList;
import java.util.List;

public class BaiTestDAO {

    private DatabaseHelper dbHelper;

    public BaiTestDAO(Context context) {
        dbHelper = new DatabaseHelper(context);    }

    public List<BaiTest> getAll() {

        List<BaiTest> list = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM BaiTest", null);

        while (c.moveToNext()) {

            list.add(
                    new BaiTest(
                            c.getInt(0),
                            c.getString(1),
                            c.getInt(2)
                    )
            );
        }

        return list;
    }
}