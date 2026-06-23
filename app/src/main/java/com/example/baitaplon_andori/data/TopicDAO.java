package com.example.baitaplon_andori.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.baitaplon_andori.database.DatabaseHelper;
import com.example.baitaplon_andori.model.Topic;
import java.util.ArrayList;
import java.util.List;

public class TopicDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public TopicDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // Lấy tất cả danh sách chủ đề
    @SuppressLint("Range")
    public List<Topic> getAllTopics() {
        List<Topic> list = new ArrayList<>();
        Cursor cursor = db.query("Topics", null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Topic topic = new Topic();
                topic.setTopicID(cursor.getInt(cursor.getColumnIndex("TopicID")));
                topic.setTopicName(cursor.getString(cursor.getColumnIndex("TopicName")));
                list.add(topic);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return list;
    }

    // Thêm chủ đề mới
    public long insertTopic(Topic topic) {
        ContentValues values = new ContentValues();
        values.put("TopicName", topic.getTopicName());
        return db.insert("Topics", null, values);
    }
}