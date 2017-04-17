package com.example.kartheek.newsfeed;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static com.example.kartheek.newsfeed.R.layout.news_list;

/**
 * Created by kartheek on 13/3/17.
 */

public class NewsFragment extends android.support.v4.app.Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<List<NewsItem>> {

    private String mnewsTopic = "Trending";

    private String mUrl;

    private int mloaderNumber;

    private ProgressBar prgBar;

    private NewsAdapter newsAdp;

    private TextView emptyTxt;

    public NewsFragment() {

    }

    public NewsFragment(String newsTopic, int loaderNumber) {
        mnewsTopic = newsTopic;
        mloaderNumber = loaderNumber;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if (mnewsTopic == "Trending") {
            //creating the url string
            mUrl = "https://content.guardianapis.com/search?orderBy=newest&show-fields=headline,byline,standfirst&api-key=67da73d0-d602-4a2b-869f-1d0b714e0b93";
        } else {
            mUrl = "https://content.guardianapis.com/" + mnewsTopic + "?orderBy=newest&show-fields=headline,byline,standfirst&api-key=67da73d0-d602-4a2b-869f-1d0b714e0b93";
        }

        //inflating the list view for each page
        View rootView = inflater.inflate(news_list, container, false);

        //finding the list view to set up the adapter
        ListView news_list = (ListView) rootView.findViewById(R.id.listview);

        //finding the text view to be set if data is empty
        emptyTxt = (TextView) rootView.findViewById(R.id.empty_list_txt);
        //setting the empty Text view with the list view to show text if no data was obtained
        news_list.setEmptyView(emptyTxt);

        //getting the progress bar
        prgBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);

        //adapter for the list view
        newsAdp = new NewsAdapter(getActivity(), 0, new ArrayList<NewsItem>());

        news_list.setAdapter(newsAdp);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("NewsFragment.class","Initializing the loader"+mUrl);
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isConnected()) {
            getLoaderManager().initLoader(mloaderNumber, null, this);
            Log.e("NewsFragment.class","Initializing the loader"+mUrl);
        }
        else {
            prgBar.setVisibility(View.GONE);
            emptyTxt.setText("No Internet Connection");
        }
    }

    @Override
    public android.support.v4.content.Loader<List<NewsItem>> onCreateLoader(int id, Bundle args) {
        Log.e("NewsFragment.class","Creating the loader"+mUrl);
        return new NewsLoader(getContext(), mUrl);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<List<NewsItem>> loader, List<NewsItem> data) {
        Log.e("NewsFragment.class","On load finished" + mUrl);
        newsAdp.clear();
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        prgBar.setVisibility(View.GONE);
        if (data != null && !data.isEmpty()) {
            newsAdp.addAll(data);
            emptyTxt.setVisibility(View.GONE);
        } else if(netInfo != null && netInfo.isConnected()) {
            emptyTxt.setText("No News Found");
        }
            else {
            emptyTxt.setText("No Internet Connection");
        }
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<List<NewsItem>> loader) {
        newsAdp.clear();
        Log.e("NewsFragment.class","Resetting the loader" + mUrl);
    }
}


