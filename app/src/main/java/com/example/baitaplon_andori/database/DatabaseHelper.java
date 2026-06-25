package com.example.baitaplon_andori.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Tiếp tục đổi tên Database mới để xóa hoàn toàn các bảng cũ bị lệch cấu trúc
    private static final String DATABASE_NAME = "ToeicDB_No_ForeignKeys1.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // 1. Bảng Chủ Đề
        db.execSQL(
                "CREATE TABLE ChuDe (" +
                        "MaChuDe INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "TenChuDe TEXT NOT NULL)"
        );

        // 2. Bảng Từ Vựng
        db.execSQL(
                "CREATE TABLE TuVung (" +
                        "MaTu INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "TuTiengAnh TEXT NOT NULL," +
                        "NghiaTiengViet TEXT NOT NULL," +
                        "HinhAnh TEXT," +
                        "MaChuDe INTEGER," +
                        "FOREIGN KEY(MaChuDe) REFERENCES ChuDe(MaChuDe) ON DELETE CASCADE)"
        );

        // 3. Bảng Thẻ Ghi Nhớ
        db.execSQL(
                "CREATE TABLE TheGhiNho (" +
                        "MaThe INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "MaTu INTEGER," +
                        "DaNho INTEGER DEFAULT 0," +
                        "FOREIGN KEY(MaTu) REFERENCES TuVung(MaTu) ON DELETE CASCADE)"
        );

        // 4. Bảng Bài Test
        db.execSQL(
                "CREATE TABLE BaiTest (" +
                        "MaBaiTest INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "TenBaiTest TEXT NOT NULL," +
                        "TongSoCau INTEGER)"
        );

        // 5. Bảng Câu Hỏi (ĐÃ SỬA: Bỏ hoàn toàn MaChuDe, MaBaiTest và các khóa ngoại liên quan)
        db.execSQL(
                "CREATE TABLE CauHoi (" +
                        "MaCauHoi INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "NoiDung TEXT NOT NULL," +
                        "DapAnA TEXT," +
                        "DapAnB TEXT," +
                        "DapAnC TEXT," +
                        "DapAnD TEXT," +
                        "DapAnDung TEXT," +
                        "GiaiThich TEXT)"
        );

        // Chèn dữ liệu mẫu
        insertSampleData(db);
    }

    private void insertSampleData(SQLiteDatabase db) {

        // 1. Dữ liệu mẫu Chủ Đề
        String sqlChuDe = "INSERT INTO ChuDe(TenChuDe) VALUES (?)";
        db.execSQL(sqlChuDe, new Object[]{"Kinh doanh"});
        db.execSQL(sqlChuDe, new Object[]{"Du lịch"});
        db.execSQL(sqlChuDe, new Object[]{"Văn phòng"});


        // 3. Dữ liệu mẫu Thẻ Ghi Nhớ

        // 4. Dữ liệu mẫu Bài Test
        String sqlBaiTest = "INSERT INTO BaiTest(TenBaiTest, TongSoCau) VALUES(?, ?)";
        db.execSQL(sqlBaiTest, new Object[]{"Mini Test 1", 2});
        db.execSQL(sqlBaiTest, new Object[]{"Mini Test 2", 2});
        db.execSQL(sqlBaiTest, new Object[]{"Grammar Test", 3});

        // 5. Dữ liệu mẫu Câu Hỏi (ĐÃ SỬA: Chỉ chèn đúng 8 trường dữ liệu, bỏ hoàn toàn các mã liên kết ở cuối)
        String sqlCauHoi = "INSERT INTO CauHoi(NoiDung, DapAnA, DapAnB, DapAnC, DapAnD, DapAnDung, GiaiThich) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";

        db.execSQL(sqlCauHoi, new Object[]{
                "The manager _____ the report yesterday.",
                "submit", "submitted", "submitting", "submits",
                "B",
                "Yesterday là dấu hiệu thì quá khứ đơn."
        });

        db.execSQL(sqlCauHoi, new Object[]{
                "We received our travel _____ yesterday.",
                "employee", "salary", "itinerary", "document",
                "C",
                "Itinerary nghĩa là lịch trình."
        });

        db.execSQL(sqlCauHoi, new Object[]{
                "Please review this _____ carefully.",
                "campaign", "document", "travel", "employee",
                "B",
                "Document nghĩa là tài liệu."
        });
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CauHoi");
        db.execSQL("DROP TABLE IF EXISTS BaiTest");
        db.execSQL("DROP TABLE IF EXISTS TheGhiNho");
        db.execSQL("DROP TABLE IF EXISTS TuVung");
        db.execSQL("DROP TABLE IF EXISTS ChuDe");
        onCreate(db);
    }
}