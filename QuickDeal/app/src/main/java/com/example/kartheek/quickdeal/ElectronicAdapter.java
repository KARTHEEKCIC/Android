package com.example.kartheek.quickdeal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by kartheek on 18/4/17.
 */

public class ElectronicAdapter extends ArrayAdapter<String> {


    public ElectronicAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull String[] objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        TextView categoryName;
        if(listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.electronic_item,parent,false);
            categoryName = (TextView) listView.findViewById(R.id.product);
            listView.setTag(R.id.product,categoryName);
            listView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent category1 = new Intent(getContext(),ServicesActivity.class);
                    category1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getContext().startActivity(category1);
                }
            });

        } else {
            categoryName = (TextView) listView.getTag(R.id.product);
        }
        categoryName.setText(getItem(position));

        return listView;
    }
}