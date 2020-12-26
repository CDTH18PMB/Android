package com.hoanglam.congthucnauan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaoCongThuc_Buoc_Final_Activity extends AppCompatActivity {
    private static String url = "http://10.0.2.2:8000/api/Create_MonAn";
    private static String URL_DANH_MUC = "http://10.0.2.2:8000/api/DanhMuc";
    private static String URL_HUONG_DAN = "http://10.0.2.2:8000/api/Create_HuongDan";

    private Spinner spinner_LoaiMon;
    private EditText txt_MoTa;

    List<String> list = new ArrayList<>();
    List<String> lstDanhMuc = new ArrayList<>();

    JSONArray lstCacBuocLam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_cong_thuc__buoc__final_);
        khoiTao();
        khoiTao_Spinner();
        try {
            getIntentData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void khoiTao(){
        spinner_LoaiMon = findViewById(R.id.spinner_LoaiMon);
        txt_MoTa = findViewById(R.id.txt_MoTa);
    }

    public void addLstDanhMuc(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DANH_MUC,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("DanhMuc");

                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject jsob = jsonArray.getJSONObject(i);
                            lstDanhMuc.add(jsob.getString("TenLoai"));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Toast.makeText(this, "Error network", Toast.LENGTH_SHORT).show();

                    Log.d("ERR", error.toString());
                }
        );
        requestQueue.add(stringRequest);
    }

    public void khoiTao_Spinner(){
        addLstDanhMuc();
        lstDanhMuc.add("Chọn loại món");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lstDanhMuc);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_LoaiMon.setAdapter(adapter);
    }

    public void getIntentData() throws JSONException {
        Intent intent = getIntent();
        String[] arrData;

        // lấy dữ liệu từ Activity trước
        arrData = intent.getStringArrayExtra(TaoCongThucActivity.SUCCESS);

        // lấy mảng json chứa hình ảnh + nội dung bước làm
        lstCacBuocLam = new JSONArray(intent.getStringExtra("CAC_BUOC_LAM"));

        if(arrData.length == 0){
            Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
        } else {
            for(int i = 0; i < arrData.length; i++){
                list.add(arrData[i]);
            }
        }
    }

    public void openNextStep(View view) {
        insertMonAn();
    }

    // thêm công thức mới vào csdl
    public void insertMonAn(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    if(response.trim().equals("success")) { // nếu thành công thì thêm hướng dẫn
                        insertHuongDan();
                    } else {
                        Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
                    }

                },
                error -> {
                    Toast.makeText(TaoCongThuc_Buoc_Final_Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                String TenMon = list.get(0);
                String AnhDaiDien = "anhdaidien.jpg";
                String MoTa = txt_MoTa.getText().toString();
                String DoKho = list.get(2);
                String ThoiGianNau = list.get(3);
                String NguyenLieu = list.get(4);
                String LuotXem = "0";
                String LuotThich = "0";
                String NguoiTao = "HoangLam";
                String LoaiMon = String.valueOf(spinner_LoaiMon.getSelectedItemId());
                String TrangThai = "2";
                String Image = list.get(1);

                Map<String, String> params = new HashMap<>();
                params.put("TenMon", TenMon);
                params.put("AnhDaiDien", AnhDaiDien);
                params.put("MoTa", MoTa);
                params.put("DoKho", DoKho);
                params.put("ThoiGianNau", ThoiGianNau);
                params.put("NguyenLieu", NguyenLieu);
                params.put("LuotXem", LuotXem);
                params.put("LuotThich", LuotThich);
                params.put("NguoiTao", NguoiTao);
                params.put("LoaiMon", LoaiMon);
                params.put("TrangThai", TrangThai);
                params.put("Image", Image);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    // thêm hướng dẫn vào csdl
    public void insertHuongDan(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_HUONG_DAN,
                response -> {
                    if(response.trim().equals("success")) { // nếu thành công thì chuyển tới Activity thông báo
                        Intent nextStep = new Intent(this, TaoCongThuc_Success_Activity.class);
                        startActivity(nextStep);
                    } else {
                        Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
                    }

                },
                error -> { // lỗi API
                    Toast.makeText(TaoCongThuc_Buoc_Final_Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("HuongDan", lstCacBuocLam.toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    //======================= back/cancel/bottomNavigation ========================================
    public void backPreviousActivity(View view) {
        finish();
    }
    public void cancelCreate(View view) {
        Intent openHome = new Intent(this,activity_home.class);
        startActivity(openHome);
    }
    public void itemNavClick(View view) {
        ImageView item = (ImageView) view;
        int id = item.getId();
        Intent intent;
        if(id == R.id.ic_Home){
            intent = new Intent(this,activity_home.class);
            startActivity(intent);
        }else if(id == R.id.ic_Search){
            intent = new Intent(this,activity_timkiem.class);
            startActivity(intent);
        }else if(id == R.id.ic_MonAn){
            intent = new Intent(this,MonAnActivity.class);
            startActivity(intent);
        }else if(id == R.id.ic_Account){
            intent = new Intent(this,TaiKhoanActivity.class);
            startActivity(intent);
        }
    }
}