/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.themiwoklanguage;

import android.media.AudioManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        //setting the music volume to change when we increase or decrease volume in the activity
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        //finding the view pager in the main activity layout file
        ViewPager category = (ViewPager) findViewById(R.id.category);

        //creating an instance of the custom FragmentPagerAdapter
        CategoryPagerAdapter categoryAdapter = new CategoryPagerAdapter(getApplicationContext(),getSupportFragmentManager());

        //setting up the custom adapter to the view pager
        category.setAdapter(categoryAdapter);

        //finding the TabLayout in activity_main xml file
        TabLayout category_tabs = (TabLayout) findViewById(R.id.sliding_tabs);

        //setting the TabLayout with the ViewPager
        category_tabs.setupWithViewPager(category);


    }

}