package com.hoanglam.congthucnauan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hoanglam.congthucnauan.ChiTietMonAnActivity;
import com.hoanglam.congthucnauan.Class.DanhMuc;
import com.hoanglam.congthucnauan.R;
import com.hoanglam.congthucnauan.activity_dangky;
import com.hoanglam.congthucnauan.activity_tim_mon_an;
import com.hoanglam.congthucnauan.activity_timkiem;

import java.util.LinkedList;

public class HienThiDanhMucAdapter extends RecyclerView.Adapter<HienThiDanhMucAdapter.HienThiDanhMucViewHolder> {
    private final LinkedList<DanhMuc> mDanhMuc;
    private final LayoutInflater mInflater;
    private Context context;
    private Activity activity;

    public HienThiDanhMucAdapter(Context context, LinkedList<DanhMuc> danmuc,Activity activity) {
        mInflater = LayoutInflater.from(context);
        this.mDanhMuc = danmuc;
        this.context=context;
        this.activity = activity;

    }
    @NonNull
    @Override
    public HienThiDanhMucAdapter.HienThiDanhMucViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(
                R.layout.items_danhmuc, parent, false);
        return new HienThiDanhMucViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull HienThiDanhMucAdapter.HienThiDanhMucViewHolder holder, int position) {
        DanhMuc danhMuc = mDanhMuc.get(position);
        // Add the data to the view holder.
        holder.TimItemView.setText(danhMuc.getTenLoai());
        holder.TimItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TenLoai = danhMuc.getTenLoai();
                Intent intent = new Intent(activity,activity_tim_mon_an.class);
                intent.putExtra("TenLoai",TenLoai);
                activity.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return mDanhMuc.size();
    }

    public class HienThiDanhMucViewHolder extends RecyclerView.ViewHolder{
        public final TextView TimItemView;
        final HienThiDanhMucAdapter mAdapter;

        /**
         * Creates a new custom view holder to hold the view to display in
         * the RecyclerView.
         *
         * @param itemView The view in which to display the data.
         * @param adapter  The adapter that manages the the data and views
         *                 for the RecyclerView.
         */
        public HienThiDanhMucViewHolder(View itemView, HienThiDanhMucAdapter adapter) {
            super(itemView);
            TimItemView = itemView.findViewById(R.id.txt_TenLoai);
            this.mAdapter = adapter;

        }
    }

}
