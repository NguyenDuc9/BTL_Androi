package com.example.baitaplon_andori.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.baitaplon_andori.database.DatabaseHelper;
import com.example.baitaplon_andori.model.Question;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public QuestionDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // Lấy danh sách câu hỏi dựa trên TestID (Thông qua bảng trung gian TestDetails)
    @SuppressLint("Range")
    public List<Question> getQuestionsByTest(int testId) {
        List<Question> list = new ArrayList<>();

        // Câu lệnh SQL JOIN giữa bảng Questions và TestDetails
        String query = "SELECT q.* FROM Questions q " +
                "INNER JOIN TestDetails td ON q.QuestionID = td.QuestionID " +
                "WHERE td.TestID = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(testId)});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Question q = new Question();
                q.setQuestionID(cursor.getInt(cursor.getColumnIndex("QuestionID")));
                q.setTopicID(cursor.getInt(cursor.getColumnIndex("TopicID")));
                q.setQuestionText(cursor.getString(cursor.getColumnIndex("QuestionText")));
                q.setOptionA(cursor.getString(cursor.getColumnIndex("OptionA")));
                q.setOptionB(cursor.getString(cursor.getColumnIndex("OptionB")));
                q.setOptionC(cursor.getString(cursor.getColumnIndex("OptionC")));
                q.setOptionD(cursor.getString(cursor.getColumnIndex("OptionD")));
                q.setCorrectAnswer(cursor.getString(cursor.getColumnIndex("CorrectAnswer")));
                q.setExplanation(cursor.getString(cursor.getColumnIndex("Explanation")));
                list.add(q);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return list;
    }
    @SuppressLint("Range")
    public ArrayList<Question> getAll() {

        ArrayList<Question> list = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM Questions",
                null);

        if (cursor.moveToFirst()) {
            do {

                Question q = new Question();

                q.setQuestionID(cursor.getInt(cursor.getColumnIndex("QuestionID")));
                q.setTopicID(cursor.getInt(cursor.getColumnIndex("TopicID")));
                q.setQuestionText(cursor.getString(cursor.getColumnIndex("QuestionText")));
                q.setOptionA(cursor.getString(cursor.getColumnIndex("OptionA")));
                q.setOptionB(cursor.getString(cursor.getColumnIndex("OptionB")));
                q.setOptionC(cursor.getString(cursor.getColumnIndex("OptionC")));
                q.setOptionD(cursor.getString(cursor.getColumnIndex("OptionD")));
                q.setCorrectAnswer(cursor.getString(cursor.getColumnIndex("CorrectAnswer")));
                q.setExplanation(cursor.getString(cursor.getColumnIndex("Explanation")));

                list.add(q);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return list;
    }
}