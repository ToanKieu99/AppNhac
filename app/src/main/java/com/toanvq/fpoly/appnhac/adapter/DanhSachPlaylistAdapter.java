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
import com.toanvq.fpoly.appnhac.model.PlayList;

import java.util.ArrayList;

import retrofit2.http.PUT;

public class DanhSachPlaylistAdapter extends RecyclerView.Adapter<DanhSachPlaylistAdapter.ViewHolder> {

    Context context;
    ArrayList<PlayList> arrayPlayList;

    public DanhSachPlaylistAdapter(Context context, ArrayList<PlayList> arrayPlayList) {
        this.context = context;
        this.arrayPlayList = arrayPlayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_danh_sach_playlist,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        PlayList playList = arrayPlayList.get(i);
        Picasso.with(context).load(playList.getHinhicon()).into(viewHolder.imghinhnen);
        viewHolder.txttenplaylist.setText(playList.getTen());

    }

    @Override
    public int getItemCount() {
        return arrayPlayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imghinhnen;
        TextView txttenplaylist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhnen = itemView.findViewById(R.id.imageviewDanhSachcacplaylist);
            txttenplaylist = itemView.findViewById(R.id.textviewdanhsachcacplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,DanhSachBaiHatActivity.class);
                    intent.putExtra("ITEMPLAYLIST",arrayPlayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
