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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hoanglam.congthucnauan.Adapter.HuongDanAdapter;
import com.hoanglam.congthucnauan.Class.HuongDan;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ChiTietMonAnActivity extends AppCompatActivity {
    private final LinkedList<HuongDan> mHuongDan = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private HuongDanAdapter mAdapter;

    private ImageView img_AnhDaiDien;
    private TextView txt_TenMon;
    private TextView txt_LuotThich;
    private TextView txt_LuotXem;
    private TextView txt_NguoiTao;
    private TextView txt_MoTa;
    private TextView txt_DoKho;
    private TextView txt_ThoiGianNau;
    private TextView txt_NguyenLieu;

    private String maMon;

    private static String URL_MON_AN;
    private static String URL_HUONG_DAN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_mon_an);
        khoiTao();
        chiTietMonAn();
    }

    public void khoiTao() {
        mRecyclerView = findViewById(R.id.recyclerView_HuongDan);
        mAdapter = new HuongDanAdapter(this, mHuongDan);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        img_AnhDaiDien = findViewById(R.id.img_AnhDaiDien_MonAn);
        txt_TenMon = findViewById(R.id.txt_TenMon);
        txt_LuotThich = findViewById(R.id.txt_LuotThich);
        txt_LuotXem = findViewById(R.id.txt_LuotXem);
        txt_NguoiTao = findViewById(R.id.txt_NguoiTao);
        txt_MoTa = findViewById(R.id.txt_MoTaMonAn);
        txt_DoKho = findViewById(R.id.txt_DoKho);
        txt_ThoiGianNau = findViewById(R.id.txt_ThoiGianNau);
        txt_NguyenLieu = findViewById(R.id.txt_NguyenLieu);

        // lấy mã món ăn từ trang món ăn
        Intent intent = getIntent();
        maMon = intent.getStringExtra("MAMON");
        URL_MON_AN = "http://10.0.2.2:8000/api/MonAn/" + maMon;
        URL_HUONG_DAN = "http://10.0.2.2:8000/api/HuongDan/" + maMon;
    }

    public void chiTietMonAn() {
        Log.d("T", URL_MON_AN);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_MON_AN,
                response -> {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);

                        huongDanChiTiet(jsonObject.getString("TenMon"));

                        String urlImage = "http://10.0.2.2:8000/images/" + jsonObject.getString("TenMon") + "/anhdaidien.jpg";
                        Picasso.get()
                                .load(urlImage)
                                .fit()
                                .into(img_AnhDaiDien);

                        txt_TenMon.setText(jsonObject.getString("TenMon"));
                        txt_LuotThich.setText(jsonObject.getString("LuotThich"));
                        txt_LuotXem.setText(jsonObject.getString("LuotXem"));
                        txt_NguoiTao.setText(jsonObject.getString("NguoiTao"));
                        txt_MoTa.setText(jsonObject.getString("MoTa"));
                        txt_DoKho.setText(jsonObject.getString("DoKho"));
                        txt_ThoiGianNau.setText(jsonObject.getString("ThoiGianNau"));
                        txt_NguyenLieu.setText(jsonObject.getString("NguyenLieu"));

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

    public void huongDanChiTiet(String tenMon) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_HUONG_DAN,
                response -> {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String urlImage = "http://10.0.2.2:8000/images/" + tenMon + "/buoc" + (i + 1) + ".jpg";
                            String content = jsonObject.getString("CacBuocLam");
                            loadGuideRecyclerView(urlImage, content);
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

    public void loadGuideRecyclerView(String img, String content) {
        int size = mHuongDan.size();

        HuongDan huongDan = new HuongDan();
        huongDan.setBuocLam(content);
        huongDan.setURL_HinhAnh(img);

        mHuongDan.addLast(huongDan);

        mRecyclerView.getAdapter().notifyItemInserted(size);
        mRecyclerView.smoothScrollToPosition(size);
    }

    public void itemNavClick(View view) {
        ImageView item = (ImageView) view;
        int id = item.getId();
        Intent intent;
        if (id == R.id.ic_Search) {
            intent = new Intent(this, activity_timkiem.class);
            startActivity(intent);
        } else if (id == R.id.ic_Home) {
            intent = new Intent(this, activity_home.class);
            startActivity(intent);
        } else if (id == R.id.ic_Create) {
            intent = new Intent(this, TaoCongThucActivity.class);
            startActivity(intent);
        } else if (id == R.id.ic_Account) {
            intent = new Intent(this, TaiKhoanActivity.class);
            startActivity(intent);
        }
    }
}