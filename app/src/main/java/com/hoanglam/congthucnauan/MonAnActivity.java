package com.hoanglam.congthucnauan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hoanglam.congthucnauan.Adapter.HuongDanAdapter;
import com.hoanglam.congthucnauan.Adapter.MonAnAdapter;
import com.hoanglam.congthucnauan.Class.MonAn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class MonAnActivity extends AppCompatActivity {
    private final LinkedList<MonAn> mMonAn = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private MonAnAdapter mAdapter;

    private static String URL_MON_AN = "http://10.0.2.2:8000/api/MonAn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_an);
        khoiTao();
        dsMonAn();
    }

    public void khoiTao(){
        mRecyclerView = findViewById(R.id.recyclerview_MonAn);
        mAdapter = new MonAnAdapter(this, mMonAn);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void dsMonAn(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_MON_AN,
                response -> {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String maMon = jsonObject.getString("MaMon");
                            String tenMon = jsonObject.getString("TenMon");
                            String urlImage = "http://10.0.2.2:8000/images/" + tenMon + "/anhdaidien.jpg";
                            String luotXem = jsonObject.getString("LuotXem");
                            String luotThich = jsonObject.getString("LuotThich");
                            String nguoiTao = jsonObject.getString("NguoiTao");
                            String thoiGianNau = jsonObject.getString("ThoiGianNau");

                            loadMonAn(maMon, urlImage, tenMon, luotXem, luotThich, nguoiTao, thoiGianNau);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
                });
        requestQueue.add(stringRequest);
    }

    public void loadMonAn(String maMon, String urlImage, String tenMon, String luotXem, String luotThich, String nguoiTao, String thoiGianNau){
        int size = mMonAn.size();
        MonAn monAn = new MonAn(maMon, tenMon, urlImage, thoiGianNau, luotXem, luotThich, nguoiTao);

        mMonAn.addLast(monAn);
        mRecyclerView.getAdapter().notifyItemInserted(size);
        mRecyclerView.smoothScrollToPosition(size);
    }


    public void itemNavClick(View view) {
        ImageView item = (ImageView) view;
        int id = item.getId();
        Intent intent;
        if(id == R.id.ic_Search){
            intent = new Intent(this,activity_timkiem.class);
            startActivity(intent);
        }else if(id == R.id.ic_Home){
            intent = new Intent(this, activity_home.class);
            startActivity(intent);
        }else if(id == R.id.ic_Create){
            intent = new Intent(this,TaoCongThucActivity.class);
            startActivity(intent);
        }else if(id == R.id.ic_Account){
            intent = new Intent(this,TaiKhoanActivity.class);
            startActivity(intent);
        }
    }
    public void test(View view) {
        Intent intent = new Intent(this,ChiTietMonAnActivity.class);
        startActivity(intent);
    }
}