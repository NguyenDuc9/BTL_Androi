package com.example.baitaplon_andori;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplon_andori.adapter.VocabularyAdapter;
import com.example.baitaplon_andori.data.VocabularyDAO;
import com.example.baitaplon_andori.model.Vocabulary;

import java.util.ArrayList;

public class VocabularyActivity extends AppCompatActivity {

    RecyclerView rvVocabulary;

    VocabularyDAO dao;

    VocabularyAdapter adapter;

    ArrayList<Vocabulary> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);

        rvVocabulary =
                findViewById(R.id.rvVocabulary);

        dao =
                new VocabularyDAO(this);

        list =
                dao.getAll();

        adapter =
                new VocabularyAdapter(list);

        rvVocabulary.setLayoutManager(
                new LinearLayoutManager(this));

        rvVocabulary.setAdapter(adapter);
    }
}