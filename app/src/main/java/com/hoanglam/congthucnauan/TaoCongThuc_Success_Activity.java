package com.hoanglam.congthucnauan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TaoCongThuc_Success_Activity extends AppCompatActivity {

    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_cong_thuc__success_);
    }

    public void openHomeActivity(View view) {
        Intent openHome = new Intent(this,activity_home.class);
        startActivity(openHome);
    }

    public void getIntentData(){
        Intent intent = getIntent();
        String[] arr;
        arr = intent.getStringArrayExtra(TaoCongThucActivity.SUCCESS);
        if(arr.length == 0){
            Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
        } else {
            for(int i = 0; i < arr.length; i++){
                list.add(arr[i]);
            }
        }
    }
}