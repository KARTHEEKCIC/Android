package com.example.kartheek.newsfeed;

/**
 * Created by kartheek on 14/3/17.
 */

public class NewsItem {

    private String mNewsTitle;

    private String mNewsBody;

    public NewsItem(String NewsTitle, String NewsBody) {

        mNewsTitle = NewsTitle;
        mNewsBody = NewsBody;
    }

    public String getNewsTitle() {
        return mNewsTitle;
    }

    public String getNewsBody() {
        return mNewsBody;
    }

}
