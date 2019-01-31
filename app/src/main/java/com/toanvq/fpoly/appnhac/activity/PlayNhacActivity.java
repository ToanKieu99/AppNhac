package com.toanvq.fpoly.appnhac.activity;

import android.content.Intent;
import android.graphics.Color;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        init();
        GetDataFromIntent();
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

    }
}
