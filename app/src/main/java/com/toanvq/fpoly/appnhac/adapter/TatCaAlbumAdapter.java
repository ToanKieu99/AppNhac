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

import com.squareup.picasso.Picasso;
import com.toanvq.fpoly.appnhac.R;
import com.toanvq.fpoly.appnhac.activity.DanhSachBaiHatActivity;
import com.toanvq.fpoly.appnhac.model.Album;

import java.util.ArrayList;

import retrofit2.http.PUT;

public class TatCaAlbumAdapter extends RecyclerView.Adapter<TatCaAlbumAdapter.ViewHolder> {

    Context context;
    ArrayList<Album> albumArrayList;

    public TatCaAlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_tat_ca_album,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Album album = albumArrayList.get(i);
        Picasso.with(context).load(album.getHinhanhAlbum()).into(viewHolder.imgalbum);
        viewHolder.txttenalbum.setText(album.getTenAlbum());
        viewHolder.tencassiAlbum.setText(album.getTencasiAlbum());

    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgalbum;
        TextView txttenalbum,tencassiAlbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgalbum = itemView.findViewById(R.id.imageviewallalbum);
            txttenalbum = itemView.findViewById(R.id.txttenALLAlbum);
            tencassiAlbum = itemView.findViewById(R.id.tencassiAlbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,DanhSachBaiHatActivity.class);
                    intent.putExtra("ALBUM",albumArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
