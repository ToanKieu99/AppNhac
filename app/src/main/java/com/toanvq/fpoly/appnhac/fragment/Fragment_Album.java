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
import android.widget.TextView;

import com.toanvq.fpoly.appnhac.R;
import com.toanvq.fpoly.appnhac.adapter.AlbumAdapter;
import com.toanvq.fpoly.appnhac.model.Album;
import com.toanvq.fpoly.appnhac.service.APIservice;
import com.toanvq.fpoly.appnhac.service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Album extends Fragment {

    View view;
    RecyclerView recyclerViewAlbum;
    TextView txtxemthemAlbum;
    AlbumAdapter albumAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album,container,false);
        recyclerViewAlbum = view.findViewById(R.id.recyclerViewAlbum);
        txtxemthemAlbum = view.findViewById(R.id.txtxemthem);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIservice.getService();
        Call<List<Album>> callback = dataservice.GetAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> arrayAlbum = (ArrayList<Album>) response.body();
                albumAdapter = new AlbumAdapter(getContext(),arrayAlbum);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewAlbum.setLayoutManager(linearLayoutManager);
                recyclerViewAlbum.setAdapter(albumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
}
