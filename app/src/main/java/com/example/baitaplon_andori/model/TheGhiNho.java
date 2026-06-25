package com.example.baitaplon_andori.model;
public class TheGhiNho {

    private int maThe;
    private int maTu;
    private int daNho;

    public TheGhiNho() {
    }

    public TheGhiNho(int maThe, int maTu, int daNho) {
        this.maThe = maThe;
        this.maTu = maTu;
        this.daNho = daNho;
    }

    public int getMaThe() {
        return maThe;
    }

    public void setMaThe(int maThe) {
        this.maThe = maThe;
    }

    public int getMaTu() {
        return maTu;
    }

    public void setMaTu(int maTu) {
        this.maTu = maTu;
    }

    public int getDaNho() {
        return daNho;
    }

    public void setDaNho(int daNho) {
        this.daNho = daNho;
    }
}