package com.example.lee.myapplication;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class GallerypictureActivity extends AppCompatActivity {
    private static final int PICK_FROM_GALLERY = 1;
    private static final int CROP_FROM_GALLERY = 2;
    private ImageView imgview;
    private ImageView background;
    private Uri mImageCaptureUri;
    private int bgtype;
    private Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallerypicture);

        bgtype = 0;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        final Bitmap bg1 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_heart,options);
        final Bitmap bg2 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_birthday,options);
        final Bitmap bg3 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_thanks,options);
        final Bitmap bg4 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_goodnight,options);

        Button home = (Button) findViewById(R.id.btn_home3);
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button change = (Button) findViewById(R.id.btn_change1);
        change.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), GalleryhologramActivity.class);
                intent.putExtra("photo",photo);
                intent.putExtra("bgtype",bgtype);
                startActivity(intent);
            }
        });

        Button nobackground = (Button) findViewById(R.id.bg1_no);
        nobackground.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                background.setImageResource(0);
                bgtype = 0;
            }
        });

        Button background1 = (Button) findViewById(R.id.bg1_1);
        background1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                background.setImageBitmap(bg1);
                bgtype = 1;
            }
        });

        Button background2 = (Button) findViewById(R.id.bg1_2);
        background2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                background.setImageBitmap(bg2);
                bgtype = 2;
            }
        });

        Button background3 = (Button) findViewById(R.id.bg1_3);
        background3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                background.setImageBitmap(bg3);
                bgtype = 3;
            }
        });

        Button background4 = (Button) findViewById(R.id.bg1_4);
        background4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                background.setImageBitmap(bg4);
                bgtype = 4;
            }
        });

        imgview = (ImageView) findViewById(R.id.imageView1);
        background = (ImageView) findViewById(R.id.background1);

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);

        try {
            startActivityForResult(intent, PICK_FROM_GALLERY);
        } catch (ActivityNotFoundException e) {
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_OK)
        {
            return;
        }

        if (requestCode == PICK_FROM_GALLERY) {
            mImageCaptureUri = data.getData();
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(mImageCaptureUri, "image/*");

            intent.putExtra("outputX", 300);
            intent.putExtra("outputY", 300);
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("scale", true);
            intent.putExtra("return-data", true);
            startActivityForResult(intent, CROP_FROM_GALLERY);
        }

        if (requestCode == CROP_FROM_GALLERY) {
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
