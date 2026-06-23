package com.example.baitaplon_andori;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baitaplon_andori.data.QuestionDAO;
import com.example.baitaplon_andori.model.Question;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    TextView txtQuestion;

    RadioButton rbA,rbB,rbC,rbD;

    Button btnSubmit;

    QuestionDAO dao;

    ArrayList<Question> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        txtQuestion =
                findViewById(R.id.txtQuestion);

        rbA =
                findViewById(R.id.rbA);

        rbB =
                findViewById(R.id.rbB);

        rbC =
                findViewById(R.id.rbC);

        rbD =
                findViewById(R.id.rbD);

        btnSubmit =
                findViewById(R.id.btnSubmit);

        dao =
                new QuestionDAO(this);

        list =
                dao.getAll();

        if(list.size() > 0){

            Question q = list.get(0);

            txtQuestion.setText(
                    q.getQuestionText());

            rbA.setText(q.getOptionA());
            rbB.setText(q.getOptionB());
            rbC.setText(q.getOptionC());
            rbD.setText(q.getOptionD());
        }

        btnSubmit.setOnClickListener(v ->
                Toast.makeText(
                        this,
                        "Đã nộp bài",
                        Toast.LENGTH_SHORT
                ).show());
    }
}