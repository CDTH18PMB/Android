package com.hoanglam.congthucnauan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_dangky extends AppCompatActivity {
private TextView txt_chuyentrangDN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        txt_chuyentrangDN=findViewById(R.id.txt_dangky);
    }

    public void SignIn(View view) {
        Intent openSignIn = new Intent(this,MainActivity.class);
        startActivity(openSignIn);
    }
}