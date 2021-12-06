package com.example.dungtnph08210_mob403_assignment;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.util.UUID;

public class AddFoodScreen extends AppCompatActivity {

    public static final String TAG = AddFoodScreen.class.getName();
    public static final String UPLOAD_URL = "https://nguyentiendungph08210.000webhostapp.com/MOB403/api_insert_restaurant.php";
    private static final int MY_REQUETS_CODE = 10;
    EditText edname, edprice;
    Button btnselect, btnsent;
    ImageView imgvImage, imgvImage2;
    private Uri muri;
    ProgressDialog progressDialog;

    ActivityResultLauncher<Intent> mActi = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e(TAG, "onActivityResult");
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }
                        Uri uri = data.getData();
                        muri = uri;
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            imgvImage.setImageBitmap(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_screen);
        inits();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait......");

        btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRequestPermission();
            }
        });
        btnsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callapi();
            }
        });
        imgvImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddFoodScreen.this, HomeScreen.class));
            }
        });

    }

    private String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images.Media._ID + "=?", new String[]{document_id}, null);
        cursor.moveToFirst();


        @SuppressLint("Range") String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }

    private void callapi() {
        progressDialog.show();
        String name = edname.getText().toString().trim();
        String price = edprice.getText().toString().trim();
        String path = getPath(muri);
        try {

            String uploadId = UUID.randomUUID().toString();
            new MultipartUploadRequest(this, uploadId, UPLOAD_URL)
                    .addFileToUpload(path, "image")
                    .addParameter("name", name)
                    .addParameter("price", price)
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(3)
                    .startUpload();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                    Toast.makeText(AddFoodScreen.this, "Insert Thành Công Vào SQL", Toast.LENGTH_SHORT).show();

                }
            }, 3000);
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private void onClickRequestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openGralery();
            return;
        }
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGralery();
        } else {
            String[] per = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(per, MY_REQUETS_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_REQUETS_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGralery();
            }
        }

    }

    private void openGralery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActi.launch(Intent.createChooser(intent, "select picture"));
    }

    private void inits() {
        edname = findViewById(R.id.edName);
        edprice = findViewById(R.id.edprice);
        btnselect = findViewById(R.id.btnselect);
        btnsent = findViewById(R.id.btnsent);
        imgvImage = findViewById(R.id.imgvImage);
        imgvImage2 = findViewById(R.id.imgback);

    }
}