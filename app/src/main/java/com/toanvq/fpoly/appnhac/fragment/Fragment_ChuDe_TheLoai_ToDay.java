package com.toanvq.fpoly.appnhac.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.toanvq.fpoly.appnhac.R;
import com.toanvq.fpoly.appnhac.model.ChuDe;
import com.toanvq.fpoly.appnhac.model.ChuDeTheLoai;
import com.toanvq.fpoly.appnhac.model.TheLoai;
import com.toanvq.fpoly.appnhac.service.APIservice;
import com.toanvq.fpoly.appnhac.service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDe_TheLoai_ToDay extends Fragment {

    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtxemthemchudetheloai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai_today,container,false);

        horizontalScrollView = view.findViewById(R.id.horizontalSconView);
        txtxemthemchudetheloai = view.findViewById(R.id.textviewxemthem);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIservice.getService();
        Call<List<ChuDeTheLoai>> callback = dataservice.GetCategoryMusic();
        callback.enqueue(new Callback<List<ChuDeTheLoai>>() {
            @Override
            public void onResponse(Call<List<ChuDeTheLoai>> call, Response<List<ChuDeTheLoai>> response) {
            ChuDeTheLoai chuDeTheLoai = (ChuDeTheLoai) response.body();


            final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
            chuDeArrayList.addAll(chuDeTheLoai.getChuDe());

            final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
            theLoaiArrayList.addAll(chuDeTheLoai.getTheLoai());


                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(580,250);
                layout.setMargins(10,20,10,30);

                for (int i =0; i < (chuDeArrayList.size()); i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (chuDeArrayList.get(i).getHinhChuDe() != null){
                        Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                }


                for (int j =0; j < (theLoaiArrayList.size()); j++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (theLoaiArrayList.get(j).getHinhTheLoai() != null){
                        Picasso.with(getActivity()).load(theLoaiArrayList.get(j).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                }
                horizontalScrollView.addView(linearLayout);

            }

            @Override
            public void onFailure(Call<List<ChuDeTheLoai>> call, Throwable t) {

            }
        });

    }


}
