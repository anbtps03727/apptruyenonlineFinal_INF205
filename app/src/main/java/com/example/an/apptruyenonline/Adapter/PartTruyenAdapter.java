package com.example.an.apptruyenonline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.an.apptruyenonline.GiaoDienTruyen.ChapterTruyen;
import com.example.an.apptruyenonline.Object.Part;
import com.example.an.apptruyenonline.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by An on 12/10/2016.
 */

public class PartTruyenAdapter extends RecyclerView.Adapter<PartTruyenAdapter.ViewHolder> {
    Context context;
    List<Part> list;

    public PartTruyenAdapter(Context context, List<Part> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_parttruyen_customlayout,
                parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       final Part part = list.get(position);
        holder.txtNamePart.setText(part.getNamePart());
        Picasso.with(context).load(part.getImages()).fit().centerCrop().into(holder.img);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChapterTruyen.class);
                intent.putExtra("IdPart",String.valueOf(part.getIdPart()));
                intent.putExtra("NamePart",part.getNamePart());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView txtNamePart;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imgPartTruyen);
            txtNamePart = (TextView) itemView.findViewById(R.id.txtNamePart);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
        }
    }
}
