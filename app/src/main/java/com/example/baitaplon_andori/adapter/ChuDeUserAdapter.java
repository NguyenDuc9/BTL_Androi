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
import java.util.ArrayList;

public class ChuDeUserAdapter
        extends RecyclerView.Adapter<ChuDeUserAdapter.ViewHolder>{

    private Context context;


    private List<ChuDe> list;

    public ChuDeUserAdapter(
            Context context,
            List<ChuDe> list
    ) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        View view =
                LayoutInflater.from(context)
                        .inflate(
                                R.layout.item_chude_user,
                                parent,
                                false
                        );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position
    ) {

        ChuDe chuDe = list.get(position);

        holder.tvTenChuDe.setText(
                chuDe.getTenChuDe()
        );

        holder.btnHoc.setOnClickListener(v -> {
            // Khởi tạo Intent để chuyển từ màn hình hiện tại sang ChiTietTuVungActivity
            android.content.Intent intent = new android.content.Intent(context, com.example.baitaplon_andori.ChiTietTuVungActivity.class);

            // Truyền mã chủ đề (MaChuDe) sang màn hình chi tiết
            // Bạn nhớ kiểm tra xem trong model ChuDe hàm lấy mã là getMaChuDe() hay gì để viết cho đúng nhé
            intent.putExtra("MA_CHU_DE", chuDe.getMaChuDe());

            // Bắt đầu mở màn hình mới
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        TextView tvTenChuDe;
        Button btnHoc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTenChuDe =
                    itemView.findViewById(
                            R.id.tvTenChuDe
                    );

            btnHoc =
                    itemView.findViewById(
                            R.id.btnHoc
                    );
        }
    }
}