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
import com.toanvq.fpoly.appnhac.model.TheLoai;

import java.util.ArrayList;

public class DanhSachTheLoaitheochudeAdapter extends RecyclerView.Adapter<DanhSachTheLoaitheochudeAdapter.ViewHolder> {

    Context context;
    ArrayList<TheLoai> theLoaiArrayList;

    public DanhSachTheLoaitheochudeAdapter(Context context, ArrayList<TheLoai> theLoaiArrayList) {
        this.context = context;
        this.theLoaiArrayList = theLoaiArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View  view = inflater.inflate(R.layout.item_theloai_theochude,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TheLoai theLoai = theLoaiArrayList.get(i);
        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(viewHolder.imgtheloaitheochude);
        viewHolder.txttentheloaitheochude.setText(theLoai.getTenTheLoai());

    }

    @Override
    public int getItemCount() {
        return theLoaiArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgtheloaitheochude;
        TextView txttentheloaitheochude;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgtheloaitheochude = itemView.findViewById(R.id.imageviewTheloaiTheochude);
            txttentheloaitheochude = itemView.findViewById(R.id.textviewtentheloaitheochude);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,DanhSachBaiHatActivity.class);
                    intent.putExtra("IDTHELOAI",theLoaiArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }

}
