package com.example.baitaplon_andori;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button btnVocabulary;
    Button btnTest;
    Button btnResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnVocabulary =
                findViewById(R.id.btnVocabulary);

        btnTest =
                findViewById(R.id.btnTest);

        btnResult =
                findViewById(R.id.btnResult);

        btnVocabulary.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                HomeActivity.this,
                                VocabularyActivity.class)));

        btnTest.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                HomeActivity.this,
                                TestActivity.class)));

        btnResult.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                HomeActivity.this,
                                ResultActivity.class)));
    }
}