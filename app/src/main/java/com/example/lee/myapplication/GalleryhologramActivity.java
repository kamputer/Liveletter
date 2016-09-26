package com.example.lee.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class GalleryhologramActivity extends AppCompatActivity {
    private ImageView image2,image4,image6,image8;
    private ImageView background2, background4, background6, background8;
    private RelativeLayout relativelayout;
    private int bgtype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galleryhologram);

        Intent intent = getIntent();
        Bitmap photo = (Bitmap) intent.getExtras().get("photo");
        bgtype = intent.getExtras().getInt("bgtype");

        Button buttonsave = (Button) findViewById(R.id.btn_gallerysave);
        Button buttonsend = (Button) findViewById(R.id.btn_gallerysend);

        relativelayout = (RelativeLayout) findViewById(R.id.galleryrelative);

        image2 = (ImageView) findViewById(R.id.imagegallery2);
        image4 = (ImageView) findViewById(R.id.imagegallery4);
        image6 = (ImageView) findViewById(R.id.imagegallery6);
        image8 = (ImageView) findViewById(R.id.imagegallery8);

        image2.setImageBitmap(photo);
        image4.setImageBitmap(photo);
        image6.setImageBitmap(photo);
        image8.setImageBitmap(photo);

        background2 = (ImageView) findViewById(R.id.imagegallerybg2);
        background4 = (ImageView) findViewById(R.id.imagegallerybg4);
        background6 = (ImageView) findViewById(R.id.imagegallerybg6);
        background8 = (ImageView) findViewById(R.id.imagegallerybg8);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;

        switch(bgtype){
            case 0:
                background2.setImageResource(0);
                background4.setImageResource(0);
                background6.setImageResource(0);
                background8.setImageResource(0);
                break;
            case 1:
                Bitmap bg1 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_heart,options);
                background2.setImageBitmap(bg1);
                background4.setImageBitmap(bg1);
                background6.setImageBitmap(bg1);
                background8.setImageBitmap(bg1);
                break;
            case 2:
                Bitmap bg2 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_birthday,options);
                background2.setImageBitmap(bg2);
                background4.setImageBitmap(bg2);
                background6.setImageBitmap(bg2);
                background8.setImageBitmap(bg2);
                break;
            case 3:
                Bitmap bg3 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_thanks,options);
                background2.setImageBitmap(bg3);
                background4.setImageBitmap(bg3);
                background6.setImageBitmap(bg3);
                background8.setImageBitmap(bg3);
                break;
            case 4:
                Bitmap bg4 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_goodnight,options);
                background2.setImageBitmap(bg4);
                background4.setImageBitmap(bg4);
                background6.setImageBitmap(bg4);
                background8.setImageBitmap(bg4);
                break;
            default:
                background2.setImageResource(0);
                background4.setImageResource(0);
                background6.setImageResource(0);
                background8.setImageResource(0);
                break;
        }

        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OutputStream outStream = null;
                relativelayout.setDrawingCacheEnabled(true);
                relativelayout.buildDrawingCache();
                Bitmap captureView = Bitmap.createBitmap(relativelayout.getDrawingCache(true));


                File imagePath = new File(Environment.getExternalStorageDirectory().toString(),
                        "IMG_" + System.currentTimeMillis() + ".png");
                try {
                    outStream = new FileOutputStream(imagePath);
                    captureView.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                    outStream.flush();
                    outStream.close();

                    Toast.makeText(GalleryhologramActivity.this,
                            "Saved", Toast.LENGTH_LONG).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(GalleryhologramActivity.this,
                            e.toString(), Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(GalleryhologramActivity.this,
                            e.toString(), Toast.LENGTH_LONG).show();
                }

                relativelayout.setDrawingCacheEnabled(false);
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory())));

            }
        });
    }
}