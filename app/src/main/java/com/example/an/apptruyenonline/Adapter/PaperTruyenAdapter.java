package com.example.an.apptruyenonline.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.an.apptruyenonline.Object.Paper;
import com.example.an.apptruyenonline.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by An on 12/10/2016.
 */

public class PaperTruyenAdapter extends RecyclerView.Adapter<PaperTruyenAdapter.ViewHolder> {
    Context context;
    List<Paper> paperList;

    public PaperTruyenAdapter(Context context, List<Paper> list) {
        this.context = context;
        this.paperList = list;
    }

    @Override
    public PaperTruyenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_paper_truyen_customlayout,
                parent, false);
        return new PaperTruyenAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PaperTruyenAdapter.ViewHolder holder, int position) {
        Paper paper = paperList.get(position);
        Picasso.with(context).load(paper.getPaper()).fit().centerCrop().into(holder.img);

    }

    @Override
    public int getItemCount() {
        return paperList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.paper_truyen);
        }
    }
}