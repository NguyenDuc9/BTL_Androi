package com.example.baitaplon_andori;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DoAnActivity extends AppCompatActivity {

    ImageView imgWord;
    TextView txtEnglish, txtMeaning;
    Button btnMeaning, btnNext, btnBack;

    int index = 0;

    int[] images = {
            R.drawable.rice,
            R.drawable.bread,
            R.drawable.noodle,
            R.drawable.cake
    };

    String[] english = {
            "Rice",
            "Bread",
            "instant noodles",
            "Cake"
    };

    String[] meaning = {
            "Cơm",
            "Bánh mì",
            "Mì Tôm",
            "Bánh ngọt"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_an);

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