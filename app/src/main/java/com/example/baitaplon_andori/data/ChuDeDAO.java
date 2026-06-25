package com.example.baitaplon_andori.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.baitaplon_andori.database.DatabaseHelper;
import com.example.baitaplon_andori.model.ChuDe;

import java.util.ArrayList;
import java.util.List;

public class ChuDeDAO {

    private DatabaseHelper dbHelper;

    public ChuDeDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Lấy toàn bộ danh sách chủ đề
    public List<ChuDe> getAll() {
        List<ChuDe> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM ChuDe", null);

        if (c != null) {
            while (c.moveToNext()) {
                ChuDe cd = new ChuDe();
                cd.setMaChuDe(c.getInt(c.getColumnIndexOrThrow("MaChuDe")));
                cd.setTenChuDe(c.getString(c.getColumnIndexOrThrow("TenChuDe")));
                list.add(cd);
            }
            c.close(); // Đóng Cursor
        }

        db.close(); // SỬA LỖI: Mở comment để đóng database sau khi đọc xong
        return list;
    }

    // Thêm chủ đề mới
    public long insert(ChuDe cd) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenChuDe", cd.getTenChuDe());

        long result = db.insert("ChuDe", null, values);
        db.close(); // SỬA LỖI: Đóng kết nối sau khi ghi xong
        return result;
    }

    // Cập nhật chủ đề
    public int update(ChuDe cd) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenChuDe", cd.getTenChuDe());

        int result = db.update(
                "ChuDe",
                values,
                "MaChuDe=?",
                new String[]{String.valueOf(cd.getMaChuDe())}
        );
        db.close(); // SỬA LỖI: Đóng kết nối sau khi cập nhật xong
        return result;
    }

    // Xóa chủ đề
    public int delete(int maChuDe) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int result = db.delete(
                "ChuDe",
                "MaChuDe=?",
                new String[]{String.valueOf(maChuDe)}
        );
        db.close(); // SỬA LỖI: Đóng kết nối sau khi xóa xong
        return result;
    }
}