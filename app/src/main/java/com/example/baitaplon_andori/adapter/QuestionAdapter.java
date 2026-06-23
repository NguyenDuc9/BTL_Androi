package com.example.baitaplon_andori.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplon_andori.R;
import com.example.baitaplon_andori.model.Question;

import java.util.ArrayList;

public class QuestionAdapter
        extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>{

    private ArrayList<Question> list;

    public QuestionAdapter(ArrayList<Question> list){
        this.list = list;
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder{

        TextView txtQuestion;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            txtQuestion =
                    itemView.findViewById(R.id.txtQuestion);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType){

        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(
                                R.layout.item_question,
                                parent,
                                false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position){

        Question q = list.get(position);

        holder.txtQuestion.setText(
                q.getQuestionText());
    }

    @Override
    public int getItemCount(){
        return list.size();
    }
}