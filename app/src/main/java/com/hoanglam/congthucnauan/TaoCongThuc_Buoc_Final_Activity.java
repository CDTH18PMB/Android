package com.hoanglam.congthucnauan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TaoCongThuc_Buoc_Final_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_cong_thuc__buoc__final_);
    }

    public void openNextStep(View view) {
        Intent openTaoCongThuc = new Intent(this,TaoCongThuc_Success_Activity.class);
        startActivity(openTaoCongThuc);
    }

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
            intent = new Intent(this,activity_timkiem.class);
            startActivity(intent);
        }else if(id == R.id.ic_Account){
            intent = new Intent(this,TaiKhoanActivity.class);
            startActivity(intent);
        }
    }
}