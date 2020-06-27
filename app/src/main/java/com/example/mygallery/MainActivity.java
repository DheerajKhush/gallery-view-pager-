package com.example.mygallery;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.io.IOException;
import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    private static ViewPager mPager;
    private static int currentPage= 0;
    private static final Integer[] img = {R.drawable.one,R.drawable.two,R.drawable.three, R.drawable.four, R.drawable.five,R.drawable.six};
    private ArrayList<Integer> ImgArray= new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
           // setWallpaper();
        for(int i=0;i<img.length;i++)
        {
            ImgArray.add(img[i]);
            mPager= findViewById(R.id.pager);
            mPager.setAdapter(new MyAdapter(MainActivity.this, ImgArray));
            CircleIndicator indicator= findViewById(R.id.indicator);
            indicator.setViewPager(mPager);

            final Handler handler= new Handler();
            final Runnable Update= new Runnable() {
                @Override
                public void run() {
                    if (currentPage == img.length)
                        currentPage = 0;

                    mPager.setCurrentItem(currentPage++, true);
                }

            };

        //auto Start
//        Timer swipeTimer = new Timer();
//        swipeTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(Update);
//            }
//        }, 2500, 2500);
    }


    }
    private void setWallpaper(){
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.one);
        WallpaperManager manager = WallpaperManager.getInstance(getApplicationContext());
        try{
            manager.setBitmap(bitmap);
            Toast.makeText(this,"Wallpaper Set!",Toast.LENGTH_SHORT).show();
        }
        catch (IOException e){
            Toast.makeText(this,"Error!",Toast.LENGTH_SHORT).show();
        }
    }
}
