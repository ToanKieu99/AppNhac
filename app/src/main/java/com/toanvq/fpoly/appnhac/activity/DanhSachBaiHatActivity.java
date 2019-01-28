package com.toanvq.fpoly.appnhac.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.toanvq.fpoly.appnhac.R;
import com.toanvq.fpoly.appnhac.model.Quangcao;

public class DanhSachBaiHatActivity extends AppCompatActivity {

    Quangcao quangcao;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewDanhSach;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        DataIntent();
        Anhxa();
    }

    private void Anhxa() {
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbarDanhSach);
        recyclerViewDanhSach = findViewById(R.id.recyclerviewDanhSachBaiHat);
        fab = findViewById(R.id.floatingactionBottom);


    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra("BANNER")){
                quangcao = (Quangcao) intent.getSerializableExtra("BANNER");
                Toast.makeText(this, quangcao.getTenBaiHat(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
