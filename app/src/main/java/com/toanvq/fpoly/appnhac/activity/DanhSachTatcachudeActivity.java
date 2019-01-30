package com.toanvq.fpoly.appnhac.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.toanvq.fpoly.appnhac.R;
import com.toanvq.fpoly.appnhac.adapter.DanhsachTatcachudeAdapter;
import com.toanvq.fpoly.appnhac.model.ChuDe;
import com.toanvq.fpoly.appnhac.service.APIservice;
import com.toanvq.fpoly.appnhac.service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTatcachudeActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerViewtatcachude;
    DanhsachTatcachudeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_tatcachude);
        init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIservice.getService();
        Call<List<ChuDe>> callback = dataservice.GetAllChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> chuDeArrayList = (ArrayList<ChuDe>) response.body();
                adapter = new DanhsachTatcachudeAdapter(DanhSachTatcachudeActivity.this,chuDeArrayList);
                recyclerViewtatcachude.setLayoutManager(new GridLayoutManager(DanhSachTatcachudeActivity.this,1));
                recyclerViewtatcachude.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewtatcachude = findViewById(R.id.recyclerviewTatcachude);
        toolbar = findViewById(R.id.toolbarchude);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả chủ đề");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
