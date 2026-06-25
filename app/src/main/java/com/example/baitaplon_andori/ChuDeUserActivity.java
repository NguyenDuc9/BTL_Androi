package com.example.baitaplon_andori;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplon_andori.adapter.ChuDeUserAdapter;
import com.example.baitaplon_andori.data.ChuDeDAO;

public class ChuDeUserActivity extends AppCompatActivity {

    private RecyclerView rvChuDe;

    private ChuDeDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chude_user);

        rvChuDe = findViewById(R.id.rvChuDe);

        dao = new ChuDeDAO(this);

        rvChuDe.setLayoutManager(
                new LinearLayoutManager(this)
        );

        rvChuDe.setAdapter(
                new ChuDeUserAdapter(
                        this,
                        dao.getAll()
                )
        );
    }
}