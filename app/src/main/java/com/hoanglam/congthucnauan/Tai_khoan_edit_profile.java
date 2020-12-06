package com.hoanglam.congthucnauan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Tai_khoan_edit_profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan_edit_profile);
    }

    public void openLogInActivity(View view) {
        Intent openLogIn = new Intent(this,MainActivity.class);
        startActivity(openLogIn);
    }

    public void backPreviousActivity(View view) {
        finish();
    }
}