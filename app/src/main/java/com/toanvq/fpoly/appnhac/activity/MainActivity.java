package com.toanvq.fpoly.appnhac.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.toanvq.fpoly.appnhac.R;
import com.toanvq.fpoly.appnhac.fragment.Fragment_Trang_Chu;

public class MainActivity extends AppCompatActivity {


    TextView btTimKiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btTimKiem = findViewById(R.id.timkiem);
        btTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new TimKiem_Activity()).commit();
                Intent intent = new Intent(MainActivity.this,TimKiem_Activity.class);
                startActivity(intent);
            }
        });
        init();
    }

    private void init() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new Fragment_Trang_Chu()).commit();
    }
}
