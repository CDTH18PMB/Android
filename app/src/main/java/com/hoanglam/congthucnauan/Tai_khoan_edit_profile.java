package com.hoanglam.congthucnauan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Tai_khoan_edit_profile extends AppCompatActivity {
public TextView txtten;
public TextView txtsdt;
public TextView txtemail;
public ImageView imganhdaidien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan_edit_profile);
        this.txtten = findViewById(R.id.txt_tkep_ten);
        this.txtsdt = findViewById(R.id.txt_tkep_sdt);
        this.txtemail = findViewById(R.id.txt_tkep_email);
        this.imganhdaidien = findViewById(R.id.img_tkep_anhdadien);
        NhanUsername();
    }
//    nhận Username
    void NhanUsername (){
//        Intent intent = getIntent();
//        String Username = intent.getStringExtra("chưa có");
        String Username = "MinhLuan";
        if(Username != null){
//            gọi api danh sách món đã tạo
            detailtaikhoan(Username);
        }
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
                    txtten.setText(jsonObject.getString("HoTen"));
                    txtsdt.setText(jsonObject.getString("SDT"));
                    txtemail.setText(jsonObject.getString("Email"));
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
                Toast.makeText(Tai_khoan_edit_profile.this, "Lỗi nhận dữ liệu tài khoản", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    public void openLogInActivity(View view) {
        Intent openLogIn = new Intent(this,MainActivity.class);
        startActivity(openLogIn);
    }

    public void backPreviousActivity(View view) {
        finish();
    }
}