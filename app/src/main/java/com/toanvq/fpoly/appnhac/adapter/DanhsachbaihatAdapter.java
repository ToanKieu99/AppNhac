package com.toanvq.fpoly.appnhac.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.toanvq.fpoly.appnhac.R;
import com.toanvq.fpoly.appnhac.model.BaiHat;

import java.util.ArrayList;

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder> {

    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public DanhsachbaihatAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_danhsachbaihat,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        BaiHat baiHat = baiHatArrayList.get(i);
        viewHolder.txtcasi.setText(baiHat.getCasi());
        viewHolder.txtindex.setText(i + 1 + "");
        viewHolder.txttenbaihat.setText(baiHat.getTenbaihat());


    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtindex, txttenbaihat, txtcasi;
        ImageView imgluotthich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtcasi = itemView.findViewById(R.id.textviewtencasi);
            txtindex = itemView.findViewById(R.id.textviewdanhdachchindex);
            txttenbaihat = itemView.findViewById(R.id.textviewdanhsachtenbaihat);
            imgluotthich = itemView.findViewById(R.id.imageluothichdanhsachbaihat);
        }
    }
}
