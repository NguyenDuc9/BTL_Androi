package com.example.baitaplon_andori;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PhuongTienActivity extends AppCompatActivity {

    ImageView imgWord;
    TextView txtEnglish, txtMeaning;
    Button btnMeaning, btnNext, btnBack;

    int index = 0;

    int[] images = {
            R.drawable.car,
            R.drawable.motorbike,
            R.drawable.bicycle,
            R.drawable.train
    };

    String[] english = {
            "Car",
            "Motorbike",
            "Bicycle",
            "Train"
    };

    String[] meaning = {
            "Xe hơi",
            "Xe máy",
            "Xe đạp",
            "Tàu hỏa"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu_vung);

        imgWord = findViewById(R.id.imgWord);
        txtEnglish = findViewById(R.id.txtEnglish);
        txtMeaning = findViewById(R.id.txtMeaning);
        btnMeaning = findViewById(R.id.btnMeaning);
        btnNext = findViewById(R.id.btnNext);
        btnBack = findViewById(R.id.btnBack);

        hienThi();

        btnMeaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMeaning.setVisibility(View.VISIBLE);
                txtMeaning.setText(meaning[index]);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                index++;

                if (index >= images.length) {
                    index = 0;
                }

                hienThi();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void hienThi() {
        imgWord.setImageResource(images[index]);
        txtEnglish.setText(english[index]);

        txtMeaning.setVisibility(View.GONE);
        txtMeaning.setText("");
    }
}