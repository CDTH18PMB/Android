package com.hoanglam.congthucnauan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hoanglam.congthucnauan.Adapter.TaiKhoanAdapter;
import com.hoanglam.congthucnauan.Class.MonAnTaiKhoan;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;

public class TaiKhoanActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private TaiKhoanAdapter mTaiKhoanAdapter;
    private LinkedList<MonAnTaiKhoan> mMonDaTao = new LinkedList<>();
    private TextView txtusername;
    private ImageView imganhdaidien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan);
        this.txtusername = findViewById(R.id.txt_tk_username);
        this.imganhdaidien = findViewById(R.id.img_tk_anhdaidien);
        mRecyclerView = findViewById(R.id.RecyclerView_activity_tai_khoan);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        NhanUsername();
    }
//    nhận Username
    void NhanUsername (){
//        Intent intent = getIntent();
//        String Username = intent.getStringExtra("chưa có");
        String Username = "MinhLuan";
        if(Username != null){
//            gọi api danh sách món đã tạo
            ShowMonDaTao(Username);
            detailtaikhoan(Username);
        }
    }
//    api danh sách món đã tạo
    public void ShowMonDaTao(String Username) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8000/api/show_mondatao/"+Username;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//                Log.d("TAG", "JsonArrayRequest onResponse: " + response.toString());
                Log.d("TAG", "onResponse: nhận dữ liệu món đã tạo");
                try {
                    for(int i= 0 ;i<response.length();i++){
                        JSONObject hit = response.getJSONObject(i);
                        String MaMon = hit.getString("MaMon");
                        String TenMon = hit.getString("TenMon");
                        String AnhDaiDien = hit.getString("AnhDaiDien");
                        String ThoiGianNau = hit.getString("ThoiGianNau");
                        int LuotXem = hit.getInt("LuotXem");
                        int LuotThich = hit.getInt("LuotThich");
                        mMonDaTao.add(new MonAnTaiKhoan(MaMon, TenMon, AnhDaiDien, ThoiGianNau,LuotXem, LuotThich));
                    }
                    LayoutAnimationController anim = AnimationUtils.loadLayoutAnimation(
                            TaiKhoanActivity.this,R.anim.layout_animation_down_to_up);
                    mRecyclerView.setLayoutAnimation(anim);
                    mTaiKhoanAdapter = new TaiKhoanAdapter(TaiKhoanActivity.this,mMonDaTao);
                    mRecyclerView.setAdapter(mTaiKhoanAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", "JsonArrayRequest onErrorResponse: " + error.getMessage());
                Toast.makeText(TaiKhoanActivity.this, "lỗi nhận dữ liệu món đã tạo", Toast.LENGTH_SHORT).show();
            }
        });
        mRequestQueue.add(jsonArrayRequest);
    }
//    api chi tiết tài khoản
    public void  detailtaikhoan(String Username){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8000/api/detail_taikhoan/"+Username;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject jsonObject = response.getJSONObject(0);
                    txtusername.setText(jsonObject.getString("username"));
                    Picasso.get()
                            .load("http://10.0.2.2:8000/images/Avatar/"+jsonObject.getString("username")+".jpg")
                            .resize(100, 100)
                            .placeholder(R.drawable.luan)
                            .error(R.drawable.android)
                            .into(imganhdaidien);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TaiKhoanActivity.this, "Lỗi nhận dữ liệu tài khoản", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    public void DaThich(View view) {
        Intent intent = new Intent(this, Tai_Khoan_Da_Thich.class);
        Log.d("TAG", "DaThich: Click");
        startActivity(intent);
    }

    public void itemNavClick(View view) {
        ImageView item = (ImageView) view;
        int id = item.getId();
        Intent intent;
        if(id == R.id.ic_Home) {
            intent = new Intent(this, activity_home.class);
            startActivity(intent);
        }else if(id == R.id.ic_Search){
            intent = new Intent(this,activity_timkiem.class);
            startActivity(intent);
        }else if(id == R.id.ic_MonAn){
            intent = new Intent(this,MonAnActivity.class);
            startActivity(intent);
        }else if(id == R.id.ic_Create){
            intent = new Intent(this,TaoCongThucActivity.class);
            startActivity(intent);
        }
    }

    public void openEditProfileActivity(View view) {
        Intent openEditProfile = new Intent(this, Tai_khoan_edit_profile.class);
        startActivity(openEditProfile);
    }

    public void openSettingActivity(View view) {
        Intent openSetting = new Intent(this, Tai_Khoan_Setting.class);
        startActivity(openSetting);
    }
}