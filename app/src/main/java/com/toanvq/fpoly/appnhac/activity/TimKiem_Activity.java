package com.toanvq.fpoly.appnhac.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.toanvq.fpoly.appnhac.R;
import com.toanvq.fpoly.appnhac.adapter.SreachAdapter;
import com.toanvq.fpoly.appnhac.model.BaiHat;
import com.toanvq.fpoly.appnhac.service.APIservice;
import com.toanvq.fpoly.appnhac.service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimKiem_Activity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerViewsearch;
    TextView textViewthongbao;
    ImageView imgthoat;
    SreachAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);


        toolbar = findViewById(R.id.toolbarsearchbaihat);
        recyclerViewsearch = findViewById(R.id.recyclervewsearchbaihat);
        textViewthongbao = findViewById(R.id.txtsearchKhongcodulieu);
        imgthoat = findViewById(R.id.imgthoat);
        imgthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem menuItem = menu.findItem(R.id.menusearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Searchtukhoa(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void Searchtukhoa(String query){
        Dataservice dataservice = APIservice.getService();
        Call<List<BaiHat>> callback = dataservice.GetSearchBaiHat(query);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> mangbaihat = (ArrayList<BaiHat>) response.body();
                if (mangbaihat.size() > 0){
                    adapter = new SreachAdapter(TimKiem_Activity.this,mangbaihat);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TimKiem_Activity.this);
                    recyclerViewsearch.setLayoutManager(linearLayoutManager);
                    recyclerViewsearch.setAdapter(adapter);
                    textViewthongbao.setVisibility(View.GONE);
                    recyclerViewsearch.setVisibility(View.VISIBLE);
                }else {
                    recyclerViewsearch.setVisibility(View.GONE);
                    textViewthongbao.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}
