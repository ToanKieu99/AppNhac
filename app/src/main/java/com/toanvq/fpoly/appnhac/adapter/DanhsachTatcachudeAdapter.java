package com.toanvq.fpoly.appnhac.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.toanvq.fpoly.appnhac.R;
import com.toanvq.fpoly.appnhac.activity.DanhsachtheloaitheochudeActivity;
import com.toanvq.fpoly.appnhac.model.ChuDe;

import java.util.ArrayList;

public class DanhsachTatcachudeAdapter extends RecyclerView.Adapter<DanhsachTatcachudeAdapter.ViewHolder> {

    Context context;
    ArrayList<ChuDe> arraychude;

    public DanhsachTatcachudeAdapter(Context context, ArrayList<ChuDe> arraychude) {
        this.context = context;
        this.arraychude = arraychude;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_tatcachude,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ChuDe chuDe = arraychude.get(i);
        Picasso.with(context).load(chuDe.getHinhChuDe()).into(viewHolder.imgchude);

    }

    @Override
    public int getItemCount() {
        return arraychude.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgchude;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgchude = itemView.findViewById(R.id.imageviewtatcachude);
            imgchude.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,DanhsachtheloaitheochudeActivity.class);
                    intent.putExtra("CHUDE",arraychude.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
