package com.example.baitaplon_andori.model;

public class TestDetail {

    private int testID;
    private int questionID;

    public TestDetail() {
    }

    public TestDetail(int testID,
                      int questionID) {
        this.testID = testID;
        this.questionID = questionID;
    }

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }
}