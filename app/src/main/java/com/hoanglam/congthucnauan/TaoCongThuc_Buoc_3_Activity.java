package com.hoanglam.congthucnauan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.LinkedList;

public class TaoCongThuc_Buoc_3_Activity extends AppCompatActivity {
    private final LinkedList<CacBuocLam> cacBuocLam = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private CacBuocLamAdapter mAdapter;

    private int count = 0;

    public static int ADDSTEP_CODE = 1;

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
            intent = new Intent(this,MonAnActivity.class);
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
        Intent addStep = new Intent(this,TaoCongThuc_Buoc_3_1_Activity.class);
        startActivityForResult(addStep,ADDSTEP_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADDSTEP_CODE){
            if(resultCode == RESULT_OK){
                this.count++;
                String img = "Hinh anh";
                String step = "Bước " + this.count;
                String content = data.getStringExtra(TaoCongThuc_Buoc_3_1_Activity.CONTENT);
                addStepRecyclerView(img, step, content);
            }
        }
    }

    public void addStepRecyclerView(String img, String step, String content){
        int size = cacBuocLam.size();
        CacBuocLam mCacBuocLam = new CacBuocLam();
        mCacBuocLam.setImage(img);
        mCacBuocLam.setStep(step);
        mCacBuocLam.setContent(content);

        cacBuocLam.addLast(mCacBuocLam);

        mRecyclerView.getAdapter().notifyItemInserted(size);
        mRecyclerView.smoothScrollToPosition(size);
    }
}