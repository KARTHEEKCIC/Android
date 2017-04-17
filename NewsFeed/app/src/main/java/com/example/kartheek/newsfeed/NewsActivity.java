package com.example.kartheek.newsfeed;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // finding the view pager in the main activity
        ViewPager viewpg = (ViewPager) findViewById(R.id.viewpager);
        viewpg.setOffscreenPageLimit(0);

        //creating an instance of the adapter
        CategoryPagerAdapter newsAdapter = new CategoryPagerAdapter(getApplicationContext(),getSupportFragmentManager());

        //attaching the adapter to view pager to display supply the content
        viewpg.setAdapter(newsAdapter);

        TabLayout tbLayout = (TabLayout) findViewById(R.id.tab);
        tbLayout.setupWithViewPager(viewpg);

    }
}
