package com.example.kartheek.earthquake;

/**
 * Created by kartheek on 11/3/17.
 */

public class Earthquake {

    //magnitude of the earthquake
    private double nmagnitude;
    //distance from the city in which the earthquake occurred
    private String ndistace;
    //city in which the earthquake occurred
    private String ncity;
    //date on which the earthquake occurred
    private String ndate;
    //time on the date at which the earthquake occurred
    private String nTime;
    //USGS url which gives details about the earthquake
    private String nUrl;

    //constructor to initialize the magnitude, city and the date on which the current earthquake occurred
    public Earthquake(double magnitude, String distance, String city, String date, String time, String Url)
    {
        nmagnitude = magnitude;
        ndistace = distance;
        ncity = city;
        ndate = date;
        nTime = time;
        nUrl=Url;
    }

    //method to get the magnitude of the earthquake
    public double getNmagnitude()
    {
        return nmagnitude;
    }

    //method to get the city in which the earthquake occurred
    public String getNcity()
    {
        return ncity;
    }

    //method to get the date on which the earthquake occurred
    public String getNdate()
    {
        return ndate;
    }

    //method to get the time on the on which the earthquake occurred
    public String getnTime() { return nTime; }

    //method to get the distance from the city
    public String getNdistace() { return ndistace; }

    //method to get the Url which gives the earthquake details
    public String getnUrl() { return nUrl; }

}
