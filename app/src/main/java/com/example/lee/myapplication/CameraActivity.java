package com.example.lee.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CameraActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Button home = (Button) findViewById(R.id.btn_home1);
        Button buttonCamera = (Button) findViewById(R.id.btn_take_camera);
        Button buttonGallery = (Button) findViewById(R.id.btn_select_gallery);


        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        buttonCamera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), TakepictureActivity.class);
                    startActivity(intent);
                }
        });

        buttonGallery.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GallerypictureActivity.class);
                startActivity(intent);
            }

        });
}
}