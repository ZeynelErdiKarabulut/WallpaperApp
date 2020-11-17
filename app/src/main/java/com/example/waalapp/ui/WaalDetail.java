package com.example.waalapp.ui;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.waalapp.DownloadsImage;
import com.example.waalapp.R;
import com.example.waalapp.Statics;
import com.example.waalapp.model.Waal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.SimpleTimeZone;

import static android.Manifest.permission.FACTORY_TEST;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.SET_WALLPAPER;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class WaalDetail extends AppCompatActivity implements View.OnClickListener {
    Waal waal;
    ImageView wallpaper;
    Button downloadBtn;
    Button setBtn;

    private static final int PERMISSION_REQUEST_CODE = 200;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waal_detail);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Statics.actionbarcentertitle(this, getDrawable(R.drawable.ic_arrow_back), getString(R.string.app_name), Color.WHITE, true, false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        wallpaper = findViewById(R.id.imageView2);

        downloadBtn = findViewById(R.id.btnDownload);
        setBtn = findViewById(R.id.btnSet);
        downloadBtn.setOnClickListener(this);
        setBtn.setOnClickListener(this);

        if (this.getIntent().getParcelableExtra("waal") != null) {
            waal = this.getIntent().getParcelableExtra("waal");
            loadWaal(waal);
        }
    }

    public void loadWaal(Waal waal) {
        Glide.with(this).load(waal.imagePath).into(wallpaper);
    }


    void DownloadImage(String ImageUrl, String name) {
        showToast("Downloading Image...");
        //Asynctask to create a thread to downlaod image in the background
        new DownloadsImage(WaalDetail.this).execute(ImageUrl, name);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDownload:
                fonksiyon();
                break;
            case R.id.btnSet:

                getBitmapFromURL(waal.imagePath);

                break;
        }
    }

    void showToast(String msg) {
        Toast.makeText(WaalDetail.this, msg, Toast.LENGTH_SHORT).show();
    }


    public void fonksiyon() {
        if (checkPermission()) {
            //yapılcak sey
            DownloadImage(waal.imagePath, "waal" + String.valueOf(waal.id));
        } else {
            requestPermission();
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int result3 = ContextCompat.checkSelfPermission(getApplicationContext(), SET_WALLPAPER);
        return result == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED&& result3 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(WaalDetail.this, new String[]{
                WRITE_EXTERNAL_STORAGE,
                READ_EXTERNAL_STORAGE,
                SET_WALLPAPER
        }, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hepsiokey = true;
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                hepsiokey = false;
                break;
            }
        }
        if (hepsiokey) {
            fonksiyon();
        }
    }



    public void getBitmapFromURL(String path) {

        Glide.with(WaalDetail.this)
                .asBitmap()
                .load(path)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        try {
                            WallpaperManager.getInstance(getApplicationContext()).setBitmap(resource);
                            Toast.makeText(WaalDetail.this, "Set wallpaper successfully!", Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
