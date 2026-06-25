package com.example.baitaplon_andori.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.baitaplon_andori.database.DatabaseHelper;
import com.example.baitaplon_andori.model.TuVung;

import java.util.ArrayList;
import java.util.List;

public class TuVungDAO {

    private DatabaseHelper dbHelper;

    public TuVungDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Lấy toàn bộ danh sách từ vựng
    public List<TuVung> getAll() {
        List<TuVung> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM TuVung", null);

        if (c != null) {
            while (c.moveToNext()) {
                TuVung tv = new TuVung();

                // SỬA CHÍ MẠNG: Dùng tên cột rõ ràng để không bao giờ sợ bị đảo lộn thứ tự dữ liệu
                tv.setMaTu(c.getInt(c.getColumnIndexOrThrow("MaTu")));
                tv.setTuTiengAnh(c.getString(c.getColumnIndexOrThrow("TuTiengAnh")));
                tv.setNghiaTiengViet(c.getString(c.getColumnIndexOrThrow("NghiaTiengViet")));
                tv.setHinhAnh(c.getString(c.getColumnIndexOrThrow("HinhAnh")));
                tv.setMaChuDe(c.getInt(c.getColumnIndexOrThrow("MaChuDe")));

                list.add(tv);
            }
            c.close();
        }
        return list;
    }

    // Thêm từ vựng mới
    public long insert(TuVung tv) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("TuTiengAnh", tv.getTuTiengAnh());
        values.put("NghiaTiengViet", tv.getNghiaTiengViet());
        values.put("HinhAnh", tv.getHinhAnh());
        values.put("MaChuDe", tv.getMaChuDe());

        long result = db.insert("TuVung", null, values);
        return result;
    }

    // Cập nhật từ vựng
    public int update(TuVung tv) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("TuTiengAnh", tv.getTuTiengAnh());
        values.put("NghiaTiengViet", tv.getNghiaTiengViet());
        values.put("HinhAnh", tv.getHinhAnh());
        values.put("MaChuDe", tv.getMaChuDe());

        int result = db.update(
                "TuVung",
                values,
                "MaTu=?",
                new String[]{String.valueOf(tv.getMaTu())}
        );
        return result;
    }

    // Xóa từ vựng
    public int delete(int maTu) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int result = db.delete(
                "TuVung",
                "MaTu=?",
                new String[]{String.valueOf(maTu)}
        );
        return result;
    }

    // Lọc từ vựng theo mã chủ đề
    public List<TuVung> getByMaChuDe(int maChuDe) {
        List<TuVung> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM TuVung WHERE MaChuDe = ?", new String[]{String.valueOf(maChuDe)});

        if (c != null) {
            while (c.moveToNext()) {
                TuVung tv = new TuVung();

                // SỬA CHÍ MẠNG: Đồng bộ tìm theo tên cột an toàn
                tv.setMaTu(c.getInt(c.getColumnIndexOrThrow("MaTu")));
                tv.setTuTiengAnh(c.getString(c.getColumnIndexOrThrow("TuTiengAnh")));
                tv.setNghiaTiengViet(c.getString(c.getColumnIndexOrThrow("NghiaTiengViet")));
                tv.setHinhAnh(c.getString(c.getColumnIndexOrThrow("HinhAnh")));
                tv.setMaChuDe(c.getInt(c.getColumnIndexOrThrow("MaChuDe")));

                list.add(tv);
            }
            c.close();
        }
        return list;
    }
}