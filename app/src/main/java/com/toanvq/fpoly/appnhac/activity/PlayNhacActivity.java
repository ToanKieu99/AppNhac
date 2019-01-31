package com.toanvq.fpoly.appnhac.activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.toanvq.fpoly.appnhac.R;
import com.toanvq.fpoly.appnhac.adapter.PlayBaiHatAdapter;
import com.toanvq.fpoly.appnhac.adapter.ViewPagerPlaynhacAdapter;
import com.toanvq.fpoly.appnhac.fragment.Fragment_Danh_Sach_Play_BaiHat;
import com.toanvq.fpoly.appnhac.fragment.Fragment_dia_nhac;
import com.toanvq.fpoly.appnhac.model.BaiHat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PlayNhacActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView txttimesong, txttotaltimesong;
    SeekBar sktime;
    ImageButton imgplay, imgrepeat, imgnext, imgpre, imgrandom;
    ViewPager viewPagerPlayNhac;
    public static ArrayList<BaiHat> mangbaihat = new ArrayList<>();
    public static ViewPagerPlaynhacAdapter viewPagerPlaynhacAdapter;
    Fragment_dia_nhac fragment_dia_nhac;
    Fragment_Danh_Sach_Play_BaiHat fragment_danh_sach_play_baiHat;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        init();
        evenClick();

    }

    private void evenClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewPagerPlaynhacAdapter.getItem(1) != null){
                    if (mangbaihat.size() > 0){
                        fragment_dia_nhac.PlayNhac(mangbaihat.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);
                    }else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        },500);
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.iconplay);
                }else {
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.iconpause);
                }
            }
        });
    }


    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if (intent != null) {

            if (intent.hasExtra("CAKHUC")) {
                BaiHat baiHat = intent.getParcelableExtra("CAKHUC");
                mangbaihat.add(baiHat);

            }
            if (intent.hasExtra("ALLBAIHAT")) {
                ArrayList<BaiHat> baiHatArrayList = intent.getParcelableArrayListExtra("ALLBAIHAT");
                mangbaihat = baiHatArrayList;

            }
        }

    }


    private void init() {
        toolbar = findViewById(R.id.toolbarplaynhac);
        txttimesong = findViewById(R.id.txttimesong);
        txttotaltimesong = findViewById(R.id.textviewtotaltimesong);
        sktime = findViewById(R.id.seebarsong);
        imgplay = findViewById(R.id.imamgebuttonplay);
        imgnext = findViewById(R.id.imamgebuttonnext);
        imgpre = findViewById(R.id.imamgebuttonpreview);
        imgrepeat = findViewById(R.id.imamgebuttonpepeat);
        imgrandom = findViewById(R.id.imamgebuttonsuffle);
        viewPagerPlayNhac = findViewById(R.id.viewpagerplaynhac);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitleTextColor(Color.WHITE);
        fragment_dia_nhac = new Fragment_dia_nhac();
        fragment_danh_sach_play_baiHat = new Fragment_Danh_Sach_Play_BaiHat();
        viewPagerPlaynhacAdapter = new ViewPagerPlaynhacAdapter(getSupportFragmentManager());
        viewPagerPlaynhacAdapter.AddFragment(fragment_danh_sach_play_baiHat);
        viewPagerPlaynhacAdapter.AddFragment(fragment_dia_nhac);
        viewPagerPlayNhac.setAdapter(viewPagerPlaynhacAdapter);

        fragment_dia_nhac = (Fragment_dia_nhac) viewPagerPlaynhacAdapter.getItem(1);
        if (mangbaihat.size() >0){
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenbaihat());
            new Playmp3().execute(mangbaihat.get(0).getLinkbaihat());
            imgplay.setImageResource(R.drawable.iconpause);
        }

    }

    class Playmp3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            return strings[0];
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }

                });

                mediaPlayer.setDataSource(s);
                mediaPlayer.prepare();

            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txttotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());
    }
}
