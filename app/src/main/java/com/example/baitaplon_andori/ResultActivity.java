package com.example.baitaplon_andori;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplon_andori.adapter.ResultAdapter;
import com.example.baitaplon_andori.data.ResultDAO;
import com.example.baitaplon_andori.model.Result;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    RecyclerView rvResult;

    ResultDAO dao;

    ResultAdapter adapter;

    ArrayList<Result> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        rvResult =
                findViewById(R.id.rvResult);

        dao =
                new ResultDAO(this);

        list =
                dao.getAll();

        adapter =
                new ResultAdapter(list);

        rvResult.setLayoutManager(
                new LinearLayoutManager(this));

        rvResult.setAdapter(adapter);
    }
}