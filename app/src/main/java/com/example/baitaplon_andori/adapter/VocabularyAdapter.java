package com.example.baitaplon_andori.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplon_andori.R;
import com.example.baitaplon_andori.model.Vocabulary;

import java.util.ArrayList;

public class VocabularyAdapter
        extends RecyclerView.Adapter<VocabularyAdapter.ViewHolder> {

    private ArrayList<Vocabulary> list;

    public VocabularyAdapter(ArrayList<Vocabulary> list) {
        this.list = list;
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        TextView txtWord;
        TextView txtMeaning;
        TextView txtExample;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtWord =
                    itemView.findViewById(R.id.txtWord);

            txtMeaning =
                    itemView.findViewById(R.id.txtMeaning);

            txtExample =
                    itemView.findViewById(R.id.txtExample);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(
                                R.layout.item_vocabulary,
                                parent,
                                false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        Vocabulary vocab =
                list.get(position);

        holder.txtWord.setText(
                vocab.getWord());

        holder.txtMeaning.setText(
                vocab.getMeaning());

        holder.txtExample.setText(
                vocab.getExample());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}