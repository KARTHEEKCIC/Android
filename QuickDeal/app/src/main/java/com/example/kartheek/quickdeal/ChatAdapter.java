package com.example.kartheek.quickdeal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kartheek on 20/4/17.
 */

public class ChatAdapter extends ArrayAdapter<ChatItem> {
    public ChatAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View gridView = convertView;
        TextView title;
        TextView text;
        TextView time;
        TextView unread;
        final ImageView dealDone;

        if(gridView == null) {
            gridView = LayoutInflater.from(getContext()).inflate(R.layout.single_chat,parent,false);
            title = (TextView) gridView.findViewById(R.id.title);
            text = (TextView) gridView.findViewById(R.id.text);
            time = (TextView) gridView.findViewById(R.id.time);
            unread = (TextView) gridView.findViewById(R.id.unread);
            dealDone = (ImageView) gridView.findViewById(R.id.deal);
            dealDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent dealdone = new Intent(getContext(),DealDoneActivity.class);
                    dealdone.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    dealdone.putExtra("dealdone",getItem(position).getmDealDone());
                    dealdone.putExtra("position",position);
                    getContext().startActivity(dealdone);
                }
            });
            gridView.setTag(R.id.deal,dealDone);
            gridView.setTag(R.id.text,text);
            gridView.setTag(R.id.title,title);
            gridView.setTag(R.id.time,time);
            gridView.setTag(R.id.unread,unread);
        } else{
            title = (TextView) gridView.getTag(R.id.title);
            text = (TextView) gridView.getTag(R.id.text);
            time = (TextView) gridView.getTag(R.id.time);
            unread = (TextView) gridView.getTag(R.id.unread);
            dealDone = (ImageView) gridView.getTag(R.id.deal);
        }

        title.setText(getItem(position).getmChatTitle());
        text.setText(getItem(position).getmText());
        time.setText(getItem(position).getmTime());
        if(getItem(position).getmUnreadMessages()>0) {
            unread.setVisibility(View.VISIBLE);
            unread.setText(String.valueOf(getItem(position).getmUnreadMessages()));
            unread.setBackgroundResource(R.drawable.unread_chats_circle);
            title.setTextColor(getContext().getResources().getColor(R.color.unreadMessageTitleColor));
        } else {
            unread.setVisibility(View.GONE);
        }

        if(getItem(position).getmDealDone() == true) {
            Log.e("ChatAdapter","dealdone");
            dealDone.setImageResource(R.drawable.dealdone);
        } else {
            Log.e("ChatAdapter","dealnotdone");
            dealDone.setImageResource(R.drawable.dealnotdone);
        }

        return gridView;
    }
}
