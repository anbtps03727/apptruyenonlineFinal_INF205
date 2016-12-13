package com.example.an.apptruyenonline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.an.apptruyenonline.GiaoDienTruyen.PaperTruyen;
import com.example.an.apptruyenonline.Object.Chapter;
import com.example.an.apptruyenonline.R;

import java.util.List;

/**
 * Created by An on 12/10/2016.
 */

public class ChapterTruyenAdapter extends RecyclerView.Adapter<ChapterTruyenAdapter.ViewHolder> {
    Context context;
    List<Chapter> chapterList;

    public ChapterTruyenAdapter(Context context, List<Chapter> list) {
        this.context = context;
        this.chapterList = list;
    }

    @Override
    public ChapterTruyenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_chapter_truyen,
                parent, false);
        return new ChapterTruyenAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChapterTruyenAdapter.ViewHolder holder, int position) {
        final Chapter chapter = chapterList.get(position);
        holder.txtNameChapter.setText(chapter.getContentChapter());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PaperTruyen.class);
                intent.putExtra("IdChapter",String.valueOf(chapter.getIdChapter()));
                intent.putExtra("NameChapter",chapter.getContentChapter());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNameChapter;
        public CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            txtNameChapter = (TextView) itemView.findViewById(R.id.txtNameChapter);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}
