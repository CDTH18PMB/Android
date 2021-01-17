package com.hoanglam.congthucnauan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hoanglam.congthucnauan.Adapter.HuongDanAdapter;
import com.hoanglam.congthucnauan.Adapter.ListMonAnAdapter;
import com.hoanglam.congthucnauan.Class.MonAn;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class activity_home extends AppCompatActivity {
    public LinkedList<MonAn> mMonAn = new LinkedList<>();
    public LinkedList<MonAn> mMonAn2 = new LinkedList<>();
    public LinkedList<MonAn> mMonAn3 = new LinkedList<>();
    private RecyclerView mRecyclerViewTopTrenDing;
    private RecyclerView mRecyclerViewGoiYMonAn;
    private RecyclerView mRecyclerViewGoiYMonAn2;
    private ListMonAnAdapter mAdapter;
    private Button btnRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mRecyclerViewTopTrenDing = findViewById(R.id.recyclerView_Toptrending);
        mRecyclerViewGoiYMonAn = findViewById(R.id.recyclerView_GoiYMon);
        mRecyclerViewGoiYMonAn2 = findViewById(R.id.recyclerView_GoiYMon2);
        btnRefresh=findViewById(R.id.btn_refrest);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadDanhMucNgauNhien(1);
                LoadDanhMucNgauNhien(2);
            }
        });
        HienThiMonAnTop();
        LoadDanhMucNgauNhien(1);
        LoadDanhMucNgauNhien(2);

    }

    public void HienThiMonAnTop() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://10.0.2.2:8000/api/MonAn3",
                response -> {
                    try {
                        mMonAn.clear();
                        Log.i("Log",response);
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String TenMon = jsonObject.get("TenMon").toString();
                            String MaMon = jsonObject.get("MaMon").toString();
                            String urlImage = "http://10.0.2.2:8000/images/"+  jsonObject.getString("TenMon") + "/anhdaidien.jpg";
                            String ThoiGianNau = jsonObject.get("ThoiGianNau").toString();
                            String LuotXem= jsonObject.get("LuotXem").toString();
                            String LuotThich = jsonObject.get("LuotThich").toString();
                            String NguoiTao= jsonObject.get("NguoiTao").toString();

                            MonAn monan = new MonAn(MaMon,TenMon,urlImage,ThoiGianNau,LuotXem,LuotThich,NguoiTao);

                            mMonAn.addLast(monan);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //    for (int i = 1; i <= 10; i++) {
                    //        mMonAn.addLast("Loại " + i);
                    //  }
                    mAdapter = new ListMonAnAdapter(this, mMonAn);
                    mRecyclerViewTopTrenDing.setAdapter(mAdapter);
                    // Give the recycler view a default layout manager.
                    //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                    //hien ngang
                    mRecyclerViewTopTrenDing.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
                    //hien doc
                    //mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                },

                error -> {
                    Toast.makeText(getApplicationContext(), "ERR", Toast.LENGTH_SHORT).show();
                }){
        };
        requestQueue.add(stringRequest);
    }

    public void HienThiMonAnGoiY(String TenLoai) {
        mMonAn2.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://10.0.2.2:8000/api/MonAn1?TenLoai=" + TenLoai,
                response -> {
                    try {
                        mMonAn2.clear();
                        Log.i("Log",response);
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String TenMon = jsonObject.get("TenMon").toString();
                            String MaMon = jsonObject.get("MaMon").toString();
                            String urlImage = "http://10.0.2.2:8000/images/"+  jsonObject.getString("TenMon") + "/anhdaidien.jpg";
                            String ThoiGianNau = jsonObject.get("ThoiGianNau").toString();
                            String LuotXem= jsonObject.get("LuotXem").toString();
                            String LuotThich = jsonObject.get("LuotThich").toString();
                            String NguoiTao= jsonObject.get("NguoiTao").toString();

                            MonAn monan = new MonAn(MaMon,TenMon,urlImage,ThoiGianNau,LuotXem,LuotThich,NguoiTao);

                            mMonAn2.addLast(monan);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //    for (int i = 1; i <= 10; i++) {
                    //        mMonAn.addLast("Loại " + i);
                    //  }
                    mAdapter = new ListMonAnAdapter(this, mMonAn2);
                    mRecyclerViewGoiYMonAn.setAdapter(mAdapter);
                    // Give the recycler view a default layout manager.
                    //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                    //hien ngang
                    mRecyclerViewGoiYMonAn.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
                    //hien doc

                    //mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                },

                error -> {
                    Toast.makeText(getApplicationContext(), "ERR", Toast.LENGTH_SHORT).show();
                }){
//
        };
        requestQueue.add(stringRequest);
    }

    public void LoadDanhMucNgauNhien(int value) {

        mMonAn2.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://10.0.2.2:8000/api/MonAn4",
                response -> {
                    try {
                        mMonAn2.clear();
                        Log.i("Log",response);
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String TenLoai = jsonObject.get("TenLoai").toString();//load dm xog goi hien thi mon an
                            if(value == 1)
                            HienThiMonAnGoiY(TenLoai);
                            else
                                HienThiMonAnGoiY2(TenLoai);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //    for (int i = 1; i <= 10; i++) {
                    //        mMonAn.addLast("Loại " + i);
                    //  }
                },
                error -> {
                    Toast.makeText(getApplicationContext(), "ERR", Toast.LENGTH_SHORT).show();
                }){
//
        };
        requestQueue.add(stringRequest);

    }

    public void HienThiMonAnGoiY2(String TenLoai) {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://10.0.2.2:8000/api/MonAn1?TenLoai=" + TenLoai,
                response -> {
                    try {
                        mMonAn3.clear();
                        Log.i("Log",response);
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String TenMon = jsonObject.get("TenMon").toString();
                            String MaMon = jsonObject.get("MaMon").toString();
                            String urlImage = "http://10.0.2.2:8000/images/"+  jsonObject.getString("TenMon") + "/anhdaidien.jpg";
                            String ThoiGianNau = jsonObject.get("ThoiGianNau").toString();
                            String LuotXem= jsonObject.get("LuotXem").toString();
                            String LuotThich = jsonObject.get("LuotThich").toString();
                            String NguoiTao= jsonObject.get("NguoiTao").toString();

                            MonAn monan = new MonAn(MaMon,TenMon,urlImage,ThoiGianNau,LuotXem,LuotThich,NguoiTao);

                            mMonAn3.addLast(monan);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //    for (int i = 1; i <= 10; i++) {
                    //        mMonAn.addLast("Loại " + i);
                    //  }
                    mAdapter = new ListMonAnAdapter(this, mMonAn3);
                    mRecyclerViewGoiYMonAn2.setAdapter(mAdapter);
                    // Give the recycler view a default layout manager.
                    //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                    //hien ngang
                    mRecyclerViewGoiYMonAn2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
                    //hien doc

                    //mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                },

                error -> {
                    Toast.makeText(getApplicationContext(), "ERR", Toast.LENGTH_SHORT).show();
                }){
//
        };
        requestQueue.add(stringRequest);
    }



    public void itemNavClick(View view) {
        ImageView item = (ImageView) view;
        int id = item.getId();
        Intent intent;
        if(id == R.id.ic_Search){
            intent = new Intent(this,activity_timkiem.class);
            startActivity(intent);
        }else if(id == R.id.ic_MonAn){
            intent = new Intent(this, MonAnActivity.class);
            startActivity(intent);
        }else if(id == R.id.ic_Create){
            intent = new Intent(this,TaoCongThucActivity.class);
            startActivity(intent);
        }else if(id == R.id.ic_Account){
            intent = new Intent(this,TaiKhoanActivity.class);
            startActivity(intent);
        }
    }
}