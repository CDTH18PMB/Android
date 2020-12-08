package com.hoanglam.congthucnauan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TaoCongThuc_Buoc_2_Activity extends AppCompatActivity {
    private TextView txt_Easy;
    private TextView txt_Normal;
    private TextView txt_Hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_cong_thuc__buoc_2_);

        txt_Easy = findViewById(R.id.txt_Easy);
        txt_Normal = findViewById(R.id.txt_Normal);
        txt_Hard = findViewById(R.id.txt_Hard);
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
            intent = new Intent(this,MonAnActivity.class);
            startActivity(intent);
        }else if(id == R.id.ic_Account){
            intent = new Intent(this,TaiKhoanActivity.class);
            startActivity(intent);
        }
    }

    public void openNextStep(View view) {
        Intent openTaoCongThuc = new Intent(this,TaoCongThuc_Buoc_3_Activity.class);
        startActivity(openTaoCongThuc);
    }

    public void doKho(View view) {
        TextView txt = (TextView) view;
        int easy = txt_Easy.getId(); int normal = txt_Normal.getId(); int hard = txt_Hard.getId();

        if(txt.getId() == easy) {
            doKho_Active(txt);
            doKho_Inactive(txt_Normal, txt_Hard);
        }else if(txt.getId() == normal){
            doKho_Active(txt);
            doKho_Inactive(txt_Easy, txt_Hard);
        }else if(txt.getId() == hard){
            doKho_Active(txt);
            doKho_Inactive(txt_Easy, txt_Normal);
        }
    }

    public void doKho_Active(TextView txt){
        txt.setBackground(getResources().getDrawable(R.drawable.dokho_active));
        txt.setTextColor(ContextCompat.getColor(this, R.color.white));
    }

    public void doKho_Inactive(TextView txt_1, TextView txt_2){
        txt_1.setBackground(getResources().getDrawable(R.drawable.border_radius));
        txt_1.setTextColor(ContextCompat.getColor(this, R.color.text_nomal));

        txt_2.setBackground(getResources().getDrawable(R.drawable.border_radius));
        txt_2.setTextColor(ContextCompat.getColor(this, R.color.text_nomal));
    }
}