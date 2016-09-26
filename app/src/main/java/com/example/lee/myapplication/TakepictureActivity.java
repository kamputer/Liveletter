package com.example.lee.myapplication;

import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;


public class TakepictureActivity extends AppCompatActivity {
    private static final int PICK_FROM_CAMERA = 1;
    private static final int CROP_FROM_CAMERA = 2;
    private ImageView imgview;
    private ImageView background;
    private Uri mImageCaptureUri;
    private int bgtype;
    private Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_takepicture);

        bgtype = 0;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        final Bitmap bg1 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_heart,options);
        final Bitmap bg2 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_birthday,options);
        final Bitmap bg3 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_thanks,options);
        final Bitmap bg4 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_goodnight,options);

        Button home = (Button) findViewById(R.id.btn_home5);
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button change = (Button) findViewById(R.id.btn_change2);
        change.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), PicturehologramActivity.class);
                intent.putExtra("photo",photo);
                intent.putExtra("bgtype",bgtype);
                startActivity(intent);
            }
        });

        Button nobackground = (Button) findViewById(R.id.bg2_no);
        nobackground.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                background.setImageResource(0);
                bgtype = 0;
            }
        });

        Button background1 = (Button) findViewById(R.id.bg2_1);
        background1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                background.setImageBitmap(bg1);
                bgtype = 1;
            }
        });

        Button background2 = (Button) findViewById(R.id.bg2_2);
        background2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                background.setImageBitmap(bg2);
                bgtype = 2;
            }
        });

        Button background3 = (Button) findViewById(R.id.bg2_3);
        background3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                background.setImageBitmap(bg3);
                bgtype = 3;
            }
        });

        Button background4 = (Button) findViewById(R.id.bg2_4);
        background4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                background.setImageBitmap(bg4);
                bgtype = 4;
            }
        });

        imgview = (ImageView) findViewById(R.id.imageView2);
        background= (ImageView) findViewById(R.id.background2);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));

        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);

        try {
            startActivityForResult(intent, PICK_FROM_CAMERA);
        } catch (ActivityNotFoundException e) {
            // Do nothing for now
        }

    }
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        if(resultCode != RESULT_OK)
        {
            return;
        }

        if (requestCode == PICK_FROM_CAMERA) {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(mImageCaptureUri, "image/*");

            intent.putExtra("outputX", 300);
            intent.putExtra("outputY", 300);
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("scale", true);
            intent.putExtra("return-data", true);
            startActivityForResult(intent, CROP_FROM_CAMERA);
        }
        if (requestCode == CROP_FROM_CAMERA) {
            final Bundle extras = data.getExtras();
            if(extras != null)
            {
                photo = extras.getParcelable("data");
                imgview.setImageBitmap(photo);
            }

            File f = new File(mImageCaptureUri.getPath());
            if(f.exists())
            {
                f.delete();
            }
        }
    }
}
