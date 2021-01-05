package com.hoanglam.congthucnauan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LongDef;
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
import com.hoanglam.congthucnauan.Class.Account;
import com.hoanglam.congthucnauan.Class.MonAnTaiKhoan;
import com.hoanglam.congthucnauan.MainActivity;
import com.hoanglam.congthucnauan.R;
import com.hoanglam.congthucnauan.TaoCongThucActivity;
import com.hoanglam.congthucnauan.activity_home;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class TaiKhoanAdapter extends RecyclerView.Adapter<TaiKhoanAdapter.ViewHolderTK> {
    private Context mContext;
    private LinkedList<MonAnTaiKhoan> mMonDaTao;

    public TaiKhoanAdapter(Context context, LinkedList<MonAnTaiKhoan> mMonDaTao) {
        this.mContext = context;
        this.mMonDaTao = mMonDaTao;
    }

    //tạo viewholder, trong đó chứa view hiện dữ liệu
    @NonNull
    @Override
    public ViewHolderTK onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_tai_khoan, parent, false);
        return new ViewHolderTK(view, this);
    }

    //chuyển dữ liệu vào viewholder
    @Override
    public void onBindViewHolder(@NonNull ViewHolderTK holder, int position) {
        MonAnTaiKhoan MonAn = this.mMonDaTao.get(position);
        String imageUrl = MonAn.getTenMon();
        //lấy ra id và lượt xem tương ứng với view đó
        holder.ID = MonAn.getMaMon();
        holder.LUOTXEM = MonAn.getLuotXem() + 1;
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
    }

    //số lượng phần tử
    @Override
    public int getItemCount() {
        return mMonDaTao.size();
    }

    public class ViewHolderTK extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtTenMon;
        public ImageView imgAnhDaiDien;
        public TextView txtThoiGianNau;
        public TextView txtLuotXem;
        public TextView txtLuotThich;
        public ImageButton btnedit;
        public ImageButton btndelete;
        public String ID;
        public int LUOTXEM;
        public final TaiKhoanAdapter mTaiKhoanAdapter;

        public ViewHolderTK(@NonNull View itemView, TaiKhoanAdapter mTaiKhoanAdapter) {
            super(itemView);
            this.mTaiKhoanAdapter = mTaiKhoanAdapter;
            itemView.setOnClickListener(this);
            this.txtTenMon = itemView.findViewById(R.id.txt_tk_tenmonan);
            this.imgAnhDaiDien = itemView.findViewById(R.id.img_tk_anhdaidien);
            this.txtThoiGianNau = itemView.findViewById(R.id.txt_tk_thoigian);
            this.txtLuotXem = itemView.findViewById(R.id.txt_tk_luotxem);
            this.txtLuotThich = itemView.findViewById(R.id.txt_tk_luotthich);
            this.btnedit = itemView.findViewById(R.id.btn_tk_edit);
            this.btndelete = itemView.findViewById(R.id.btn_tk_delete);

            btnedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, TaoCongThucActivity.class);
                    Log.d("TAG", "onClick: đã gửi ID món ăn đến trang tạo công thức");
                    intent.putExtra("MAMON", ID);
                    mContext.startActivity(intent);
                }
            });
            btndelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    Toast.makeText(mContext, "Đã xoá món " + txtTenMon.getText(), Toast.LENGTH_SHORT).show();
                    mMonDaTao.remove(position);
                    mTaiKhoanAdapter.notifyItemRemoved(position);
                    mTaiKhoanAdapter.notifyDataSetChanged();
//                    gọi api xoá món đã tạo
                    DeleteMonDaTao();
                }
            });
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, ChiTietMonAnActivity.class);
            Log.d("TAG", "onClick: đã gửi ID món ăn đến trang chi tiết");
            intent.putExtra("MAMON", ID);
            mContext.startActivity(intent);
//            gọi api tăng lượt xem
            PlusLuotXem();
        }
//        api xoá món đã tạo
        public  void DeleteMonDaTao(){
            RequestQueue mRequestQueue = Volley.newRequestQueue(mContext);
            String url ="http://10.0.2.2:8000/api/delete_mondatao/" + ID;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("TAG", "onResponse: Xoá món đã tạo thành công");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("TAG", "onErrorResponse: thất bại khi xoá món đã tạo");
                    Log.d("TAG", "onErrorResponse() returned: " + error);
                    Toast.makeText(mContext, "lỗi xoá món đã tạo", Toast.LENGTH_SHORT).show();
                }
            });
            mRequestQueue.add(stringRequest);
        }
//      api tăng lượt xem
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
    }//end holder
}//end adapter
