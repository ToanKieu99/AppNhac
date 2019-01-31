package com.toanvq.fpoly.appnhac.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.squareup.picasso.Picasso;
import com.toanvq.fpoly.appnhac.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_dia_nhac extends Fragment {

    View view;
    ObjectAnimator objectAnimator;
    CircleImageView circleImageViewl;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_dia_nhac,container,false);
        circleImageViewl = view.findViewById(R.id.imageviewcircle);
        objectAnimator = ObjectAnimator.ofFloat(circleImageViewl,"rotation",0f,360f);
        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());
        return view;
    }

    public void PlayNhac(String hinhanh) {
        Picasso.with(getContext()).load(hinhanh).into(circleImageViewl);


    }
}
