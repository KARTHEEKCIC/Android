package com.example.android.themiwoklanguage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import static android.media.AudioManager.STREAM_MUSIC;

public class family_members extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        //setting the music volume to change when we increase or decrease volume in the activity
        setVolumeControlStream(STREAM_MUSIC);
        //set content view to be activity_category layout xml file
        setContentView(R.layout.activity_category);
        //create a fragment transaction to link it to the activity category file
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new MembersFragment()).commit();
    }

}


