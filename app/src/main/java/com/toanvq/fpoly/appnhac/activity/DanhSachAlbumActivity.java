package com.toanvq.fpoly.appnhac.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.toanvq.fpoly.appnhac.R;
import com.toanvq.fpoly.appnhac.adapter.TatCaAlbumAdapter;
import com.toanvq.fpoly.appnhac.model.Album;
import com.toanvq.fpoly.appnhac.service.APIservice;
import com.toanvq.fpoly.appnhac.service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachAlbumActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerViewAlbum;
    TatCaAlbumAdapter albumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_album);
        init();
        GetData();

    }

    private void GetData() {
        Dataservice dataservice = APIservice.getService();
        Call<List<Album>> callback = dataservice.GetAllAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList = (ArrayList<Album>) response.body();
                albumAdapter = new TatCaAlbumAdapter(DanhSachAlbumActivity.this,albumArrayList);
                recyclerViewAlbum.setLayoutManager(new GridLayoutManager(DanhSachAlbumActivity.this,2));
                recyclerViewAlbum.setAdapter(albumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void init() {
        toolbar = findViewById(R.id.toolbarAlbum);
        recyclerViewAlbum = findViewById(R.id.recyclerAlbum);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Album");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
