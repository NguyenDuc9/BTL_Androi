package com.example.baitaplon_andori.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplon_andori.R;
import com.example.baitaplon_andori.model.Result;

import java.util.ArrayList;

public class ResultAdapter
        extends RecyclerView.Adapter<ResultAdapter.ViewHolder>{

    private ArrayList<Result> list;

    public ResultAdapter(ArrayList<Result> list){
        this.list = list;
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder{

        TextView txtScore;
        TextView txtDate;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            txtScore =
                    itemView.findViewById(R.id.txtScore);

            txtDate =
                    itemView.findViewById(R.id.txtDate);
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
                                R.layout.item_result,
                                parent,
                                false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position){

        Result result =
                list.get(position);

        holder.txtScore.setText(
                "Điểm: " + result.getScore());

        holder.txtDate.setText(
                result.getTestDate());
    }

    @Override
    public int getItemCount(){
        return list.size();
    }
}