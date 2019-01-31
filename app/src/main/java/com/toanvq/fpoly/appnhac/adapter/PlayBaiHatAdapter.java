package com.toanvq.fpoly.appnhac.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.toanvq.fpoly.appnhac.R;
import com.toanvq.fpoly.appnhac.model.BaiHat;

import java.util.ArrayList;

public class PlayBaiHatAdapter extends RecyclerView.Adapter<PlayBaiHatAdapter.ViewHolder> {

    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public PlayBaiHatAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_play_bai_hat,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        BaiHat baiHat = baiHatArrayList.get(i);
        viewHolder.txtplayindex.setText(i +1 + "");
        viewHolder.txttenbaihat.setText(baiHat.getTenbaihat());
        viewHolder.txtcasi.setText(baiHat.getCasi());

    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtplayindex,txttenbaihat,txtcasi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtplayindex = itemView.findViewById(R.id.textplayindex);
            txttenbaihat = itemView.findViewById(R.id.textviewtenbaihat);
            txtcasi = itemView.findViewById(R.id.textviewPlaytencasi);
        }
    }
}
