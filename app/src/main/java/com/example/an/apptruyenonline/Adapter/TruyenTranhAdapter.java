package com.example.an.apptruyenonline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.an.apptruyenonline.GiaoDienTruyen.PartTruyen;
import com.example.an.apptruyenonline.Object.Story;
import com.example.an.apptruyenonline.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by An on 12/9/2016.
 */

public class TruyenTranhAdapter extends RecyclerView.Adapter<TruyenTranhAdapter.ViewHolder> {
    List<Story> storyList;
    Context context;

    public TruyenTranhAdapter(Context context, List<Story> storyList) {
        this.context = context;
        this.storyList = storyList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_truyentranh_customlayout,
                parent, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Story story = storyList.get(position);
        holder.txtNameStory.setText(story.getNameStory());
        holder.txtDescription.setText(Html.fromHtml(story.getDescription()));
        holder.txtReleaseDate.setText(story.getReleaseDate());
        if(story.getActive().equals("1")){
            holder.txtActive.setText("Hoàn tất");
        }else{
            holder.txtActive.setText("Chưa hoàn thành");
        }
        Picasso.with(context).load(story.getImages()).fit().centerCrop().into(holder.img);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PartTruyen.class);
                intent.putExtra("IdStory",String.valueOf(story.getIdStory()));
                intent.putExtra("NameStory",story.getNameStory());
                context.startActivity(intent);
            }
        });
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
        CardView cardView;
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            txtNameStory = (TextView) itemView.findViewById(R.id.txtNameStory);
            txtReleaseDate = (TextView)itemView.findViewById(R.id.txtReleaseDate);
            txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
            txtActive = (TextView) itemView.findViewById(R.id.txtActive);
            img = (ImageView) itemView.findViewById(R.id.imgTruyenTranh);
            cardView = (CardView)itemView.findViewById(R.id.cv);
        }
    }
}
