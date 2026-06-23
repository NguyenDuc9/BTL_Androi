package com.example.baitaplon_andori.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplon_andori.R;
import com.example.baitaplon_andori.model.Topic;

import java.util.ArrayList;

public class TopicAdapter
        extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {

    private ArrayList<Topic> list;

    public TopicAdapter(ArrayList<Topic> list) {
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTopicName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTopicName =
                    itemView.findViewById(R.id.txtTopicName);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_topic,
                        parent,
                        false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        Topic topic = list.get(position);

        holder.txtTopicName.setText(
                topic.getTopicName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}