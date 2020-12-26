package com.hoanglam.congthucnauan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoanglam.congthucnauan.Class.ImageAndText;
import com.hoanglam.congthucnauan.R;

import java.util.ArrayList;

public class GridBaseAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context ctx;
    private ArrayList<ImageAndText> imageModelArrayList;
    private ImageView ivGallery;
    private TextView Name;
    private TextView Time;
    private TextView Heart;
    private TextView See;

    public GridBaseAdapter(Context ctx, ArrayList<ImageAndText> imageModelArrayList) {

        this.ctx = ctx;
        this.imageModelArrayList = imageModelArrayList;
    }

    @Override
    public int getCount() {
        return imageModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.grid_item_layout, parent, false);

        ivGallery = (ImageView) itemView.findViewById(R.id.imageView_anhdaidien);
        Name = (TextView) itemView.findViewById(R.id.textView_tenmon);
        Time = (TextView) itemView.findViewById(R.id.textView_time);
        See  = (TextView) itemView.findViewById(R.id.textView_see);
        Heart = (TextView) itemView.findViewById(R.id.textView_heart);

        ivGallery.setImageResource(imageModelArrayList.get(position).getImage_drawable());
        Name.setText(imageModelArrayList.get(position).getName());
        Time.setText(imageModelArrayList.get(position).getTime());
        See.setText(imageModelArrayList.get(position).getSee());
        Heart.setText(imageModelArrayList.get(position).getHeart());

        return itemView;
    }
}

