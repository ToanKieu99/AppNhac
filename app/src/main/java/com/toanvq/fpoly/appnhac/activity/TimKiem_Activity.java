package com.toanvq.fpoly.appnhac.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class TimKiem_Activity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerViewsearch;
    TextView textViewthongbao;
    ImageView imgthoat;

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
                Log.d("BBB",s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
