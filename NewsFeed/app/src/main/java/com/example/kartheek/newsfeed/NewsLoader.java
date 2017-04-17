package com.example.kartheek.newsfeed;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

/**
 * Created by kartheek on 15/3/17.
 */

public class NewsLoader extends AsyncTaskLoader<List<NewsItem>> {

    private String mUrl = null;

    public NewsLoader(Context context, String Url) {
        super(context);
        mUrl = Url;
    }

    @Override
    protected void onStartLoading() {
        Log.e("NewsLoader.class","starting the loader" + mUrl);
        forceLoad();
    }

    @Override
    public List<NewsItem> loadInBackground() {

        Log.e("NewsLoader.class","Loading in the background"+mUrl);
        if(mUrl == null){
            return null;
        }
        return HelperJs.getNews(mUrl);
    }
}
