package com.hoanglam.congthucnauan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hoanglam.congthucnauan.ChiTietMonAnActivity;
import com.hoanglam.congthucnauan.Class.MonAn;
import com.hoanglam.congthucnauan.R;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

public class ListMonAnAdapter extends RecyclerView.Adapter<ListMonAnAdapter.ListMonAnViewHolder> {
    private LinkedList<MonAn> mMonAn;
    private LayoutInflater mInflater;
    private Context context;

    public ListMonAnAdapter(Context context, LinkedList<MonAn> mMonAn) {

        this.mMonAn = mMonAn;
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ListMonAnAdapter.ListMonAnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.items_mon_an, parent, false);
        return new ListMonAnAdapter.ListMonAnViewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMonAnAdapter.ListMonAnViewHolder holder, int position) {
        MonAn monAn = mMonAn.get(position);

        Picasso.get()
                .load(monAn.getAnhDaiDien())
                .fit()
                .into(holder.img_AnhDaiDien);
        holder.txt_TenMon.setText(monAn.getTenMon());
        holder.txt_LuotXem.setText(monAn.getLuotXem());
        holder.txt_LuotThich.setText(monAn.getLuotThich());
        holder.txt_NguoiTao.setText(monAn.getNguoiTao());
        holder.txt_ThoiGianNau.setText(monAn.getThoiGianNau());
    }

    @Override
    public int getItemCount() {
        return mMonAn.size();
    }

    public class ListMonAnViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_AnhDaiDien;
        private TextView txt_TenMon;
        private TextView txt_LuotXem;
        private TextView txt_LuotThich;
        private TextView txt_NguoiTao;
        private TextView txt_ThoiGianNau;
        final ListMonAnAdapter mAdapter;

        public ListMonAnViewHolder(@NonNull View itemView, ListMonAnAdapter mAdapter) {
            super(itemView);
            this.mAdapter = mAdapter;
            img_AnhDaiDien = itemView.findViewById(R.id.img_MonAn);
            txt_TenMon = itemView.findViewById(R.id.txt_TenMon_MonAn);
            txt_LuotXem = itemView.findViewById(R.id.txt_LuotXem_MonAn);
            txt_LuotThich = itemView.findViewById(R.id.txt_LuotThich_MonAn);
            txt_NguoiTao = itemView.findViewById(R.id.txt_NguoiTao_MonAn);
            txt_ThoiGianNau = itemView.findViewById(R.id.txt_ThoiGianNau_MonAn);

        }
    }
}
