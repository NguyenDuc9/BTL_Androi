package com.example.baitaplon_andori.model;


public class Vocabulary {

    private int vocabID;
    private String word;
    private String meaning;
    private String pronunciation;
    private String example;
    private int topicID;

    public Vocabulary() {
    }

    public Vocabulary(int vocabID, String word,
                      String meaning,
                      String pronunciation,
                      String example,
                      int topicID) {

        this.vocabID = vocabID;
        this.word = word;
        this.meaning = meaning;
        this.pronunciation = pronunciation;
        this.example = example;
        this.topicID = topicID;
    }

    public int getVocabID() {
        return vocabID;
    }

    public void setVocabID(int vocabID) {
        this.vocabID = vocabID;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public int getTopicID() {
        return topicID;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }
}