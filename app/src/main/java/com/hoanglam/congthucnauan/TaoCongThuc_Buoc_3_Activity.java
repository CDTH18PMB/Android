package com.hoanglam.congthucnauan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.LinkedList;

public class TaoCongThuc_Buoc_3_Activity extends AppCompatActivity {
    private final LinkedList<CacBuocLam> cacBuocLam = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private CacBuocLamAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_cong_thuc__buoc_3_);

        this.mRecyclerView = findViewById(R.id.recyclerView_CacBuocLam);
        this.mAdapter = new CacBuocLamAdapter(this,cacBuocLam);
        this.mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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

    public void openNextStep(View view) {
        Intent openTaoCongThuc = new Intent(this,TaoCongThuc_Buoc_Final_Activity.class);
        startActivity(openTaoCongThuc);
    }

    public void addStep(View view) {
        //Intent addStep = new Intent(this,TaoCongThuc_Buoc_3_1_Activity.class);
        //startActivity(addStep);
        int size = cacBuocLam.size();

        CacBuocLam mCacBuocLam = new CacBuocLam();
        mCacBuocLam.setImage("Image");
        mCacBuocLam.setStep("Buoc 1");
        mCacBuocLam.setContent("Content");

        cacBuocLam.addLast(mCacBuocLam);

        mRecyclerView.getAdapter().notifyItemInserted(size);
        mRecyclerView.smoothScrollToPosition(size);
    }
}