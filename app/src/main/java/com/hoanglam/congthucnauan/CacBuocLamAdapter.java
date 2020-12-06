package com.hoanglam.congthucnauan;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CacBuocLamAdapter extends RecyclerView.Adapter<CacBuocLamAdapter.CacBuocLamViewHolder> {

    class CacBuocLamViewHolder extends RecyclerView.ViewHolder{

        public CacBuocLamViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    @NonNull
    @Override
    public CacBuocLamAdapter.CacBuocLamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CacBuocLamAdapter.CacBuocLamViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
