package com.example.android.zailet;

/**
 * Created by kartheek on 21/5/17.
 */

public class Suggestions {

    private String mTitle;
    private String[] mTopics;

    public Suggestions(String mTitle, String[] mTopics) {
        this.mTitle = mTitle;
        this.mTopics = mTopics;
    }

    public String getmTitle(){
        return mTitle;
    }

    public String[] getmTopics() {
        return mTopics;
    }
}
