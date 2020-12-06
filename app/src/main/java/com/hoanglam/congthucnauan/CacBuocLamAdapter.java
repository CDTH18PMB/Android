package com.hoanglam.congthucnauan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class CacBuocLamAdapter extends RecyclerView.Adapter<CacBuocLamAdapter.CacBuocLamViewHolder> {
    private final LinkedList<CacBuocLam> cacBuocLam;
    private LayoutInflater mInfalter;

    public CacBuocLamAdapter(Context context, LinkedList<CacBuocLam> cacBuocLam) {
        this.cacBuocLam = cacBuocLam;
        this.mInfalter = LayoutInflater.from(context);
    }

    class CacBuocLamViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_Step;
        private TextView txt_Step;
        private EditText txt_Content;
        final CacBuocLamAdapter mAdapter;

        public CacBuocLamViewHolder(@NonNull View itemView, CacBuocLamAdapter mAdapter) {
            super(itemView);
            this.mAdapter = mAdapter;
            this.img_Step = itemView.findViewById(R.id.img_Step);
            this.txt_Step = itemView.findViewById(R.id.txt_Step);
            this.txt_Content = itemView.findViewById(R.id.txt_Content);
        }
    }
    @NonNull
    @Override
    public CacBuocLamAdapter.CacBuocLamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInfalter.inflate(R.layout.buoc_lam_items,parent,false);
        return new CacBuocLamViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CacBuocLamAdapter.CacBuocLamViewHolder holder, int position) {
        CacBuocLam mCacBuocLam = this.cacBuocLam.get(position);
        holder.txt_Step.setText(mCacBuocLam.getStep());
        holder.txt_Content.setText(mCacBuocLam.getContent());
    }

    @Override
    public int getItemCount() {
        return this.cacBuocLam.size();
    }
}
