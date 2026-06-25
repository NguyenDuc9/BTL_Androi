package com.example.baitaplon_andori.model;
public class BaiTest {

    private int maBaiTest;
    private String tenBaiTest;
    private int tongSoCau;

    public BaiTest() {
    }

    public BaiTest(int maBaiTest, String tenBaiTest, int tongSoCau) {
        this.maBaiTest = maBaiTest;
        this.tenBaiTest = tenBaiTest;
        this.tongSoCau = tongSoCau;
    }

    public int getMaBaiTest() {
        return maBaiTest;
    }

    public void setMaBaiTest(int maBaiTest) {
        this.maBaiTest = maBaiTest;
    }

    public String getTenBaiTest() {
        return tenBaiTest;
    }

    public void setTenBaiTest(String tenBaiTest) {
        this.tenBaiTest = tenBaiTest;
    }

    public int getTongSoCau() {
        return tongSoCau;
    }

    public void setTongSoCau(int tongSoCau) {
        this.tongSoCau = tongSoCau;
    }
}