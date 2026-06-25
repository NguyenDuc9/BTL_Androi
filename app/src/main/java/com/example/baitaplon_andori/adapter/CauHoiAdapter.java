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
import com.example.baitaplon_andori.model.CauHoi;

import java.util.ArrayList;

public class CauHoiAdapter extends RecyclerView.Adapter<CauHoiAdapter.ViewHolder> {

    private Context context;
    private ArrayList<CauHoi> list;
    private CauHoiListener listener;

    public CauHoiAdapter(Context context, ArrayList<CauHoi> list, CauHoiListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cauhoi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CauHoi q = list.get(position);

        holder.tvNoiDung.setText(q.getNoiDung());
        holder.tvDapAnDung.setText("Đáp án đúng: " + q.getDapAnDung());

        // CHUẨN HÓA: Sử dụng getAdapterPosition() để lấy đúng phần tử khi click, tránh lệch vị trí khi cuộn nhanh
        holder.btnSua.setOnClickListener(v -> {
            int currentPos = holder.getAdapterPosition();
            if (currentPos != RecyclerView.NO_POSITION && listener != null) {
                listener.onEdit(list.get(currentPos));
            }
        });

        holder.btnXoa.setOnClickListener(v -> {
            int currentPos = holder.getAdapterPosition();
            if (currentPos != RecyclerView.NO_POSITION && listener != null) {
                listener.onDelete(list.get(currentPos));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNoiDung;
        TextView tvDapAnDung;
        Button btnSua;
        Button btnXoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNoiDung = itemView.findViewById(R.id.tvNoiDung);
            tvDapAnDung = itemView.findViewById(R.id.tvDapAnDung);
            btnSua = itemView.findViewById(R.id.btnSua);
            btnXoa = itemView.findViewById(R.id.btnXoa);
        }
    }
}