package com.hoanglam.congthucnauan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MonAnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_an);
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