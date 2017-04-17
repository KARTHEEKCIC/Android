package com.example.kartheek.newsfeed;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by kartheek on 13/3/17.
 */

public class CategoryPagerAdapter extends FragmentPagerAdapter {

    /** Context of the app */
    private Context mContext;

    public CategoryPagerAdapter(Context ctx,FragmentManager fm) {
        super(fm);
        mContext = ctx;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return new NewsFragment("Trending",0);
        } else if(position == 1) {
            return new NewsFragment("technology",1);
        } else if(position == 2) {
            return new NewsFragment("education",2);
        } else return new NewsFragment("sport",3);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0)
            return "Trending";  // first item of the tab
        else if(position == 1)
            return "Technology";
        else if(position == 2)
            return "Education";
        else return "Sports";
    }
}
