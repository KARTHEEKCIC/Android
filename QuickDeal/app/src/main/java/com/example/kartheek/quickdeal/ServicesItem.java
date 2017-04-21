package com.example.kartheek.quickdeal;

/**
 * Created by kartheek on 21/4/17.
 */

public class ServicesItem {
    private String mServiceProvider;

    private String mLocation;

    public ServicesItem(String ServiceProvider, String Location) {
        mServiceProvider = ServiceProvider;
        mLocation = Location;
    }

    //getter
    public String getmServiceProvider() { return mServiceProvider; }

    public String getmLocation() { return mLocation; }

    //setter
    public void setmServiceProvider(String ServiceProvider) {
        mServiceProvider = ServiceProvider;
    }

    public void setmLocation(String Location) {
        mLocation = Location;
    }
}
