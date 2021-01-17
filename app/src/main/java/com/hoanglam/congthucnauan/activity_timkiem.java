package com.hoanglam.congthucnauan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hoanglam.congthucnauan.Adapter.HienThiDanhMucAdapter;
import com.hoanglam.congthucnauan.Adapter.TimKiemMonAdapter;
import com.hoanglam.congthucnauan.Class.DanhMuc;
import com.hoanglam.congthucnauan.Class.MonAn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class activity_timkiem extends AppCompatActivity {
   private EditText search;
   private ImageView imgTimKiem;
   public LinkedList<DanhMuc> mDanhMuc = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private HienThiDanhMucAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timkiem);
        imgTimKiem = findViewById(R.id.image_Seach);
        imgTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity_timkiem.this,activity_tim_mon_an.class);
                intent.putExtra("TenLoai",search.getText().toString());
//                intent.putExtra("TenMon",search.getText().toString());
                startActivity(intent);
            }
        });

        mRecyclerView = findViewById(R.id.recyclerView_TimKiem);
        search=findViewById(R.id.edittext_seach);

        TimDanhMuc();
    }



    public void itemNavClick(View view) {
        ImageView item = (ImageView) view;
        int id = item.getId();
        Intent intent;
        if(id == R.id.ic_Home){
            intent = new Intent(this,activity_home.class);
            startActivity(intent);
        }else if(id == R.id.ic_MonAn){
            intent = new Intent(this,MonAnActivity.class);
            startActivity(intent);
        }else if(id == R.id.ic_Create){
            intent = new Intent(this,TaoCongThucActivity.class);
            startActivity(intent);
        }else if(id == R.id.ic_Account){
            intent = new Intent(this,TaiKhoanActivity.class);
            startActivity(intent);
        }
    }

    public void TimDanhMuc() {
       // mDanhMuc.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://10.0.2.2:8000/api/DanhMuc",
                response -> {
                    try {
                        Log.i("Log",response);
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String TenLoai = jsonObject.get("TenLoai").toString();
                            Integer TrangThai = Integer.parseInt(jsonObject.get("TrangThai").toString());
                            Integer MaLoai = Integer.parseInt(jsonObject.get("MaLoai").toString());
                            DanhMuc danhmuc = new DanhMuc(MaLoai,TenLoai,TrangThai);
                            mDanhMuc.addLast(danhmuc);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                    for (int i = 1; i <= 10; i++) {
//                        mWordList.addLast("Loại " + i);
//                    }
                    mAdapter = new HienThiDanhMucAdapter(getApplicationContext(), mDanhMuc,this);
                    mRecyclerView.setAdapter(mAdapter);
                    // Give the recycler view a default layout manager.
                    //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                    //hien ngang
                    // mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
                    //hien doc
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                },

                error -> {
                    Toast.makeText(getApplicationContext(), "ERR", Toast.LENGTH_SHORT).show();
                }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params = new HashMap<>();
//                params.put("danhmuc",search.getText().toString());
//                return params;
//            }
        };
        requestQueue.add(stringRequest);
    }


}