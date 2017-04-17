package com.example.android.themiwoklanguage;


import android.view.View;

/**
 * Created by kartheek on 24/12/16.
 * a class to store the english and miwok translation of a given word
 */

public class Word {

    //creating a string to store the english translation
    private String nEnglish;
    //creating a string to store the miwok translation
    private String nMiwok;
    //creating a integer to store the image source
    private int nImgSrc;
    //creating a variable to store the audio Resource Id
    private int nAudioSrc;

    //public constructor with english and miwok translations as its arguments
    public Word(String english, String miwok, int audioSrc)
    {
        //initializing the states of the class
        nEnglish = english;
        nMiwok = miwok;
        nAudioSrc = audioSrc;
        // to make the ImageView invisible
        nImgSrc = View.GONE;
    }

    //public constructor with english and miwok translation and a integer which stores the image source as arguments
    public Word(String english, String miwok, int imgSrc, int audioSrc)
    {
        //initializing the states of the class
        nEnglish = english;
        nMiwok = miwok;
        nImgSrc = imgSrc;
        nAudioSrc = audioSrc;
    }

    //method to get the english translation
    public String getEnglishTranslation()
    {
        return nEnglish;
    }

    //method to get the miwok translation
    public String getMiwokTranslation()
    {
        return nMiwok;
    }

    //method to get the image src
    public int getImgSrc() { return nImgSrc; }

    //method to get the audio Resource Id
    public int getnAudioSrc() { return nAudioSrc; }

}

