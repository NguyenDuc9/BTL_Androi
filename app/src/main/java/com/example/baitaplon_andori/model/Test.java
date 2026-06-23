package com.example.baitaplon_andori.model;

public class Test {

    private int testID;
    private String testName;
    private int totalQuestions;

    public Test() {
    }

    public Test(int testID,
                String testName,
                int totalQuestions) {

        this.testID = testID;
        this.testName = testName;
        this.totalQuestions = totalQuestions;
    }

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }
}