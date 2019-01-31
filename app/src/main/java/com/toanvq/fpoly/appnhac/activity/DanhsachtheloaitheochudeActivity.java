package com.toanvq.fpoly.appnhac.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.toanvq.fpoly.appnhac.R;
import com.toanvq.fpoly.appnhac.adapter.DanhSachTheLoaitheochudeAdapter;
import com.toanvq.fpoly.appnhac.model.ChuDe;
import com.toanvq.fpoly.appnhac.model.TheLoai;
import com.toanvq.fpoly.appnhac.service.APIservice;
import com.toanvq.fpoly.appnhac.service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtheloaitheochudeActivity extends AppCompatActivity {

    ChuDe chuDe;
    Toolbar toolbar;
    RecyclerView recyclerViewtheloaitheochude;
    DanhSachTheLoaitheochudeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtheloaitheochude);
        GetIntent();
        init();
        GetData();

    }

    private void GetData() {
        Dataservice dataservice = APIservice.getService();
        Call<List<TheLoai>> callback = dataservice.GetTheloaitheochude(chuDe.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> arraytheloai = (ArrayList<TheLoai>) response.body();
                adapter = new DanhSachTheLoaitheochudeAdapter(DanhsachtheloaitheochudeActivity.this,arraytheloai);
                recyclerViewtheloaitheochude.setLayoutManager(new GridLayoutManager(DanhsachtheloaitheochudeActivity.this,2));
                recyclerViewtheloaitheochude.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewtheloaitheochude = findViewById(R.id.recyclerviewTheloaitheochude);
        toolbar = findViewById(R.id.toolbartheloaitheochude);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChhuDe());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void GetIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("CHUDE")){
            chuDe = (ChuDe) intent.getSerializableExtra("CHUDE");

        }
    }
}
