package com.toanvq.fpoly.appnhac.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.toanvq.fpoly.appnhac.R;
import com.toanvq.fpoly.appnhac.adapter.PlaylistAdapter;
import com.toanvq.fpoly.appnhac.model.PlayList;
import com.toanvq.fpoly.appnhac.service.APIservice;
import com.toanvq.fpoly.appnhac.service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_PlayList extends Fragment {

    View view;
    ListView lvPlaylist;
    TextView txttitlePlaylist,txtviewxemthemplaylist;
    PlaylistAdapter playlistAdapter;
    ArrayList<PlayList> mangPlaylist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist,container,false);
        lvPlaylist = view.findViewById(R.id.listviewPlaylist);
        txttitlePlaylist = view.findViewById(R.id.textviewTitlePlaylist);
        txtviewxemthemplaylist = view.findViewById(R.id.textviewviewmoreplaylist);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIservice.getService();
        Call<List<PlayList>> callback = dataservice.GetPlayListCurrentDay();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                 mangPlaylist = (ArrayList<PlayList>) response.body();
               playlistAdapter = new PlaylistAdapter(getActivity(),android.R.layout.simple_list_item_1,mangPlaylist);
               lvPlaylist.setAdapter(playlistAdapter);
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }
}
