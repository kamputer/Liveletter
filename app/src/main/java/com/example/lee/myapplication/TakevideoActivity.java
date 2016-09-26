package com.example.lee.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

public class TakevideoActivity extends AppCompatActivity {
    private static final int TAKE_VIDEO = 1;
    private VideoView videoview;
    private ImageView background;
    private Uri mImageMovieUri;
    private int bgtype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_takevideo);

        bgtype = 0;

        videoview=(VideoView) findViewById(R.id.videoView1);
        background = (ImageView) findViewById(R.id.videobackground1);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        final Bitmap bg1 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_heart,options);
        final Bitmap bg2 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_birthday,options);
        final Bitmap bg3 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_thanks,options);
        final Bitmap bg4 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_goodnight,options);


        Button home = (Button) findViewById(R.id.btn_home6);
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button change = (Button) findViewById(R.id.btn_change3);
        change.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), TvideohologramActivity.class);
                intent.putExtra("mImageMovieUri",mImageMovieUri);
                intent.putExtra("bgtype",bgtype);
                startActivity(intent);
            }
        });

        Button nobackground = (Button) findViewById(R.id.vbg1_no);
        nobackground.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                background.setImageResource(0);
                bgtype = 0;
            }
        });

        Button background1 = (Button) findViewById(R.id.vbg1_1);
        background1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                background.setImageBitmap(bg1);
                bgtype = 1;
            }
        });

        Button background2 = (Button) findViewById(R.id.vbg1_2);
        background2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                background.setImageBitmap(bg2);
                bgtype = 2;
            }
        });

        Button background3 = (Button) findViewById(R.id.vbg1_3);
        background3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                background.setImageBitmap(bg3);
                bgtype = 3;
            }
        });

        Button background4 = (Button) findViewById(R.id.vbg1_4);
        background4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                background.setImageBitmap(bg4);
                bgtype = 4;
            }
        });

        Intent intent=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        String url = "video_" + String.valueOf(System.currentTimeMillis()) + ".mp4";
        mImageMovieUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));

        intent.putExtra("android.intent.extras.CAMERA_FACING",1);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,10);
        intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT,(long)(1024*1024*4));
        intent.putExtra(MediaStore.EXTRA_OUTPUT,mImageMovieUri);

        try{
            startActivityForResult(intent,TAKE_VIDEO);
        } catch(ActivityNotFoundException e){
        }
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_OK)
        {
            return;
        }

        if (requestCode == TAKE_VIDEO) {
            MediaController mc=new MediaController(TakevideoActivity.this);
            videoview.setMediaController(mc);
            videoview.setVideoURI(mImageMovieUri);
            videoview.requestFocus();
            videoview.start();
        }
    }
}