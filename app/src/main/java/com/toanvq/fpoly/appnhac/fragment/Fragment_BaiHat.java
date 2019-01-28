package com.toanvq.fpoly.appnhac.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toanvq.fpoly.appnhac.R;
import com.toanvq.fpoly.appnhac.adapter.BaiHatThichAdapter;
import com.toanvq.fpoly.appnhac.model.BaiHat;
import com.toanvq.fpoly.appnhac.service.APIservice;
import com.toanvq.fpoly.appnhac.service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_BaiHat extends Fragment {

    View view;
    RecyclerView recyclerBaiHat;
    BaiHatThichAdapter baiHatThichAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_baihat,container,false);
        recyclerBaiHat = view.findViewById(R.id.recyclerBaiHatThich);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIservice.getService();
        Call<List<BaiHat>> callback = dataservice.GetBaiHat();
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> arrayBaiHat = (ArrayList<BaiHat>) response.body();
               baiHatThichAdapter = new BaiHatThichAdapter(getActivity(),arrayBaiHat);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerBaiHat.setLayoutManager(linearLayoutManager);
                recyclerBaiHat.setAdapter(baiHatThichAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}
