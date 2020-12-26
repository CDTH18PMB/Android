package com.hoanglam.congthucnauan.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hoanglam.congthucnauan.CacBuocLam;
import com.hoanglam.congthucnauan.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedList;

public class CacBuocLamAdapter extends RecyclerView.Adapter<CacBuocLamAdapter.CacBuocLamViewHolder> {
    private final LinkedList<CacBuocLam> cacBuocLam;
    private LayoutInflater mInfalter;
    private Context context;

    public CacBuocLamAdapter(Context context, LinkedList<CacBuocLam> cacBuocLam) {
        this.cacBuocLam = cacBuocLam;
        this.mInfalter = LayoutInflater.from(context);
        this.context = context;
    }

    class CacBuocLamViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_Step_Ryc;
        private TextView txt_Step_Ryc;
        private EditText txt_Content_Ryc;
        final CacBuocLamAdapter mAdapter;

        public CacBuocLamViewHolder(@NonNull View itemView, CacBuocLamAdapter mAdapter) {
            super(itemView);
            this.mAdapter = mAdapter;
            this.img_Step_Ryc = itemView.findViewById(R.id.img_Step_Ryc);
            this.txt_Step_Ryc = itemView.findViewById(R.id.txt_Step_Ryc);
            this.txt_Content_Ryc = itemView.findViewById(R.id.txt_Content_Ryc);
        }
    }
    @NonNull
    @Override
    public CacBuocLamAdapter.CacBuocLamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInfalter.inflate(R.layout.items_buoc_lam,parent,false);
        return new CacBuocLamViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CacBuocLamAdapter.CacBuocLamViewHolder holder, int position) {
        CacBuocLam mCacBuocLam = this.cacBuocLam.get(position);

        String uri = mCacBuocLam.getImage();

        InputStream imageStream = null;
        try {
            imageStream = context.getContentResolver().openInputStream(Uri.parse(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap photo = BitmapFactory.decodeStream(imageStream);

        holder.img_Step_Ryc.setImageBitmap(photo);
        holder.txt_Step_Ryc.setText(mCacBuocLam.getStep());
        holder.txt_Content_Ryc.setText(mCacBuocLam.getContent());
    }

    @Override
    public int getItemCount() {
        return this.cacBuocLam.size();
    }
}
