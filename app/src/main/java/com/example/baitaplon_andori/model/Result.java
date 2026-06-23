package com.example.baitaplon_andori.model;

public class Result {

    private int resultID;
    private int userID;
    private int testID;
    private int score;
    private String testDate;

    public Result() {
    }

    public Result(int resultID,
                  int userID,
                  int testID,
                  int score,
                  String testDate) {

        this.resultID = resultID;
        this.userID = userID;
        this.testID = testID;
        this.score = score;
        this.testDate = testDate;
    }

    public int getResultID() {
        return resultID;
    }

    public void setResultID(int resultID) {
        this.resultID = resultID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }
}