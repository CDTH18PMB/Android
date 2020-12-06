package com.hoanglam.congthucnauan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openHomeActivity(View view) {
        Intent openHome = new Intent(this,activity_home.class);
        startActivity(openHome);
    }

    public void signUp(View view) {
        Intent openSignUp = new Intent(this, activity_dangky.class);
        startActivity(openSignUp);
    }
}