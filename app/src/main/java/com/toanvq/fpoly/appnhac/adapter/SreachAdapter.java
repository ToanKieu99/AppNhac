package com.toanvq.fpoly.appnhac.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.toanvq.fpoly.appnhac.R;
import com.toanvq.fpoly.appnhac.activity.PlayNhacActivity;
import com.toanvq.fpoly.appnhac.model.BaiHat;
import com.toanvq.fpoly.appnhac.service.APIservice;
import com.toanvq.fpoly.appnhac.service.Dataservice;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SreachAdapter extends RecyclerView.Adapter<SreachAdapter.ViewHolder> {

    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public SreachAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_search,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        BaiHat baiHat = baiHatArrayList.get(i);
        viewHolder.txtten.setText(baiHat.getTenbaihat());
        viewHolder.txtcasi.setText(baiHat.getCasi());
        Picasso.with(context).load(baiHat.getHinhbaihat()).into(viewHolder.imganhbaihat);

    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imganhbaihat,imgluotthich;
        TextView txtten,txtcasi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imganhbaihat = itemView.findViewById(R.id.imagebaihatSearch);
            imgluotthich = itemView.findViewById(R.id.imageviewluotthichsearch);
            txtten = itemView.findViewById(R.id.txttensearch);
            txtcasi = itemView.findViewById(R.id.txttencasisearch);
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(context,PlayNhacActivity.class);
                   intent.putExtra("CAKHUC",baiHatArrayList.get(getPosition()));
                   context.startActivity(intent);
               }
           });

           imgluotthich.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   imgluotthich.setImageResource(R.drawable.iconloved);
                   Dataservice dataservice = APIservice.getService();
                   Call<String> callback = dataservice.UpdateLuotThich("1",baiHatArrayList.get(getPosition()).getIdbaihat());
                   callback.enqueue(new Callback<String>() {
                       @Override
                       public void onResponse(Call<String> call, Response<String> response) {
                           String kq = response.body();
                           if (kq.equals("Success")){
                               Toast.makeText(context, "Like", Toast.LENGTH_SHORT).show();
                           }else {
                               Toast.makeText(context, "false", Toast.LENGTH_SHORT).show();
                           }
                       }

                       @Override
                       public void onFailure(Call<String> call, Throwable t) {

                       }
                   });
                   imgluotthich.setEnabled(false);

               }
           });
        }
    }
}
