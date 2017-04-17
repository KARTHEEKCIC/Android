package com.example.android.themiwoklanguage;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class numbers extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //set content view to be activity_category layout xml file
        setContentView(R.layout.activity_category);

        //create a fragment transaction to link it to the activity category file
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new NumbersFragment()).commit();
    }

}