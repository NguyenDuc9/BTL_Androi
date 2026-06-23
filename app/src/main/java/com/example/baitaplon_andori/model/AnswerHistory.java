package com.example.baitaplon_andori.model;

public class AnswerHistory {

    private int historyID;
    private int userID;
    private int questionID;
    private String selectedAnswer;
    private int isCorrect;
    private String answerDate;

    public AnswerHistory() {
    }

    public AnswerHistory(int historyID,
                         int userID,
                         int questionID,
                         String selectedAnswer,
                         int isCorrect,
                         String answerDate) {

        this.historyID = historyID;
        this.userID = userID;
        this.questionID = questionID;
        this.selectedAnswer = selectedAnswer;
        this.isCorrect = isCorrect;
        this.answerDate = answerDate;
    }

    public int getHistoryID() {
        return historyID;
    }

    public void setHistoryID(int historyID) {
        this.historyID = historyID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public int getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(int isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(String answerDate) {
        this.answerDate = answerDate;
    }
}
