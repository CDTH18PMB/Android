package com.hoanglam.congthucnauan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hoanglam.congthucnauan.Class.HuongDan;
import com.hoanglam.congthucnauan.R;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

public class HuongDanAdapter extends RecyclerView.Adapter<HuongDanAdapter.HuongDanViewHolder> {
    private final LinkedList<HuongDan> mHuongDan;
    private LayoutInflater mInflater;

    public HuongDanAdapter(Context context, LinkedList<HuongDan> mHuongDan) {
        mInflater = LayoutInflater.from(context);
        this.mHuongDan = mHuongDan;
    }

    class HuongDanViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_SoBuocLam;
        private TextView txt_BuocLam;
        private ImageView img_BuocLam;
        final HuongDanAdapter mAdapter;

        public HuongDanViewHolder(@NonNull View itemView, HuongDanAdapter mAdapter) {
            super(itemView);
            txt_SoBuocLam = itemView.findViewById(R.id.txt_SoBuocLam);
            txt_BuocLam = itemView.findViewById(R.id.txt_BuocLam);
            img_BuocLam = itemView.findViewById(R.id.img_BuocLam);
            this.mAdapter = mAdapter;
        }
    }
    @NonNull
    @Override
    public HuongDanAdapter.HuongDanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.items_huong_dan,parent,false);
        return new HuongDanViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull HuongDanViewHolder holder, int position) {
        HuongDan huongDan = this.mHuongDan.get(position);

        holder.txt_SoBuocLam.setText("Bước " + (position + 1));
        Picasso.get()
                .load(huongDan.getURL_HinhAnh())
                .fit()
                .into(holder.img_BuocLam);
        holder.txt_BuocLam.setText(huongDan.getBuocLam());
    }

    @Override
    public int getItemCount() {
        return mHuongDan.size();
    }
}
