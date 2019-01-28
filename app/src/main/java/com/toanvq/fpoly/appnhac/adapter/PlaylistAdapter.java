package com.toanvq.fpoly.appnhac.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.toanvq.fpoly.appnhac.R;
import com.toanvq.fpoly.appnhac.model.PlayList;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<PlayList> {


    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<PlayList> objects) {
        super(context, resource, objects);
    }

    class ViewHolder{
        TextView txttenPlaylist;
        ImageView imgBackgroun,imgplaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_playlist,null);
            viewHolder = new ViewHolder();
            viewHolder.txttenPlaylist = convertView.findViewById(R.id.textviewtenPlaylist);
            viewHolder.imgplaylist = convertView.findViewById(R.id.imageviewplaylist);
            viewHolder.imgBackgroun = convertView.findViewById(R.id.imageviewbackgroundPlaylist);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PlayList playList = getItem(position);
        Picasso.with(getContext()).load(playList.getHinhnen()).into(viewHolder.imgBackgroun);
        Picasso.with(getContext()).load(playList.getHinhicon()).into(viewHolder.imgplaylist);
        viewHolder.txttenPlaylist.setText(playList.getTen());

        return convertView;
    }
}
