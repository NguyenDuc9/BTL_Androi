package com.example.baitaplon_andori.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.baitaplon_andori.database.DatabaseHelper;
import com.example.baitaplon_andori.model.AnswerHistory;

import java.util.ArrayList;

public class AnswerHistoryDAO {

    DatabaseHelper dbHelper;

    public AnswerHistoryDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long insert(AnswerHistory history) {

        SQLiteDatabase db =
                dbHelper.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put("UserID", history.getUserID());
        values.put("QuestionID", history.getQuestionID());
        values.put("SelectedAnswer", history.getSelectedAnswer());
        values.put("IsCorrect", history.getIsCorrect());

        return db.insert(
                "AnswerHistory",
                null,
                values);
    }

    public ArrayList<AnswerHistory> getAll() {

        ArrayList<AnswerHistory> list =
                new ArrayList<>();

        SQLiteDatabase db =
                dbHelper.getReadableDatabase();

        Cursor cursor =
                db.rawQuery(
                        "SELECT * FROM AnswerHistory",
                        null);

        while(cursor.moveToNext()) {

            AnswerHistory h =
                    new AnswerHistory();

            h.setHistoryID(cursor.getInt(0));
            h.setUserID(cursor.getInt(1));
            h.setQuestionID(cursor.getInt(2));
            h.setSelectedAnswer(cursor.getString(3));
            h.setIsCorrect(cursor.getInt(4));
            h.setAnswerDate(cursor.getString(5));

            list.add(h);
        }

        cursor.close();

        return list;
    }
}
