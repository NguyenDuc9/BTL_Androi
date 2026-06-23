package com.example.baitaplon_andori.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.baitaplon_andori.database.DatabaseHelper;
import com.example.baitaplon_andori.model.TestDetail;

import java.util.ArrayList;

public class TestDetailDAO {

    DatabaseHelper dbHelper;

    public TestDetailDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long insert(TestDetail detail) {

        SQLiteDatabase db =
                dbHelper.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put("TestID", detail.getTestID());
        values.put("QuestionID", detail.getQuestionID());

        return db.insert(
                "TestDetails",
                null,
                values);
    }

    public ArrayList<TestDetail> getAll() {

        ArrayList<TestDetail> list =
                new ArrayList<>();

        SQLiteDatabase db =
                dbHelper.getReadableDatabase();

        Cursor cursor =
                db.rawQuery(
                        "SELECT * FROM TestDetails",
                        null);

        while(cursor.moveToNext()) {

            TestDetail t =
                    new TestDetail();

            t.setTestID(cursor.getInt(0));
            t.setQuestionID(cursor.getInt(1));

            list.add(t);
        }

        cursor.close();

        return list;
    }
}