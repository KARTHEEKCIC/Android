package com.example.kartheek.earthquake;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kartheek on 11/1/17.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View Earthquake = convertView;
        if(Earthquake == null){
            //inflate a new layout if there is no layout returned by list view
            Earthquake = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,parent,false);
        }

        //finding the earthquakelistitem linearlayout in the xml file inflated above
        LinearLayout earthquakeDetails = (LinearLayout) Earthquake.findViewById(R.id.earthquakeListItem);

        //finding the magnitude textview
        TextView magnitude = (TextView) Earthquake.findViewById(R.id.magnitude);
        //setting the magnitude of the earthquake
        float mag = (float) getItem(position).getNmagnitude();
        magnitude.setText(mag+"");
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();
        //setting the color to the magnitude circle
        int color = getMagnitudeColor(mag);
        magnitudeCircle.setColor(color);

        //finding the city textview
        TextView city = (TextView) Earthquake.findViewById(R.id.city);
        //setting the city in which the earthquake occurred
        city.setText(getItem(position).getNcity());

        //finding the distance textview
        TextView distance = (TextView) Earthquake.findViewById(R.id.distance);
        //setting the distance from the city at which the earthquake occurred
        distance.setText(getItem(position).getNdistace());

        //finding the date textview
        TextView date = (TextView) Earthquake.findViewById(R.id.date);
        //setting the date on which the earthquake occurred
        date.setText(getItem(position).getNdate());

        //finding the time textview
        TextView time = (TextView) Earthquake.findViewById(R.id.time);
        //setting the time on the date on which the earthquake occurred
        time.setText(getItem(position).getnTime());

        //setting the onclicklistener on the earthquakedetails linearlayout which is the list item for list view
        earthquakeDetails.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Uri Url = Uri.parse(getItem(position).getnUrl());
                Intent goToUrl = new Intent(Intent.ACTION_VIEW,Url);
                if(goToUrl.resolveActivity(getContext().getPackageManager())!=null)
                    getContext().startActivity(goToUrl);
            }
        });
        //returning the list item
        return Earthquake;
    }

    //method to return the correct color based on the magnitude of the earthquake
    public int getMagnitudeColor(float magnitude)
    {
        int iMagnitude = (int) magnitude;
        switch (iMagnitude) {
            case 0:
            case 1:
                return ContextCompat.getColor(getContext(),R.color.magnitude1);
            case 2:
                return ContextCompat.getColor(getContext(),R.color.magnitude2);
            case 3:
                return ContextCompat.getColor(getContext(),R.color.magnitude3);
            case 4:
                return ContextCompat.getColor(getContext(),R.color.magnitude4);
            case 5:
                return ContextCompat.getColor(getContext(),R.color.magnitude5);
            case 6:
                return ContextCompat.getColor(getContext(),R.color.magnitude6);
            case 7:
                return ContextCompat.getColor(getContext(),R.color.magnitude7);
            case 8:
                return ContextCompat.getColor(getContext(),R.color.magnitude8);
            case 9:
                return ContextCompat.getColor(getContext(),R.color.magnitude9);
            default:
                return ContextCompat.getColor(getContext(),R.color.magnitude10plus);
        }
    }

    public EarthquakeAdapter(Context context, int resource, List<Earthquake> objects) {
        super(context, resource, objects);
    }

}
