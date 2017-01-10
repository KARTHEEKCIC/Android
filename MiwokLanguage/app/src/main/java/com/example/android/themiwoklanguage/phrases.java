package com.example.android.themiwoklanguage;


import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class phrases extends AppCompatActivity {

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        //setting the music volume to change when we increase or decrease volume in the activity
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        //set content view to be activity_category layout xml file
        setContentView(R.layout.activity_category);
        //create a fragment transaction to link it to the activity category file
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new PhrasesFragment()).commit();

       }
}
