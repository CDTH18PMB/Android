package com.hoanglam.congthucnauan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Tai_Khoan_Da_Thich extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan_da_thich);
    }

    public void MonCuaTui(View view) {
        Intent intent = new Intent(this,TaiKhoanActivity.class);
        Log.d("TAG","click Món Của tui");
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