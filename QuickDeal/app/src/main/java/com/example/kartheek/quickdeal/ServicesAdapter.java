package com.example.kartheek.quickdeal;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by kartheek on 21/4/17.
 */

public class ServicesAdapter extends ArrayAdapter<ServicesItem> {

    public ServicesAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        TextView ServiceProvider;
        TextView Location;
        if(listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.services_item,parent,false);
            ServiceProvider = (TextView) listItem.findViewById(R.id.serviceProvider);
            Location = (TextView) listItem.findViewById(R.id.location);
            listItem.setTag(R.id.serviceProvider,ServiceProvider);
            listItem.setTag(R.id.location,Location);
            listItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),"Display for messaging",Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            ServiceProvider = (TextView) listItem.getTag(R.id.serviceProvider);
            Location = (TextView) listItem.getTag(R.id.location);
        }

        ServiceProvider.setText(getItem(position).getmServiceProvider());
        Location.setText(getItem(position).getmLocation());

        return listItem;
    }
}