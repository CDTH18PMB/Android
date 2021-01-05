package com.hoanglam.congthucnauan;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hoanglam.congthucnauan.Adapter.TaiKhoanDaThichAdapter;
import com.hoanglam.congthucnauan.Class.MonAnTaiKhoan;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class Tai_Khoan_Da_Thich extends AppCompatActivity {
    LinkedList<MonAnTaiKhoan> mMonDaThich = new LinkedList<>();
    RecyclerView mRecyclerView;
    TaiKhoanDaThichAdapter mTaiKhoanDaThichAdapter;
    private TextView txtusername;
    private ImageView imganhdaidien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan_da_thich);
        this.txtusername = findViewById(R.id.txt_tkdt_username);
        this.imganhdaidien = findViewById(R.id.img_tkdt_anhdaidien);
        mRecyclerView = findViewById(R.id.RecyclerView_activity_tai_khoan_da_thich);
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
            ShowMonDaThich(Username);
            detailtaikhoan(Username);
        }
    }
//    api danh sach món đã thích
    public void ShowMonDaThich(String Username){
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8000/api/show_mondathich/" + Username;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("TAG", "onResponse: Nhận dữ liệu món đã thích");
                try {
                    for(int i= 0;i<response.length();i++){
                        JSONObject jsonObject = response.getJSONObject(i);
                        String MaMon = jsonObject.getString("MaMon");
                        String TenMon = jsonObject.getString("TenMon");
                        String AnhDaiDien = jsonObject.getString("AnhDaiDien");
                        String ThoiGianNau = jsonObject.getString("ThoiGianNau");
                        int LuotXem = jsonObject.getInt("LuotXem");
                        int LuotThich = jsonObject.getInt("LuotThich");
                        String NguoiTao = jsonObject.getString("NguoiTao");
                        String Username = jsonObject.getString("Username");
                        mMonDaThich.add(new MonAnTaiKhoan(MaMon, TenMon, AnhDaiDien, ThoiGianNau,LuotXem, LuotThich,NguoiTao,Username));
                    }
                    LayoutAnimationController anim = AnimationUtils.loadLayoutAnimation(
                            Tai_Khoan_Da_Thich.this,R.anim.layout_animation_down_to_up);
                    mRecyclerView.setLayoutAnimation(anim);
                    mTaiKhoanDaThichAdapter = new TaiKhoanDaThichAdapter(Tai_Khoan_Da_Thich.this, mMonDaThich);
                    mRecyclerView.setAdapter(mTaiKhoanDaThichAdapter);
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", "onErrorResponse() returned: " + error.getMessage());
                Toast.makeText(Tai_Khoan_Da_Thich.this, "Lỗi nhận dữ liệu món đã thích", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Tai_Khoan_Da_Thich.this, "Lỗi nhận dữ liệu tài khoản", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }























    public void MonCuaTui(View view) {
        Intent intent = new Intent(this,TaiKhoanActivity.class);
        Log.d("TAG", "MonCuaTui: Click");
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
            intent = new Intent(this,activity_timkiem.class);
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