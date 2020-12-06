package com.hoanglam.congthucnauan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class TaiKhoanAdapter extends RecyclerView.Adapter<TaiKhoanAdapter.ViewHD>{
    final LinkedList<String> mDataSet;
    LayoutInflater mInflater;
    //phương thức khởi tạo
    public TaiKhoanAdapter(Context context, LinkedList<String> mDataSet) {
        this.mDataSet = mDataSet;
        this.mInflater = LayoutInflater.from(context);
    }
    //tạo ra đối tượng ViewHolder, trong nó chứa View hiện thị dữ liệu
    @NonNull
    @Override
    public ViewHD onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View iTemView =this.mInflater.inflate(R.layout.recyclerview_tai_khoan,parent,false);
        return new ViewHD(iTemView,this);
    }
    // chuyển dữ liệu phần tử vào ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHD holder, int position) {
    String curstr = this.mDataSet.get(position);
    holder.txttaikhoantenmonan.setText(curstr);
    }
    //cho biết số phần tử của dữ liệu
    @Override
    public int getItemCount() {
        return this.mDataSet.size();
    }
    //tạo lớp kế thừa ViewHolder
    public class ViewHD extends RecyclerView.ViewHolder{
        public final TextView txttaikhoantenmonan;
        final TaiKhoanAdapter mAdapter;

        public ViewHD(@NonNull View itemView, TaiKhoanAdapter mAdapter) {
            super(itemView);
            this.mAdapter = mAdapter;
            this.txttaikhoantenmonan =itemView.findViewById(R.id.txt_taikhoandathichtenmonan);
        }
    }
}
