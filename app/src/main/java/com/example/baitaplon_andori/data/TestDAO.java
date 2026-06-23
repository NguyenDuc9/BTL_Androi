package com.example.baitaplon_andori.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.baitaplon_andori.database.DatabaseHelper;
import com.example.baitaplon_andori.model.Test;

import java.util.ArrayList;

public class TestDAO {

    DatabaseHelper dbHelper;

    public TestDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long insert(Test test) {

        SQLiteDatabase db =
                dbHelper.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put("TestName", test.getTestName());
        values.put("TotalQuestions", test.getTotalQuestions());

        return db.insert(
                "Tests",
                null,
                values);
    }

    public ArrayList<Test> getAll() {

        ArrayList<Test> list =
                new ArrayList<>();

        SQLiteDatabase db =
                dbHelper.getReadableDatabase();

        Cursor cursor =
                db.rawQuery(
                        "SELECT * FROM Tests",
                        null);

        while(cursor.moveToNext()) {

            Test t =
                    new Test();

            t.setTestID(cursor.getInt(0));
            t.setTestName(cursor.getString(1));
            t.setTotalQuestions(cursor.getInt(2));

            list.add(t);
        }

        cursor.close();

        return list;
    }
}