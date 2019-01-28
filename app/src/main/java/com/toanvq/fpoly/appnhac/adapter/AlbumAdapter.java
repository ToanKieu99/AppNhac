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
import com.toanvq.fpoly.appnhac.model.Album;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    Context context;
    ArrayList<Album> albumArrayList;

    public AlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater =  LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_album,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Album album = albumArrayList.get(i);
        viewHolder.txtcasialbum.setText(album.getTencasiAlbum());
        viewHolder.txtTenAlbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhanhAlbum()).into(viewHolder.imghinhAlbum);

    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imghinhAlbum;
        TextView txtTenAlbum,txtcasialbum;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhAlbum = itemView.findViewById(R.id.imageviewAlbum);
            txtTenAlbum = itemView.findViewById(R.id.textviewtenalbum);
            txtcasialbum = itemView.findViewById(R.id.textviewtencasialbum);
        }
    }
}
