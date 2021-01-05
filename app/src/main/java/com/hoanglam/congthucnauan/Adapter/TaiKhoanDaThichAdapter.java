package com.hoanglam.congthucnauan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hoanglam.congthucnauan.ChiTietMonAnActivity;
import com.hoanglam.congthucnauan.Class.MonAnTaiKhoan;
import com.hoanglam.congthucnauan.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.zip.Inflater;

public class TaiKhoanDaThichAdapter extends RecyclerView.Adapter<TaiKhoanDaThichAdapter.ViewHD>{
    final LinkedList<MonAnTaiKhoan> mMonDaThich;
    Context mContext;

    public TaiKhoanDaThichAdapter(Context context, LinkedList<MonAnTaiKhoan> mMonDaThich) {
        this.mMonDaThich = mMonDaThich;
        mContext = context;
    }
    // tạo ra đối tượng ViewHolder, trong nó chứa View hiện thị dữ liệu onCreateViewHolder()
    @NonNull
    @Override
    public ViewHD onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View iTemView = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_tai_khoan_da_thich,parent,false);
        return new ViewHD(iTemView,this);
    }
    //chuyển dữ liệu phần tử vào ViewHolder onBindViewHolder()
    @Override
    public void onBindViewHolder(@NonNull ViewHD holder, int position) {
        MonAnTaiKhoan MonAn = this.mMonDaThich.get(position);
        String imageUrl = MonAn.getTenMon();
        //lấy ra id và lượt xem tương ứng với view đó
        holder.ID = MonAn.getMaMon();
        holder.LUOTXEM = MonAn.getLuotXem() + 1;
        holder.LUOTTHICH = MonAn.getLuotThich() - 1;
        holder.USERNAME = MonAn.getUsername();
        //thêm dữ liệu vào view
        holder.txtTenMon.setText(MonAn.getTenMon());
        Picasso.get()
                .load("http://10.0.2.2:8000/images/" + imageUrl + "/anhdaidien.jpg")
                .resize(100, 100)
                .placeholder(R.drawable.luan)
                .error(R.drawable.android)
                .into(holder.imgAnhDaiDien);
        holder.txtThoiGianNau.setText(MonAn.getThoiGianNau());
        holder.txtLuotXem.setText(String.valueOf(MonAn.getLuotXem()));
        holder.txtLuotThich.setText(String.valueOf(MonAn.getLuotThich()));
        holder.txtNguoiTao.setText(MonAn.getNguoiTao());
    }
    //cho biết số phần tử của dữ liệu getItemCount()
    @Override
    public int getItemCount() {
        return this.mMonDaThich.size();
    }

    public class ViewHD extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtTenMon;
        public ImageView imgAnhDaiDien;
        public TextView txtThoiGianNau;
        public TextView txtLuotXem;
        public TextView txtLuotThich;
        public TextView txtNguoiTao;
        public ImageButton btnThich;
        public String ID;
        public int LUOTXEM;
        public int LUOTTHICH;
        public String USERNAME;

        final TaiKhoanDaThichAdapter mTaiKhoanDaThichAdapter;
        public ViewHD(@NonNull View itemView, TaiKhoanDaThichAdapter taiKhoanDaThichAdapter) {
            super(itemView);
            this.mTaiKhoanDaThichAdapter = taiKhoanDaThichAdapter;
            itemView.setOnClickListener(this);
            this.txtTenMon = itemView.findViewById(R.id.txt_tkdt_tenmonan);
            this.imgAnhDaiDien = itemView.findViewById(R.id.img_tkdt_anhdaidien);
            this.txtThoiGianNau = itemView.findViewById(R.id.txt_tkdt_thoigian);
            this.txtLuotXem = itemView.findViewById(R.id.txt_tkdt_luotxem);
            this.txtLuotThich = itemView.findViewById(R.id.txt_tkdt_luotthich);
            this.txtNguoiTao = itemView.findViewById(R.id.txt_tkdt_tennguoitao);
            this.btnThich = itemView.findViewById(R.id.btn_tkdt_thich);

            btnThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    mMonDaThich.remove(position);
                    mTaiKhoanDaThichAdapter.notifyItemRemoved(position);
                    mTaiKhoanDaThichAdapter.notifyDataSetChanged();
//                    gọi api Xoá món đã thích
                    unlikemondathich();
                    Toast.makeText(mContext, "Đã bỏ thích món "+ txtTenMon.getText(), Toast.LENGTH_SHORT).show();
                }
            });

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, ChiTietMonAnActivity.class);
            intent.putExtra("MAMON",ID);
            Log.d("TAG", "onClick: đã gửi ID món ăn đến trang chi tiết món ăn");
            mContext.startActivity(intent);
//          gọi api tăng lượt xem
            PlusLuotXem();
        }
//        api bỏ thích
        public void unlikemondathich() {
            RequestQueue requestQueue = Volley.newRequestQueue(mContext);
            String url = "http://10.0.2.2:8000/api/unlike_mondathich/" + ID;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("TAG", "onResponse: bỏ thích thành công");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("TAG", "onErrorResponse: thất bại khi bỏ thích");
                    Log.d("TAG", "onErrorResponse() returned: " + error);
                    Toast.makeText(mContext, "lỗi bỏ thích", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("Username", String.valueOf(USERNAME));
                    params.put("luotthich", String.valueOf(LUOTTHICH));
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }

//        api tăng lượt xem
        public void PlusLuotXem() {
            RequestQueue mRequestQueue = Volley.newRequestQueue(mContext);
            String url = "http://10.0.2.2:8000/api/plus_luotxem/" + ID;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("TAG", "onResponse: tăng lượt xem thành công");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("TAG", "onErrorResponse: thất bại khi tăng lượt xem");
                    Log.d("TAG", "onErrorResponse() returned: " + error);
                    Toast.makeText(mContext, "lỗi tăng lượt xem", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("LuotXem", String.valueOf(LUOTXEM));
                    return params;
                }
            };
            mRequestQueue.add(stringRequest);
        }//end PlusLuotXem
    }//end view holder
}
