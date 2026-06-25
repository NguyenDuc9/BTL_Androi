package com.example.baitaplon_andori.adapter;

import android.content.Context;
import android.net.Uri;
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

public class TuVungAdapter extends RecyclerView.Adapter<TuVungAdapter.ViewHolder>{

    private Context context;
    private List<TuVung> list;
    private TuVungListener listener;

    public TuVungAdapter(Context context, List<TuVung> list){
        this.context = context;
        this.list = list;
    }

    public TuVungAdapter(Context context, List<TuVung> list, TuVungListener listener){
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.item_tuvung, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // 1. Kiểm tra an toàn danh sách đầu vào
        if (list == null || position >= list.size()) {
            return;
        }

        TuVung tv = list.get(position);
        if (tv == null) return;

        // 2. ĐỔ DỮ LIỆU VĂN BẢN (ĐÃ SỬA: Đổi thành holder.txtTuAnh và holder.txtNghia cho khớp ViewHolder)
        holder.txtTuAnh.setText(tv.getTuTiengAnh() != null ? tv.getTuTiengAnh() : "");
        holder.txtNghia.setText(tv.getNghiaTiengViet() != null ? tv.getNghiaTiengViet() : "");

        // 3. XỬ LÝ HÌNH ẢNH (ĐÃ SỬA: Đổi thành holder.img cho khớp ViewHolder của Đức)
        String pathAnh = tv.getHinhAnh();
        try {
            if (pathAnh != null && !pathAnh.isEmpty()) {
                Uri uri = Uri.parse(pathAnh);
                holder.img.setImageURI(uri);
            } else {
                holder.img.setImageResource(R.drawable.ic_launcher_background);
            }
        } catch (Exception e) {
            holder.img.setImageResource(R.drawable.ic_launcher_background);
            e.printStackTrace();
        }

        // 4. Lắng nghe sự kiện click nút bấm sửa/xóa an toàn
        if (listener != null) {
            holder.btnSua.setOnClickListener(v -> listener.onEdit(tv));
            holder.btnXoa.setOnClickListener(v -> listener.onDelete(tv));
        }
    }

    @Override
    public int getItemCount(){
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtTuAnh, txtNghia;
        ImageView img;
        Button btnSua, btnXoa;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            txtTuAnh = itemView.findViewById(R.id.txtTuAnh);
            txtNghia = itemView.findViewById(R.id.txtNghia);
            img = itemView.findViewById(R.id.imgTuVung);
            btnSua = itemView.findViewById(R.id.btnSua);
            btnXoa = itemView.findViewById(R.id.btnXoa);
        }
    }
}