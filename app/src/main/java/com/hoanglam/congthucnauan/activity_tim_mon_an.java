package com.hoanglam.congthucnauan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.hoanglam.congthucnauan.Adapter.ListMonAnAdapter;
import com.hoanglam.congthucnauan.Adapter.TimKiemMonAdapter;
import com.hoanglam.congthucnauan.Class.MonAn;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class activity_tim_mon_an extends AppCompatActivity {
    private final LinkedList<MonAn> mMonAn = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private TimKiemMonAdapter mAdapter;
    private ImageView img_AnhDaiDien;
    private TextView txt_TenMon;
    private TextView txt_LuotThich;
    private TextView txt_LuotXem;
    private TextView txt_NguoiTao;
    private TextView txt_ThoiGianNau;
    private String tenLoai;
    private static String URL_MON_AN;
    private static String URL_Tim_Ten_MON_AN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_mon_an);
        mMonAn.clear();
        khoiTao();
        TimkiemMonAntheodanhmuc();
        TimkiemtheotenMonAn();
    }

    public void khoiTao() {
        mRecyclerView = findViewById(R.id.recyclerView_TimMon);
        img_AnhDaiDien = findViewById(R.id.img_AnhDaiDien_MonAn);
        txt_TenMon = findViewById(R.id.txt_TenMon);
        txt_LuotThich = findViewById(R.id.txt_LuotThich);
        txt_LuotXem = findViewById(R.id.txt_LuotXem);
        txt_NguoiTao = findViewById(R.id.txt_NguoiTao);
        txt_ThoiGianNau = findViewById(R.id.txt_ThoiGianNau);

        // lấy tenloai từ trang danh muc
          Intent intent = getIntent();
          tenLoai = intent.getStringExtra("TenLoai");
          URL_MON_AN = "http://10.0.2.2:8000/api/MonAn1?TenLoai=" + tenLoai;
          URL_Tim_Ten_MON_AN = "http://10.0.2.2:8000/api/MonAn2?TenMon=" + tenLoai;


    }

    public void TimkiemMonAntheodanhmuc() {
        Log.d("T", URL_MON_AN);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_MON_AN,
                response -> {
                    try {
                        Log.i("Log", response);
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String TenMon = jsonObject.get("TenMon").toString();
                            String MaMon = jsonObject.get("MaMon").toString();
                            String urlImage = "http://10.0.2.2:8000/images/" + jsonObject.getString("TenMon") + "/anhdaidien.jpg";
                            String ThoiGianNau = jsonObject.get("ThoiGianNau").toString();
                            String LuotXem = jsonObject.get("LuotXem").toString();
                            String LuotThich = jsonObject.get("LuotThich").toString();
                            String NguoiTao = jsonObject.get("NguoiTao").toString();
                            MonAn monan = new MonAn(MaMon, TenMon, urlImage, ThoiGianNau, LuotXem, LuotThich, NguoiTao);
                            if(KiemTraMonAn(TenMon))
                            mMonAn.addLast(monan);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    mAdapter = new TimKiemMonAdapter(getApplicationContext(), mMonAn);
                    mRecyclerView.setAdapter(mAdapter);
                    // Give the recycler view a default layout manager.
                    //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                    //hien ngang
                    //mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    //hien doc
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                },

                error -> {
                    Toast.makeText(getApplicationContext(), "ERR", Toast.LENGTH_SHORT).show();
                }) {


        };

        requestQueue.add(stringRequest);

    }

    public void TimkiemtheotenMonAn() {
        Log.d("T", URL_MON_AN);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_Tim_Ten_MON_AN,
                response -> {
                    try {
                        Log.i("Log", response);
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String TenMon = jsonObject.get("TenMon").toString();
                            String MaMon = jsonObject.get("MaMon").toString();
                            String urlImage = "http://10.0.2.2:8000/images/" + jsonObject.getString("TenMon") + "/anhdaidien.jpg";
                            String ThoiGianNau = jsonObject.get("ThoiGianNau").toString();
                            String LuotXem = jsonObject.get("LuotXem").toString();
                            String LuotThich = jsonObject.get("LuotThich").toString();
                            String NguoiTao = jsonObject.get("NguoiTao").toString();
                            MonAn monan = new MonAn(MaMon, TenMon, urlImage, ThoiGianNau, LuotXem, LuotThich, NguoiTao);
                            if(KiemTraMonAn(TenMon))
                            mMonAn.addLast(monan);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    mAdapter = new TimKiemMonAdapter(getApplicationContext(), mMonAn);
                    mRecyclerView.setAdapter(mAdapter);
                    // Give the recycler view a default layout manager.
                    //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                    //hien ngang
                    //mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    //hien doc
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                },

                error -> {
                    Toast.makeText(getApplicationContext(), "ERR", Toast.LENGTH_SHORT).show();
                }) {


        };

        requestQueue.add(stringRequest);

    }

    boolean KiemTraMonAn(String TenMon){
        for(int i = 0; i<mMonAn.size();i++){
            if(mMonAn.get(i).getTenMon().equals(TenMon))
                return false;
        }
        return true;
    }

}