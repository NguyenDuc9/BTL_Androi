package com.example.baitaplon_andori.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplon_andori.R;
import com.example.baitaplon_andori.model.TuVung;

import java.util.List;

public class TuVungDrawableAdapter extends RecyclerView.Adapter<TuVungDrawableAdapter.ViewHolder> {

    private Context context;
    private List<TuVung> list;
    private TuVungListener listener;

    public TuVungDrawableAdapter(Context context, List<TuVung> list) {
        this.context = context;
        this.list = list;
    }

    public TuVungDrawableAdapter(Context context, List<TuVung> list, TuVungListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tuvung_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list == null || position >= list.size()) {
            return;
        }

        TuVung tv = list.get(position);
        if (tv == null) return;

        // Đổ dữ liệu văn bản an toàn
        holder.txtTuAnh.setText(tv.getTuTiengAnh() != null ? tv.getTuTiengAnh() : "");
        holder.txtNghia.setText(tv.getNghiaTiengViet() != null ? tv.getNghiaTiengViet() : "");

        // Tìm nạp ID ảnh trực tiếp từ thư mục res/drawable theo chuỗi kí tự tên ảnh
        String nameAnh = tv.getHinhAnh();
        try {
            if (nameAnh != null && !nameAnh.isEmpty()) {
                int resId = context.getResources().getIdentifier(nameAnh, "drawable", context.getPackageName());
                if (resId != 0) {
                    holder.img.setImageResource(resId);
                } else {
                    holder.img.setImageResource(R.drawable.ic_launcher_background);
                }
            } else {
                holder.img.setImageResource(R.drawable.ic_launcher_background);
            }
        } catch (Exception e) {
            holder.img.setImageResource(R.drawable.ic_launcher_background);
            e.printStackTrace();
        }

        // Ủy quyền sự kiện click thông qua Listener
        if (listener != null) {
            holder.btnSua.setOnClickListener(v -> listener.onEdit(tv));
            holder.btnXoa.setOnClickListener(v -> listener.onDelete(tv));
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTuAnh, txtNghia;
        ImageView img;
        Button btnSua, btnXoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTuAnh = itemView.findViewById(R.id.txtTuAnh);
            txtNghia = itemView.findViewById(R.id.txtNghia);
            img = itemView.findViewById(R.id.imgTuVung);
            btnSua = itemView.findViewById(R.id.btnSua);
            btnXoa = itemView.findViewById(R.id.btnXoa);
        }
    }
}