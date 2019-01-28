package com.toanvq.fpoly.appnhac.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.toanvq.fpoly.appnhac.R;
import com.toanvq.fpoly.appnhac.model.BaiHat;

import java.util.ArrayList;

public class BaiHatThichAdapter extends RecyclerView.Adapter<BaiHatThichAdapter.ViewHolder> {

    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public BaiHatThichAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_baihatthich,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        BaiHat baiHat = baiHatArrayList.get(i);
        viewHolder.txtten.setText(baiHat.getTenbaihat());
        viewHolder.txtcasi.setText(baiHat.getCasi());
        Picasso.with(context).load(baiHat.getHinhbaihat()).into(viewHolder.imghinhanh);

    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtten, txtcasi;
        ImageView imghinhanh, imgluotthich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.txtbaihatthich);
            txtcasi = itemView.findViewById(R.id.txttencasibaihatthich);
            imghinhanh = itemView.findViewById(R.id.imageviewbaihathich);
            imgluotthich = itemView.findViewById(R.id.imageviewluotthich);
        }
    }
}
