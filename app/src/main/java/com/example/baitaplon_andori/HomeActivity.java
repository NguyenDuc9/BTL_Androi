package com.example.baitaplon_andori;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button btnTuVung;
    Button btnChuDe;
    Button btnCauHoi;
    Button btnTuVungver1;
    Button btnChat;
    Button btnDangXuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnTuVung =
                findViewById(R.id.btnTuVung);

        btnCauHoi =
                findViewById(R.id.btnCauHoi);

        btnChuDe =
                findViewById(R.id.btnChuDe);
        btnChat = findViewById(R.id.btnChat);
        btnDangXuat = findViewById(R.id.btlDangXuat);
        btnTuVungver1 =
                findViewById(R.id.btlTuVungver1);

        btnTuVung.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                HomeActivity.this,
                                TuVungDrawable.class)));

        btnCauHoi.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                HomeActivity.this,
                                CauHoiActivity.class)));

        btnChuDe.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                HomeActivity.this,
                                ChuDeActivity.class)));
        btnChat.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                HomeActivity.this,
                                ChatActivity.class)));
        btnDangXuat.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                HomeActivity.this,
                                MainActivity.class)));
        btnTuVungver1.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                HomeActivity.this,
                                TuVungActivity.class)));
    }
}