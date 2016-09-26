package com.example.lee.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Button home = (Button) findViewById(R.id.btn_home7);
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button buttonCamera = (Button) findViewById(R.id.btn_take_video);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TakevideoActivity.class);
                startActivity(intent);
            }

        });

        Button buttonGallery = (Button) findViewById(R.id.btn_gallery_video);
        buttonGallery.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GalleryvideoActivity.class);
                startActivity(intent);
            }

        });
    }
}
