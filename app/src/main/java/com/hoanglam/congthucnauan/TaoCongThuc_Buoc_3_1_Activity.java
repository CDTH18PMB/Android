package com.hoanglam.congthucnauan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TaoCongThuc_Buoc_3_1_Activity extends AppCompatActivity {
    public static String CONTENT =  "CONTENT";
    public static String STEP = "STEP";

    private EditText txt_Content_Step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_cong_thuc__buoc_3_1_);

        txt_Content_Step = findViewById(R.id.txt_Detail_Step);
    }

    public void backPreviousActivity(View view) {
        finish();
    }

    public void saveStep(View view) {
        if(txt_Content_Step.getText().toString().equals("")){
            Toast.makeText(this, "Please enter your content of step", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent();
            intent.putExtra(CONTENT, txt_Content_Step.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}