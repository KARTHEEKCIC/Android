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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by kartheek on 18/4/17.
 */

public class CategoryAdapter extends ArrayAdapter<CategoryItem> {

    public CategoryAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View gridItem = convertView;
        ImageView categoryImage;
        TextView categoryName;
        if(gridItem == null) {
            gridItem = LayoutInflater.from(getContext()).inflate(R.layout.grid_item,parent,false);
            categoryImage = (ImageView) gridItem.findViewById(R.id.category_img);
            categoryName = (TextView) gridItem.findViewById(R.id.category);
            gridItem.setTag(R.id.category_img,categoryImage);
            gridItem.setTag(R.id.category,categoryName);

            gridItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] categories = getContext().getResources().getStringArray(R.array.categories);
                    if(getItem(position).getCategoryName().equals(categories[2])) {
                        Intent category1 = new Intent(getContext(),ElectronicActivity.class);
                        category1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getContext().startActivity(category1);
                    } else {
                        Toast.makeText(getContext(),"Display the respective category, press electronic section for example",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else {
            categoryName = (TextView) gridItem.getTag(R.id.category);
            categoryImage = (ImageView) gridItem.getTag(R.id.category_img);
        }

        categoryImage.setImageResource(getItem(position).getCategoryImage());
        categoryName.setText(getItem(position).getCategoryName());

        return gridItem;
    }
}