package com.example.baitaplon_andori.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.baitaplon_andori.database.DatabaseHelper;
import com.example.baitaplon_andori.model.Vocabulary;
import java.util.ArrayList;
import java.util.List;

public class VocabularyDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public VocabularyDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // Lấy danh sách từ vựng theo TopicID (Học từ vựng theo chủ đề)
    @SuppressLint("Range")
    public List<Vocabulary> getVocabularyByTopic(int topicId) {
        List<Vocabulary> list = new ArrayList<>();
        Cursor cursor = db.query("Vocabulary", null, "TopicID = ?",
                new String[]{String.valueOf(topicId)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Vocabulary vocab = new Vocabulary();
                vocab.setVocabID(cursor.getInt(cursor.getColumnIndex("VocabID")));
                vocab.setWord(cursor.getString(cursor.getColumnIndex("Word")));
                vocab.setMeaning(cursor.getString(cursor.getColumnIndex("Meaning")));
                vocab.setPronunciation(cursor.getString(cursor.getColumnIndex("Pronunciation")));
                vocab.setExample(cursor.getString(cursor.getColumnIndex("Example")));
                vocab.setTopicID(cursor.getInt(cursor.getColumnIndex("TopicID")));
                list.add(vocab);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return list;
    }
    @SuppressLint("Range")
    public ArrayList<Vocabulary> getAll() {

        ArrayList<Vocabulary> list = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM Vocabulary",
                null);

        if (cursor.moveToFirst()) {

            do {

                Vocabulary vocab = new Vocabulary();

                vocab.setVocabID(cursor.getInt(cursor.getColumnIndex("VocabID")));
                vocab.setWord(cursor.getString(cursor.getColumnIndex("Word")));
                vocab.setMeaning(cursor.getString(cursor.getColumnIndex("Meaning")));
                vocab.setPronunciation(cursor.getString(cursor.getColumnIndex("Pronunciation")));
                vocab.setExample(cursor.getString(cursor.getColumnIndex("Example")));
                vocab.setTopicID(cursor.getInt(cursor.getColumnIndex("TopicID")));

                list.add(vocab);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return list;
    }
}