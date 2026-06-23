package com.example.baitaplon_andori.model;

public class Flashcard {

    private int flashcardID;
    private int userID;
    private int vocabID;
    private int isRemembered;

    public Flashcard() {
    }

    public Flashcard(int flashcardID,
                     int userID,
                     int vocabID,
                     int isRemembered) {
        this.flashcardID = flashcardID;
        this.userID = userID;
        this.vocabID = vocabID;
        this.isRemembered = isRemembered;
    }

    public int getFlashcardID() {
        return flashcardID;
    }

    public void setFlashcardID(int flashcardID) {
        this.flashcardID = flashcardID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getVocabID() {
        return vocabID;
    }

    public void setVocabID(int vocabID) {
        this.vocabID = vocabID;
    }

    public int getIsRemembered() {
        return isRemembered;
    }

    public void setIsRemembered(int isRemembered) {
        this.isRemembered = isRemembered;
    }
}