package com.toanvq.fpoly.appnhac.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.StrictMode;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.toanvq.fpoly.appnhac.R;
import com.toanvq.fpoly.appnhac.adapter.DanhsachbaihatAdapter;
import com.toanvq.fpoly.appnhac.model.Album;
import com.toanvq.fpoly.appnhac.model.BaiHat;
import com.toanvq.fpoly.appnhac.model.PlayList;
import com.toanvq.fpoly.appnhac.model.Quangcao;
import com.toanvq.fpoly.appnhac.model.TheLoai;
import com.toanvq.fpoly.appnhac.service.APIservice;
import com.toanvq.fpoly.appnhac.service.Dataservice;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatActivity extends AppCompatActivity {

    Quangcao quangcao;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewDanhSach;
    FloatingActionButton fab;
    ImageView imgdanhsachcakhuc;
    ArrayList<BaiHat> mangbaihat;
    DanhsachbaihatAdapter danhsachbaihatAdapter;
    PlayList playList;
    TheLoai theLoai;
    Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntent();
        Anhxa();
        init();
        if (quangcao != null && !quangcao.getTenBaiHat().equals("")) {
            setValueInView(quangcao.getTenBaiHat(), quangcao.getHinhBaiHat());
            GetDataquangcao(quangcao.getIdQuangCao());
        }
        if (playList != null && !playList.getTen().equals("")){
            setValueInView(playList.getTen(),playList.getHinhicon());
            GetDataPlaylist(playList.getIdPlayList());
        }
        if (theLoai != null && !theLoai.getTenTheLoai().equals("")){
            setValueInView(theLoai.getTenTheLoai(),theLoai.getHinhTheLoai());
            GetDataTheLoai(theLoai.getIdTheLoai());
        }
        if (album != null && !album.getTenAlbum().equals("")){
            setValueInView(album.getTenAlbum(),album.getHinhanhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }
    }

    private void GetDataAlbum(String idAlbum) {
        Dataservice dataservice = APIservice.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhSachBaiHatTheoAlbum(idAlbum);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhSachBaiHatActivity.this,mangbaihat);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DanhSachBaiHatActivity.this);
                recyclerViewDanhSach.setLayoutManager(linearLayoutManager);
                recyclerViewDanhSach.setAdapter(danhsachbaihatAdapter);
                evenClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });

    }


    private void GetDataTheLoai(String idtheloai){
        Dataservice dataservice = APIservice.getService();
        Call<List<BaiHat>> callback = dataservice.Getdanhsachbaihattheotheloai(idtheloai);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhSachBaiHatActivity.this,mangbaihat);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DanhSachBaiHatActivity.this);
                recyclerViewDanhSach.setLayoutManager(linearLayoutManager);
                recyclerViewDanhSach.setAdapter(danhsachbaihatAdapter);
                evenClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });

    }

    private void GetDataPlaylist(String idplaylist) {
        Dataservice dataservice = APIservice.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhSachBaihatTheoPlaylist(idplaylist);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhSachBaiHatActivity.this,mangbaihat);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DanhSachBaiHatActivity.this);
                recyclerViewDanhSach.setLayoutManager(linearLayoutManager);
                recyclerViewDanhSach.setAdapter(danhsachbaihatAdapter);
                evenClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }


    private void setValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                collapsingToolbarLayout.setBackground(bitmapDrawable);

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Picasso.with(this).load(hinh).into(imgdanhsachcakhuc);
    }

    private void GetDataquangcao(String idquangcao) {

        Dataservice dataservice = APIservice.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhSachBaiHatTheoQuangCao(idquangcao);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhSachBaiHatActivity.this, mangbaihat);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DanhSachBaiHatActivity.this);
                recyclerViewDanhSach.setLayoutManager(linearLayoutManager);
                recyclerViewDanhSach.setAdapter(danhsachbaihatAdapter);
                evenClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });

    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        fab.setExpanded(false);

    }

    private void Anhxa() {
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbarDanhSach);
        recyclerViewDanhSach = findViewById(R.id.recyclerviewDanhSachBaiHat);
        fab = findViewById(R.id.floatingactionBottom);
        imgdanhsachcakhuc = findViewById(R.id.imageviewdánhachcakhuc);


    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("BANNER")) {
                quangcao = (Quangcao) intent.getSerializableExtra("BANNER");
            }
            if (intent.hasExtra("ITEMPLAYLIST")){
                playList = (PlayList) intent.getSerializableExtra("ITEMPLAYLIST");
            }
            if (intent.hasExtra("IDTHELOAI")){
                theLoai = (TheLoai) intent.getSerializableExtra("IDTHELOAI");
            }
            if (intent.hasExtra("ALBUM")){
                album = (Album) intent.getSerializableExtra("ALBUM");
            }
        }
    }
    private void evenClick(){
        fab.setExpanded(true);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachBaiHatActivity.this,PlayNhacActivity.class);
                intent.putExtra("ALLBAIHAT",mangbaihat);
                startActivity(intent);
            }
        });
    }
}
