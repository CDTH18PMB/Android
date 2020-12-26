package com.hoanglam.congthucnauan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hoanglam.congthucnauan.Adapter.CacBuocLamAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TaoCongThuc_Buoc_3_Activity extends AppCompatActivity {
    private final LinkedList<CacBuocLam> cacBuocLam = new LinkedList<>();
    private static String SUCCESS = "success";
    public static int ADDSTEP_CODE = 1;

    private RecyclerView mRecyclerView;
    private CacBuocLamAdapter mAdapter;
    private int count = 0; // số bước làm

    List<String> list = new ArrayList<>();
    List<String> encodeImageList = new ArrayList<>();
    List<String> ContentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_cong_thuc__buoc_3_);
        khoiTao();
        getIntentData();
    }

    public void khoiTao(){
        this.mRecyclerView = findViewById(R.id.recyclerView_CacBuocLam);
        this.mAdapter = new CacBuocLamAdapter(this,cacBuocLam);
        this.mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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


    public void openNextStep(View view) throws JSONException {
        Intent nextStep = new Intent(this,TaoCongThuc_Buoc_Final_Activity.class);

        String[] strSendData = list.toArray(new String[list.size()]);
        nextStep.putExtra(TaoCongThucActivity.SUCCESS, strSendData);

//        List<String> arrBuocLam = new ArrayList<>();
//        for(int i = 0; i < count; i++){
//            arrBuocLam.add(encodeImageList.get(i)); // truyền encodeImage
//            arrBuocLam.add(ContentList.get(i)); // truyền nội dung bước
//        }
//        String[] strCacBuocLam = arrBuocLam.toArray(new String[cacBuocLam.size()]);


        //nextStep.putExtra("CAC_BUOC_LAM", strCacBuocLam);
        JSONArray jsonArray = new JSONArray();
        for(int i = 0; i < count; i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("encodeImage", encodeImageList.get(i));
            jsonObject.put("contentStep", ContentList.get(i));
            jsonArray.put(jsonObject);
        }
        nextStep.putExtra("CAC_BUOC_LAM", jsonArray.toString());
        startActivity(nextStep);
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
                //String[] arrData = data.getStringArrayExtra(TaoCongThuc_Buoc_3_1_Activity.CONTENT);
                try {
                    JSONArray jsonArray = new JSONArray(data.getStringExtra(TaoCongThuc_Buoc_3_1_Activity.CONTENT));
                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                    encodeImageList.add(jsonObject.getString("encodeImage")); // láy encode
                    ContentList.add(jsonObject.getString("NoiDung")); // lấy nội dung bước

                    String img = jsonObject.getString("uriImage");
                    String step = "Bước " + this.count;
                    String content = jsonObject.getString("NoiDung");
                    addStepRecyclerView(img, step, content);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // thêm bước mới vào recyclerView
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


    //======================= back/cancel/bottomNavigation ========================================
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
}