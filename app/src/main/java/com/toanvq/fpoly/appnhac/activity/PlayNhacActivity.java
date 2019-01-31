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
import java.util.Random;

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
    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;


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
                if (viewPagerPlaynhacAdapter.getItem(1) != null) {
                    if (mangbaihat.size() > 0) {
                        fragment_dia_nhac.PlayNhac(mangbaihat.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);
                        UpdateTime();
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.iconplay);
                } else {
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.iconpause);
                }
            }
        });

        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false) {
                    if (checkrandom == true) {
                        checkrandom = false;
                        imgrepeat.setImageResource(R.drawable.iconsyned);
                        imgrandom.setImageResource(R.drawable.iconsuffle);
                    }
                    imgrepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                } else {
                    imgrepeat.setImageResource(R.drawable.iconsyned);
                    repeat = false;
                }
            }
        });
        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkrandom == false) {
                    if (repeat == true) {
                        repeat = false;
                        imgrandom.setImageResource(R.drawable.iconshuffled);
                        imgrepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imgrandom.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                } else {
                    imgrandom.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }

            }
        });

        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangbaihat.size() != 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangbaihat.size())) {
                        imgplay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = mangbaihat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (mangbaihat.size() - 1)) {
                            position = 0;
                        }
                        new Playmp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_dia_nhac.PlayNhac(mangbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                    UpdateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);
            }
        });

        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mangbaihat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangbaihat.size())) {
                        imgplay.setImageResource(R.drawable.iconpause);
                        position--;

                        if (position < 0) {
                            position = mangbaihat.size() - 1;
                        }

                        if (repeat == true) {

                            position += 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }

                        new Playmp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_dia_nhac.PlayNhac(mangbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                        UpdateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);
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
                mediaPlayer.stop();
                mangbaihat.clear();
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
        if (mangbaihat.size() > 0) {
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

    private void UpdateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer !=null){
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txttimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                next = true;

                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                }

        },300);
        final Handler handler1 = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true){



                    if (position < (mangbaihat.size())) {
                        imgplay.setImageResource(R.drawable.iconpause);
                        position--;

                        if (position < 0) {
                            position = mangbaihat.size() - 1;
                        }

                        if (repeat == true) {

                            position += 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }

                        new Playmp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_dia_nhac.PlayNhac(mangbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                    }

                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);

                next  = false;
                handler1.removeCallbacks(this);

                }else {
                    handler1.postDelayed(this,1000);
                }
            }
        },1000);
    }
}
