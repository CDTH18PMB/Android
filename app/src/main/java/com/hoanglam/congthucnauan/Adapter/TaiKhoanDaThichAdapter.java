package com.hoanglam.congthucnauan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hoanglam.congthucnauan.R;

import java.util.LinkedList;

public class TaiKhoanDaThichAdapter extends RecyclerView.Adapter<TaiKhoanDaThichAdapter.ViewHD>{
    final LinkedList<String> mDataSet1;
    final LinkedList<String> mDataSet2;
    LayoutInflater mInflater;

    public TaiKhoanDaThichAdapter(Context context, LinkedList<String> mDataSet1, LinkedList<String> mDataSet2) {
        this.mDataSet1 = mDataSet1;
        this.mDataSet2 = mDataSet2;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHD onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View iTemView = this.mInflater.inflate(R.layout.recyclerview_tai_khoan_da_thich,parent,false);
        return new ViewHD(iTemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHD holder, int position) {
        String curstr1 =this.mDataSet1.get(position);
        holder.txttaikhoandathichtenmonan.setText(curstr1);
        String curstr2 =this.mDataSet2.get(position);
        holder.txttaikhoandathichtennguoitao.setText(curstr2);
    }

    @Override
    public int getItemCount() {
        return this.mDataSet1.size();
    }

    public class ViewHD extends RecyclerView.ViewHolder {
        public final TextView txttaikhoandathichtenmonan;
        public final TextView txttaikhoandathichtennguoitao;
        final TaiKhoanDaThichAdapter mAdapter;

        public ViewHD(@NonNull View itemView, TaiKhoanDaThichAdapter mAdapter) {
            super(itemView);
            this.mAdapter = mAdapter;
            this.txttaikhoandathichtenmonan = itemView.findViewById(R.id.txt_taikhoandathichtenmonan);
            this.txttaikhoandathichtennguoitao = itemView.findViewById(R.id.txt_taikhoandathichtennguoitao);
        }
    }
}
