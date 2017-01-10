package com.example.android.themiwoklanguage;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by kartheek on 4/1/17.
 */

public class CategoryPagerAdapter extends FragmentPagerAdapter {

    /** Context of the app */
    private Context mContext;


    //return the number of categories in the app
    @Override
    public int getCount() {
        return 4;
    }

    //over writing the method to return the correct fragment when the user slides through view pager
    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return new com.example.android.themiwoklanguage.NumbersFragment();
        }
        else if(position == 1) {
            return new com.example.android.themiwoklanguage.MembersFragment();
        }
            else if(position == 2) {
            return new com.example.android.themiwoklanguage.ColorsFragment();
        }
                else
            return new com.example.android.themiwoklanguage.PhrasesFragment();
        }

    //returning the title of each category fragment
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0)
            return "Numbers";
        else if(position == 1)
                return "Family";
            else if(position == 2)
                    return "Colors";
                else return "Phrases";
    }

    public CategoryPagerAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext = context;
    }
}
