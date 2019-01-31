package com.toanvq.fpoly.appnhac.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toanvq.fpoly.appnhac.R;
import com.toanvq.fpoly.appnhac.activity.PlayNhacActivity;
import com.toanvq.fpoly.appnhac.adapter.PlayBaiHatAdapter;

public class Fragment_Danh_Sach_Play_BaiHat extends Fragment {

    View view;
    RecyclerView recyclerViewPlayNhac;
    PlayBaiHatAdapter playBaiHatAdapter;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_danh_sach_play_baihat, container, false);
        recyclerViewPlayNhac = view.findViewById(R.id.recyclerPlayBaiHat);
        if (PlayNhacActivity.mangbaihat.size() > 0){
            playBaiHatAdapter = new PlayBaiHatAdapter(getActivity(),PlayNhacActivity.mangbaihat);
            recyclerViewPlayNhac.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewPlayNhac.setAdapter(playBaiHatAdapter);
        }

        return view;
    }
}
