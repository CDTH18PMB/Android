package com.hoanglam.congthucnauan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TaoCongThucActivity extends AppCompatActivity {
    public static int REQUEST_CODE = 1;
    public static int PICK_IMAGE = 2;
    public static String SUCCESS = "success";

    // Key phương thức chọn ảnh là từ camera hay gallery

    public static final int REQUEST_IMAGE_CAPTURE = 111;
    public static final int REQUEST_IMAGE_GALLERY = 112;

    private EditText txt_TenMon;
    private ImageView img_AnhDaiDien;

    String encodeImageString;

    List<String>  list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_cong_thuc);
        khoiTao();
    }

    public void khoiTao(){
        txt_TenMon = findViewById(R.id.txt_TenMon_Create);
        img_AnhDaiDien = findViewById(R.id.img_AnhDaiDien);
    }

    public interface PickerOptionListener {
        void onCameraSelected(); // Khi chọn là lấy ảnh từ camera
        void onGallerySelected(); // Khi chọn là lấy ảnh từ gallery
    }
    // Hàm này dùng để show ra dialog lựa chọn phương thức lấy ảnh cho user,trả về một callback là
    // PickerOptionListener
    public static void showImagePickerOptions(Context context, PickerOptionListener listener) {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.lbl_set_profile_photo));

        // Add chose item to dialog
        String[] animals = {context.getString(R.string.lbl_take_camera_picture), context.getString(R.string.lbl_choose_from_gallery)};
        builder.setItems(animals, (dialog, which) -> {
            switch (which) {
                case 0:
                    listener.onCameraSelected();
                    break;
                case 1:
                    listener.onGallerySelected();
                    break;
            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Hàm lấy ảnh từ camera
    private void takeCameraImage() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }

    // Lấy ảnh từ thư viện
    private void takeGalleryImage(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_IMAGE_GALLERY);

    }

    // btn chọn hình
    public void chooseImage(View view) {
        showImagePickerOptions(this, new PickerOptionListener() {
            @Override
            public void onCameraSelected() {
                takeCameraImage();
            }

            @Override
            public void onGallerySelected() {
                takeGalleryImage();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case REQUEST_IMAGE_CAPTURE:
                if(resultCode == RESULT_OK){
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    String savedImageURL = MediaStore.Images.Media.insertImage(
                            getContentResolver(),
                            photo,
                            "image",
                            "image"
                    );
                    Uri selectedImage = Uri.parse(savedImageURL);
                    InputStream imageStream = null;
                    try {
                        imageStream = getContentResolver().openInputStream(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    photo = BitmapFactory.decodeStream(imageStream);
                    encodeBitmapImage(photo);
                    img_AnhDaiDien.setImageBitmap(photo);
                } else {
                    finish();
                    Intent intent = new Intent(this, TaoCongThucActivity.class);
                    startActivity(intent);
                }
                break;
            case REQUEST_IMAGE_GALLERY:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    InputStream imageStream = null;
                    try {
                        imageStream = getContentResolver().openInputStream(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Bitmap photo = BitmapFactory.decodeStream(imageStream);
                    img_AnhDaiDien.setImageBitmap(photo);
                    encodeBitmapImage(photo);
                } else {
                    finish();
                    Intent intent = new Intent(this, TaoCongThucActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    public void encodeBitmapImage(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytesOfImage = byteArrayOutputStream.toByteArray();
        encodeImageString = Base64.encodeToString(bytesOfImage, Base64.DEFAULT);
    }
    //======================================================================================
    public void openNextStep(View view) {
        Intent nextStep = new Intent(this,TaoCongThuc_Buoc_2_Activity.class);

        if(!list.contains(txt_TenMon.getText().toString())) {
            list.add(txt_TenMon.getText().toString());
            list.add(encodeImageString);
        }
        String[] strSendData = list.toArray(new String[list.size()]);
        nextStep.putExtra(SUCCESS, strSendData);
        startActivity(nextStep);
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