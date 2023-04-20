package com.example.miit_application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miit_application.model.NewsModel;

import java.util.ArrayList;

public class AdapterRV extends RecyclerView.Adapter<AdapterRV.MyViewHolder> {
    private ArrayList<NewsModel> newsModels;
    public AdapterRV(ArrayList<NewsModel> newsModels) {
        this.newsModels = newsModels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_article, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemCount(){
        return newsModels.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        NewsModel newsModel = newsModels.get(position);
        holder.title.setText(newsModel.getTitle());
        holder.text.setText(newsModel.getText());
        holder.date.setText(newsModel.getDate());
        holder.source.setText(newsModel.getText());
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title,
        text,
        date,
        source;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_title);
            text = itemView.findViewById(R.id.text_news);
            date = itemView.findViewById(R.id.news_date);
            source = itemView.findViewById(R.id.news_source);
        }
    }
}
