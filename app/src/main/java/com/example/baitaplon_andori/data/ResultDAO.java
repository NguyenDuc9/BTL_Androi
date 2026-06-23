package com.example.baitaplon_andori.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.baitaplon_andori.database.DatabaseHelper;
import com.example.baitaplon_andori.model.Result;

import java.util.ArrayList;

public class ResultDAO {

    DatabaseHelper dbHelper;

    public ResultDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long insert(Result result) {

        SQLiteDatabase db =
                dbHelper.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put("UserID", result.getUserID());
        values.put("TestID", result.getTestID());
        values.put("Score", result.getScore());

        return db.insert(
                "Results",
                null,
                values);
    }

    public ArrayList<Result> getAll() {

        ArrayList<Result> list =
                new ArrayList<>();

        SQLiteDatabase db =
                dbHelper.getReadableDatabase();

        Cursor cursor =
                db.rawQuery(
                        "SELECT * FROM Results",
                        null);

        while(cursor.moveToNext()) {

            Result r =
                    new Result();

            r.setResultID(cursor.getInt(0));
            r.setUserID(cursor.getInt(1));
            r.setTestID(cursor.getInt(2));
            r.setScore(cursor.getInt(3));
            r.setTestDate(cursor.getString(4));

            list.add(r);
        }

        cursor.close();

        return list;
    }
}