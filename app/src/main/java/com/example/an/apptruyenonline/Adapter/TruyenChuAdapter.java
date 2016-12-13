package com.example.an.apptruyenonline.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.an.apptruyenonline.Object.Story;
import com.example.an.apptruyenonline.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by An on 12/10/2016.
 */

public class TruyenChuAdapter extends RecyclerView.Adapter<TruyenChuAdapter.ViewHolder> {
    List<Story> storyList;
    Context context;

    public TruyenChuAdapter(Context context, List<Story> storyList) {
        this.context = context;
        this.storyList = storyList;
    }

    @Override
    public TruyenChuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_truyenchu_customlayout,
                parent, false);
        return new TruyenChuAdapter.ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(TruyenChuAdapter.ViewHolder holder, int position) {
        Story story = storyList.get(position);
        holder.txtNameStory.setText(story.getNameStory());
        holder.txtDescription.setText(Html.fromHtml(story.getDescription()));
        holder.txtReleaseDate.setText(story.getReleaseDate());
        if (story.getActive().equals("1")) {
            holder.txtActive.setText("Hoàn tất");
        } else {
            holder.txtActive.setText("Chưa hoàn thành");
        }
        Picasso.with(context).load(story.getImages()).fit().centerCrop().into(holder.img);
    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameStory;
        TextView txtReleaseDate;
        TextView txtDescription;
        TextView txtActive;
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNameStory = (TextView) itemView.findViewById(R.id.txtNameStory);
            txtReleaseDate = (TextView) itemView.findViewById(R.id.txtReleaseDate);
            txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
            txtActive = (TextView) itemView.findViewById(R.id.txtActive);
            img = (ImageView) itemView.findViewById(R.id.imgTruyenTranh);
        }
    }
}
