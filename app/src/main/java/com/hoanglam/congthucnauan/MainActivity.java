package com.hoanglam.congthucnauan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hoanglam.congthucnauan.Class.Account;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{
    private static String url = "http://10.0.2.2:8000/api/CheckLogin";
    private EditText txt_Username;
    private EditText txt_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        khoiTao();
    }

    public void khoiTao(){
        txt_Username = findViewById(R.id.txt_Username);
        txt_Password = findViewById(R.id.txt_Password);
    }

    public void signUp(View view) {
        Intent openSignUp = new Intent(this, activity_dangky.class);
        startActivity(openSignUp);
    }

    public void btnDangNhapClick(View view) {
        //checkLogin();
        Intent openHome = new Intent(this,activity_home.class);

        startActivity(openHome);
    }

    public void checkLogin(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    try {
                        if (response.trim().equals("false")){
                            Toast.makeText(MainActivity.this, "Tên đăng nhập hoặc Mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                        } else {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("TaiKhoan");
                            JSONObject account_json = jsonArray.getJSONObject(0);
                            
                            Account account = new Account();
                            account.setUsername(account_json.getString("username"));
                            account.setAnhDaiDien(account_json.getString("AnhDaiDien"));
                            account.setPassword(account_json.getString("password"));
                            account.setHoTen(account_json.getString("HoTen"));
                            account.setSDT(account_json.getString("SDT"));
                            account.setEmail(account_json.getString("Email"));
                            account.setLoaiTK(account_json.getString("LoaiTK"));

                            Intent openHome = new Intent(this,activity_home.class);
                            openHome.putExtra("KEY", account);
                            startActivity(openHome);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Toast.makeText(MainActivity.this, "Error network", Toast.LENGTH_SHORT).show();

                    Log.d("ERR", error.toString());
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", txt_Username.getText().toString().trim());
                params.put("password", txt_Password.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


}