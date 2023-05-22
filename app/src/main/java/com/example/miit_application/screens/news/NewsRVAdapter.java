package com.example.miit_application.screens.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miit_application.R;
import com.example.miit_application.data.model.News;

import java.util.ArrayList;
import java.util.List;

public class NewsRVAdapter extends RecyclerView.Adapter<NewsRVAdapter.MyViewHolder> {
    private List<News> news;
    public NewsRVAdapter() {
        this.news = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_article, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemCount(){
        return news.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        News news = this.news.get(position);
        holder.title.setText(news.getTitle());
        holder.text.setText(news.getText());
        holder.date.setText(news.getDate());
        holder.source.setText(news.getSource());
    }

    public void updateNewsList(final List<News> newsList) {
        if (news != null) {
            this.news.clear();
            this.news.addAll(newsList);
            notifyDataSetChanged();
        } else {
            news = newsList;
        }

    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView text;
        private final TextView date;
        private final TextView source;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_title);
            text = itemView.findViewById(R.id.text_news);
            date = itemView.findViewById(R.id.news_date);
            source = itemView.findViewById(R.id.news_source);
        }
    }
}
