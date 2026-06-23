package com.example.baitaplon_andori.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ToeicDB.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // 1. Users
        db.execSQL(
                "CREATE TABLE Users (" +
                        "UserID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "Username TEXT NOT NULL UNIQUE," +
                        "Password TEXT NOT NULL," +
                        "FullName TEXT" +
                        ")"
        );

        // 2. Topics
        db.execSQL(
                "CREATE TABLE Topics (" +
                        "TopicID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "TopicName TEXT NOT NULL" +
                        ")"
        );

        // 3. Vocabulary
        db.execSQL(
                "CREATE TABLE Vocabulary (" +
                        "VocabID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "Word TEXT NOT NULL," +
                        "Meaning TEXT NOT NULL," +
                        "Pronunciation TEXT," +
                        "Example TEXT," +
                        "TopicID INTEGER," +
                        "FOREIGN KEY(TopicID) REFERENCES Topics(TopicID)" +
                        ")"
        );

        // 4. Flashcards
        db.execSQL(
                "CREATE TABLE Flashcards (" +
                        "FlashcardID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "UserID INTEGER," +
                        "VocabID INTEGER," +
                        "IsRemembered INTEGER DEFAULT 0," +
                        "FOREIGN KEY(UserID) REFERENCES Users(UserID)," +
                        "FOREIGN KEY(VocabID) REFERENCES Vocabulary(VocabID)" +
                        ")"
        );

        // 5. Questions
        db.execSQL(
                "CREATE TABLE Questions (" +
                        "QuestionID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "TopicID INTEGER," +
                        "QuestionText TEXT NOT NULL," +
                        "OptionA TEXT," +
                        "OptionB TEXT," +
                        "OptionC TEXT," +
                        "OptionD TEXT," +
                        "CorrectAnswer TEXT," +
                        "Explanation TEXT," +
                        "FOREIGN KEY(TopicID) REFERENCES Topics(TopicID)" +
                        ")"
        );

        // 6. Tests
        db.execSQL(
                "CREATE TABLE Tests (" +
                        "TestID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "TestName TEXT NOT NULL," +
                        "TotalQuestions INTEGER" +
                        ")"
        );

        // 7. TestDetails
        db.execSQL(
                "CREATE TABLE TestDetails (" +
                        "TestID INTEGER," +
                        "QuestionID INTEGER," +
                        "PRIMARY KEY(TestID, QuestionID)," +
                        "FOREIGN KEY(TestID) REFERENCES Tests(TestID)," +
                        "FOREIGN KEY(QuestionID) REFERENCES Questions(QuestionID)" +
                        ")"
        );

        // 8. Results
        db.execSQL(
                "CREATE TABLE Results (" +
                        "ResultID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "UserID INTEGER," +
                        "TestID INTEGER," +
                        "Score INTEGER," +
                        "TestDate DATETIME DEFAULT CURRENT_TIMESTAMP," +
                        "FOREIGN KEY(UserID) REFERENCES Users(UserID)," +
                        "FOREIGN KEY(TestID) REFERENCES Tests(TestID)" +
                        ")"
        );

        // 9. AnswerHistory
        db.execSQL(
                "CREATE TABLE AnswerHistory (" +
                        "HistoryID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "UserID INTEGER," +
                        "QuestionID INTEGER," +
                        "SelectedAnswer TEXT," +
                        "IsCorrect INTEGER," +
                        "AnswerDate DATETIME DEFAULT CURRENT_TIMESTAMP," +
                        "FOREIGN KEY(UserID) REFERENCES Users(UserID)," +
                        "FOREIGN KEY(QuestionID) REFERENCES Questions(QuestionID)" +
                        ")"
        );

        insertSampleData(db);
    }

    private void insertSampleData(SQLiteDatabase db) {

        // Chủ đề
        db.execSQL("INSERT INTO Topics(TopicName) VALUES('Business')");
        db.execSQL("INSERT INTO Topics(TopicName) VALUES('Office')");
        db.execSQL("INSERT INTO Topics(TopicName) VALUES('Travel')");
        db.execSQL("INSERT INTO Topics(TopicName) VALUES('Marketing')");

        // Tài khoản mẫu
        db.execSQL(
                "INSERT INTO Users(Username,Password,FullName) " +
                        "VALUES('admin','123456','Administrator')"
        );

        // Từ vựng mẫu
        db.execSQL(
                "INSERT INTO Vocabulary(Word,Meaning,Pronunciation,Example,TopicID) " +
                        "VALUES('employee','nhân viên','/ɪmˈplɔɪiː/','He is an employee.',1)"
        );

        db.execSQL(
                "INSERT INTO Vocabulary(Word,Meaning,Pronunciation,Example,TopicID) " +
                        "VALUES('salary','tiền lương','/ˈsæləri/','The salary is high.',1)"
        );

        // Câu hỏi mẫu
        db.execSQL(
                "INSERT INTO Questions(" +
                        "TopicID,QuestionText,OptionA,OptionB,OptionC,OptionD,CorrectAnswer,Explanation)" +
                        "VALUES(" +
                        "1," +
                        "'The manager _____ the report yesterday.'," +
                        "'submit'," +
                        "'submitted'," +
                        "'submitting'," +
                        "'submits'," +
                        "'B'," +
                        "'Yesterday => Past Simple nên dùng submitted.'" +
                        ")"
        );

        // Test mẫu
        db.execSQL(
                "INSERT INTO Tests(TestName,TotalQuestions) " +
                        "VALUES('Mini Test 1',10)"
        );

        // Ghép câu hỏi vào bài test
        db.execSQL(
                "INSERT INTO TestDetails(TestID,QuestionID) " +
                        "VALUES(1,1)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS AnswerHistory");
        db.execSQL("DROP TABLE IF EXISTS Results");
        db.execSQL("DROP TABLE IF EXISTS TestDetails");
        db.execSQL("DROP TABLE IF EXISTS Tests");
        db.execSQL("DROP TABLE IF EXISTS Questions");
        db.execSQL("DROP TABLE IF EXISTS Flashcards");
        db.execSQL("DROP TABLE IF EXISTS Vocabulary");
        db.execSQL("DROP TABLE IF EXISTS Topics");
        db.execSQL("DROP TABLE IF EXISTS Users");

        onCreate(db);
    }
}
