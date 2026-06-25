package com.example.baitaplon_andori.adapter;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplon_andori.R;
import com.example.baitaplon_andori.model.ChatMessage;

import java.util.List;

public class ChatAdapter
        extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private List<ChatMessage> list;

    public ChatAdapter(List<ChatMessage> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.item_chat,
                        parent,
                        false
                );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        ChatMessage msg = list.get(position);

        holder.txtMessage.setText(
                msg.getMessage()
        );

        LinearLayout.LayoutParams params =
                (LinearLayout.LayoutParams)
                        holder.txtMessage.getLayoutParams();

        if (msg.isUser()) {

            params.gravity = Gravity.END;

            holder.txtMessage.setBackgroundColor(
                    Color.parseColor("#DCF8C6")
            );

        } else {

            params.gravity = Gravity.START;

            holder.txtMessage.setBackgroundColor(
                    Color.parseColor("#EEEEEE")
            );
        }

        holder.txtMessage.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder
            extends RecyclerView.ViewHolder {

        TextView txtMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMessage =
                    itemView.findViewById(
                            R.id.txtMessage
                    );
        }
    }
}