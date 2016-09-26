package com.example.lee.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class GalleryvideoActivity extends AppCompatActivity{
    private static final int PICK_FROM_GALLERY = 1;
    private VideoView videoview;
    private ImageView background;
    private Uri mImageMovieUri;
    private int bgtype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galleryvideo);

        bgtype = 0;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        final Bitmap bg1 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_heart,options);
        final Bitmap bg2 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_birthday,options);
        final Bitmap bg3 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_thanks,options);
        final Bitmap bg4 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_goodnight,options);

        Button home = (Button) findViewById(R.id.btn_home4);
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button change = (Button) findViewById(R.id.btn_change4);
        change.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), GvideohologramActivity.class);
                intent.putExtra("mImageMovieUri",mImageMovieUri);
                intent.putExtra("bgtype",bgtype);
                startActivity(intent);
            }
        });

        Button nobackground = (Button) findViewById(R.id.vbg2_no);
        nobackground.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                background.setImageResource(0);
                bgtype = 0;
            }
        });

        Button background1 = (Button) findViewById(R.id.vbg2_1);
        background1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                background.setImageBitmap(bg1);
                bgtype = 1;
            }
        });

        Button background2 = (Button) findViewById(R.id.vbg2_2);
        background2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                background.setImageBitmap(bg2);
                bgtype = 2;
            }
        });

        Button background3 = (Button) findViewById(R.id.vbg2_3);
        background3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                background.setImageBitmap(bg3);
                bgtype = 3;
            }
        });

        Button background4 = (Button) findViewById(R.id.vbg2_4);
        background4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                background.setImageBitmap(bg4);
                bgtype = 4;
            }
        });

        videoview = (VideoView) findViewById(R.id.videoView2);
        background = (ImageView) findViewById(R.id.videobackground2);

        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
        try{
            startActivityForResult(intent,PICK_FROM_GALLERY);
        }catch(ActivityNotFoundException e){
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == PICK_FROM_GALLERY) {
            mImageMovieUri = data.getData();
            MediaController mc = new MediaController(GalleryvideoActivity.this);
            videoview.setMediaController(mc);
            videoview.setVideoURI(mImageMovieUri);
            videoview.requestFocus();
            videoview.start();
        }
    }
}
