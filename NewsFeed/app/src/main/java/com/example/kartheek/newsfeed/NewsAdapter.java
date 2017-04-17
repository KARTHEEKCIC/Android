package com.example.kartheek.newsfeed;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kartheek on 14/3/17.
 */

public class NewsAdapter extends ArrayAdapter<NewsItem> {
    public NewsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_item,parent,false);
        }
        //getting the item in that position
        NewsItem item = getItem(position);

        //setting the title of the news
        TextView header = (TextView) convertView.findViewById(R.id.list_item_header);
        header.setText(item.getNewsTitle());

        //setting the body of the news
        TextView body = (TextView) convertView.findViewById(R.id.list_item_body);
        body.setText(item.getNewsBody());

        return convertView;
    }
}
