package com.example.baitaplon_andori;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baitaplon_andori.model.ChuDe;

public class HomeUserActivity extends AppCompatActivity {

    Button btnTuVung;
    Button btnTuVung1;

    Button btnCauHoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeuser);


        btnCauHoi =
                findViewById(R.id.btnCauHoi);

        btnTuVung =
                findViewById(R.id.btnTuVung);
        btnTuVung1 =
                findViewById(R.id.btnTuVung1);

//        btnTuVung.setOnClickListener(v ->
//                startActivity(
//                        new Intent(
//                                HomeActivity.this,
//                                TuVungActivity.class)));
//
        btnCauHoi.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                HomeUserActivity.this,
                                QuestionUserActivity.class)));

        btnTuVung.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                HomeUserActivity.this,
                                ChuDeUser1.class)));
        btnTuVung1.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                HomeUserActivity.this,
                                ChuDeUserActivity.class)));
    }
}