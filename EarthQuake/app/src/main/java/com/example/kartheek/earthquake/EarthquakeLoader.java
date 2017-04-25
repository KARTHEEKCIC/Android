package com.example.kartheek.earthquake;

/**
 * Created by kartheek on 11/3/17.
 */

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private String murl = null;

    public EarthquakeLoader(Context context, String nUrl) {
        super(context);
        murl = nUrl;
    }

    @Override
    protected void onStartLoading() {
        Log.e("MainActivity","starting loader");
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        Log.e("MainActivity","starting loading in background");
        if (murl == null)
            return null;
        List<Earthquake> earthquakes = QueryUtils.getEarthQuakes(murl);
        return earthquakes;
    }

}