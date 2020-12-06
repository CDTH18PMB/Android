package com.hoanglam.congthucnauan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TaoCongThuc_Success_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_cong_thuc__success_);
    }

    public void openHomeActivity(View view) {
        Intent openHome = new Intent(this,activity_home.class);
        startActivity(openHome);
    }
}