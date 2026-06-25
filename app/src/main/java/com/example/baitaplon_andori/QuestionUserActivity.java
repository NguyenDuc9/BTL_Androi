package com.example.baitaplon_andori;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baitaplon_andori.data.CauHoiDAO;
import com.example.baitaplon_andori.model.CauHoi;

import java.util.List;

public class QuestionUserActivity extends AppCompatActivity {

    private TextView tvSoCau;
    private TextView tvNoiDung;
    private TextView tvKetQua;
    private TextView tvGiaiThich;

    private RadioGroup rgDapAn;

    private RadioButton rbA;
    private RadioButton rbB;
    private RadioButton rbC;
    private RadioButton rbD;

    private Button btnCheck;
    private Button btnPrev;
    private Button btnNext;

    private CauHoiDAO dao;

    private List<CauHoi> list;

    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_user);

        tvSoCau = findViewById(R.id.tvSoCau);
        tvNoiDung = findViewById(R.id.tvNoiDung);
        tvKetQua = findViewById(R.id.tvKetQua);
        tvGiaiThich = findViewById(R.id.tvGiaiThich);

        rgDapAn = findViewById(R.id.rgDapAn);

        rbA = findViewById(R.id.rbA);
        rbB = findViewById(R.id.rbB);
        rbC = findViewById(R.id.rbC);
        rbD = findViewById(R.id.rbD);

        btnCheck = findViewById(R.id.btnCheck);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);

        dao = new CauHoiDAO(this);

        list = dao.getAll();

        if (list == null || list.isEmpty()) {

            Toast.makeText(
                    this,
                    "Chưa có câu hỏi",
                    Toast.LENGTH_SHORT
            ).show();

            finish();
            return;
        }

        showQuestion();

        btnCheck.setOnClickListener(v -> checkAnswer());

        btnNext.setOnClickListener(v -> {

            if (currentIndex < list.size() - 1) {

                currentIndex++;

                showQuestion();

            } else {

                Toast.makeText(
                        this,
                        "Đây là câu cuối cùng",
                        Toast.LENGTH_SHORT
                ).show();
            }

        });

        btnPrev.setOnClickListener(v -> {

            if (currentIndex > 0) {

                currentIndex--;

                showQuestion();

            } else {

                Toast.makeText(
                        this,
                        "Đây là câu đầu tiên",
                        Toast.LENGTH_SHORT
                ).show();
            }

        });
    }

    private void showQuestion() {

        CauHoi q = list.get(currentIndex);

        tvSoCau.setText(
                "Câu "
                        + (currentIndex + 1)
                        + "/"
                        + list.size()
        );

        tvNoiDung.setText(
                q.getNoiDung()
        );

        rbA.setText(
                "A. " + q.getDapAnA()
        );

        rbB.setText(
                "B. " + q.getDapAnB()
        );

        rbC.setText(
                "C. " + q.getDapAnC()
        );

        rbD.setText(
                "D. " + q.getDapAnD()
        );

        rgDapAn.clearCheck();

        tvKetQua.setText("");

        tvGiaiThich.setText("");

        rbA.setTextColor(Color.BLACK);
        rbB.setTextColor(Color.BLACK);
        rbC.setTextColor(Color.BLACK);
        rbD.setTextColor(Color.BLACK);
    }

    private void checkAnswer() {

        String selected = getSelectedAnswer();

        if (selected.isEmpty()) {

            Toast.makeText(
                    this,
                    "Vui lòng chọn đáp án",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        CauHoi q = list.get(currentIndex);

        String correct =
                q.getDapAnDung();

        tvGiaiThich.setText(
                "Giải thích:\n"
                        + q.getGiaiThich()
        );

        if (selected.equalsIgnoreCase(correct)) {

            tvKetQua.setText(
                    "✓ Chính xác"
            );

            tvKetQua.setTextColor(
                    Color.GREEN
            );

            highlightCorrect(correct);

        } else {

            tvKetQua.setText(
                    "✗ Sai\nĐáp án đúng: "
                            + correct
            );

            tvKetQua.setTextColor(
                    Color.RED
            );

            highlightWrong(correct);
        }
    }

    private String getSelectedAnswer() {

        int id =
                rgDapAn.getCheckedRadioButtonId();

        if (id == R.id.rbA) return "A";

        if (id == R.id.rbB) return "B";

        if (id == R.id.rbC) return "C";

        if (id == R.id.rbD) return "D";

        return "";
    }

    private RadioButton getRadioButton(
            String answer
    ) {

        switch (answer.toUpperCase()) {

            case "A":
                return rbA;

            case "B":
                return rbB;

            case "C":
                return rbC;

            default:
                return rbD;
        }
    }

    private void highlightCorrect(
            String answer
    ) {

        RadioButton rb =
                getRadioButton(answer);

        rb.setTextColor(
                Color.GREEN
        );
    }

    private void highlightWrong(
            String answer
    ) {

        RadioButton rb =
                getRadioButton(answer);

        rb.setTextColor(
                Color.RED
        );
    }
}