package com.example.baitaplon_andori.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.baitaplon_andori.database.DatabaseHelper;
import com.example.baitaplon_andori.model.Flashcard;

import java.util.ArrayList;

public class FlashcardDAO {

    DatabaseHelper dbHelper;

    public FlashcardDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long insert(Flashcard flashcard) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("UserID", flashcard.getUserID());
        values.put("VocabID", flashcard.getVocabID());
        values.put("IsRemembered", flashcard.getIsRemembered());

        return db.insert("Flashcards", null, values);
    }

    public ArrayList<Flashcard> getAll() {

        ArrayList<Flashcard> list = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor =
                db.rawQuery("SELECT * FROM Flashcards", null);

        while(cursor.moveToNext()) {

            Flashcard f = new Flashcard();

            f.setFlashcardID(cursor.getInt(0));
            f.setUserID(cursor.getInt(1));
            f.setVocabID(cursor.getInt(2));
            f.setIsRemembered(cursor.getInt(3));

            list.add(f);
        }

        cursor.close();

        return list;
    }
}