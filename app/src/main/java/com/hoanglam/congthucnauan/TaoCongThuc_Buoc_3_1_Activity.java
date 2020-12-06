package com.hoanglam.congthucnauan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class TaoCongThuc_Buoc_3_1_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_cong_thuc__buoc_3_1_);
    }

    public void backPreviousActivity(View view) {
        finish();
    }

    public void saveStep(View view) {
        backPreviousActivity(view);
    }
}