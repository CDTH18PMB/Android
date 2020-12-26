package com.hoanglam.congthucnauan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TaoCongThuc_Buoc_3_1_Activity extends AppCompatActivity {
    public static String CONTENT =  "CONTENT";
    public static String STEP = "STEP";
    public static final int REQUEST_IMAGE_CAPTURE = 111;
    public static final int REQUEST_IMAGE_GALLERY = 112;
    private Uri uriImage;
    private String encodeImageString;

    private EditText txt_MoTa;
    private ImageView img_Step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_cong_thuc__buoc_3_1_);

        txt_MoTa = findViewById(R.id.txt_MoTa);
        img_Step = findViewById(R.id.img_Step);
    }

    //======================================================================================================
    public interface PickerOptionListener {
        void onCameraSelected(); // Khi chọn là lấy ảnh từ camera
        void onGallerySelected(); // Khi chọn là lấy ảnh từ gallery
    }
    // Hàm này dùng để show ra dialog lựa chọn phương thức lấy ảnh cho user,trả về một callback là
    // PickerOptionListener
    public static void showImagePickerOptions(Context context, TaoCongThuc_Buoc_3_1_Activity.PickerOptionListener listener) {
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
                    uriImage = Uri.parse(savedImageURL);
                    InputStream imageStream = null;
                    try {
                        imageStream = getContentResolver().openInputStream(uriImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    photo = BitmapFactory.decodeStream(imageStream);
                    encodeBitmapImage(photo);
                    img_Step.setImageBitmap(photo);
                } else {
                    finish();
                    Intent intent = new Intent(this, TaoCongThuc_Buoc_3_Activity.class);
                    startActivity(intent);
                }
                break;
            case REQUEST_IMAGE_GALLERY:
                if(resultCode == RESULT_OK){
                    uriImage = data.getData();
                    InputStream imageStream = null;
                    try {
                        imageStream = getContentResolver().openInputStream(uriImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Bitmap photo = BitmapFactory.decodeStream(imageStream);
                    encodeBitmapImage(photo);
                    img_Step.setImageBitmap(photo);
                } else {
                    finish();
                    Intent intent = new Intent(this, TaoCongThuc_Buoc_3_Activity.class);
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
    //===========================================================================================================

    public void saveStep(View view) throws JSONException {
        if(txt_MoTa.getText().toString().equals("")){
            Toast.makeText(this, "Please enter your content of step", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent();

            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("encodeImage", encodeImageString);
            jsonObject.put("uriImage", uriImage);
            jsonObject.put("NoiDung",txt_MoTa.getText().toString());
            jsonArray.put(jsonObject);

            //String[] strSendData = {encodeImageString, uriImage.toString(), txt_MoTa.getText().toString()};
            intent.putExtra(CONTENT, jsonArray.toString());
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void backPreviousActivity(View view) {
        finish();
    }
}