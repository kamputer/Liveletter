package com.example.lee.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TvideohologramActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener{
    private TextureView video2, video4, video6, video8;
    private ImageView background2, background4, background6, background8;
    private int bgtype;
    private Uri mImageMovieUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvideohologram);

        Intent intent = getIntent();
        mImageMovieUri = intent.getParcelableExtra("mImageMovieUri");
        bgtype = intent.getExtras().getInt("bgtype");

        Button buttonplay = (Button) findViewById(R.id.btn_videotakeplay);
        Button buttonsend = (Button) findViewById(R.id.btn_videotakesend);

        video2 = (TextureView) findViewById(R.id.videotake2);
        video4 = (TextureView) findViewById(R.id.videotake4);
        video6 = (TextureView) findViewById(R.id.videotake6);
        video8 = (TextureView) findViewById(R.id.videotake8);

        video2.setSurfaceTextureListener(this);
        video4.setSurfaceTextureListener(this);
        video6.setSurfaceTextureListener(this);
        video8.setSurfaceTextureListener(this);

        background2 = (ImageView) findViewById(R.id.videotakebg2);
        background4 = (ImageView) findViewById(R.id.videotakebg4);
        background6 = (ImageView) findViewById(R.id.videotakebg6);
        background8 = (ImageView) findViewById(R.id.videotakebg8);

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

        buttonplay.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = getIntent();
                overridePendingTransition(0,0);
                intent.addFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                overridePendingTransition(0,0);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        final MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setSurface(new Surface(surface));
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(this,mImageMovieUri);
            mediaPlayer.setScreenOnWhilePlaying(true);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                public void onBufferingUpdate(MediaPlayer mediaPlayer, int percent) {
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mediaPlayer) {
                }
            });
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
                public void onPrepared(MediaPlayer mediaPlayer){
                    mediaPlayer.start();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {return false;}
    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {}
    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {}
}
