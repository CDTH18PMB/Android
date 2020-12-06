package com.hoanglam.congthucnauan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.LinkedList;

public class TaiKhoanActivity extends AppCompatActivity {
    LinkedList<String> List;
    RecyclerView mRecyclerView;
    TaiKhoanAdapter mTaiKhoanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan);
        //tạo một mảng
        this.List = new LinkedList<String>();
        for(int i = 1 ; i< 10;i++){
            List.addLast("Món số: "+ i);
        }
        mRecyclerView = findViewById(R.id.RecyclerView_activity_tai_khoan);
        mTaiKhoanAdapter = new TaiKhoanAdapter(this, this.List);
        mRecyclerView.setAdapter(mTaiKhoanAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    public void DaThich(View view) {
        Intent intent = new Intent(this, Tai_Khoan_Da_Thich.class);
        Log.d("TAG","CLick Món Thích");
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