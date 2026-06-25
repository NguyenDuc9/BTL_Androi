package com.example.baitaplon_andori.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplon_andori.R;
import com.example.baitaplon_andori.model.ChuDe;

import java.util.List;

public class ChuDeAdapter extends RecyclerView.Adapter<ChuDeAdapter.ViewHolder> {

    private Context context;
    private List<ChuDe> list;
    private ChuDeListener listener;

    public ChuDeAdapter(
            Context context,
            List<ChuDe> list,
            ChuDeListener listener) {

        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_chude, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        ChuDe chuDe = list.get(position);

        holder.txtTenChuDe.setText(chuDe.getTenChuDe());

        holder.btnSua.setOnClickListener(v -> {
            listener.onEdit(chuDe);
        });

        holder.btnXoa.setOnClickListener(v -> {
            listener.onDelete(chuDe);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTenChuDe;
        Button btnSua;
        Button btnXoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTenChuDe =
                    itemView.findViewById(R.id.txtTenChuDe);

            btnSua =
                    itemView.findViewById(R.id.btnSua);

            btnXoa =
                    itemView.findViewById(R.id.btnXoa);
        }
    }
}