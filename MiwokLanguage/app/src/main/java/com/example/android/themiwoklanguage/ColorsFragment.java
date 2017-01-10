package com.example.android.themiwoklanguage;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_GAIN;
import static android.media.AudioManager.AUDIOFOCUS_LOSS;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {

    //a MediaPlayer Object to store and play the audio file
    private MediaPlayer miwokAudio;

    //creating an instance of the AudioManager Class
    AudioManager familyAudio;

    //creating an instance of onAudioFocusChangeListener to be supplied as argument to requestAudioFocus() method
    // to listen and notify us when Audio Focus is changed by calling onAudioFocusChange() method
    final AudioManager.OnAudioFocusChangeListener familyAudioFocus = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AUDIOFOCUS_GAIN)
                miwokAudio.start();
            else if(focusChange == AUDIOFOCUS_LOSS)
                releaseMediaPlayer();
            else if(focusChange == AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
                miwokAudio.pause();
            miwokAudio.seekTo(0);
        }
    };

    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //inflating the view with the words_list layout
        View rootView = inflater.inflate(R.layout.words_list,container,false);

        //creating an array list of objects of the word class
        ArrayList<Word> numbers = new ArrayList<Word>();

        //inserting the english and miwok words in @numbers array list of object of Word class
        numbers.add(new Word("red","weṭeṭṭi", R.drawable.color_red,R.raw.color_red));
        numbers.add(new Word("green","chokokki",R.drawable.color_green,R.raw.color_green));
        numbers.add(new Word("brown","ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
        numbers.add(new Word("gray","ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
        numbers.add(new Word("black","kululli",R.drawable.color_black,R.raw.color_black));
        numbers.add(new Word("white","kelelli",R.drawable.color_white,R.raw.color_white));
        numbers.add(new Word("dusty yellow","ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        numbers.add(new Word("mustard yellow","chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));

        //creating the WordAdapter which extends ArrayAdapter for the list view
        final WordAdapter colorsAdapter = new WordAdapter(getContext(),numbers,R.color.category_colors);

        //finding the list view
        ListView colors_list = (ListView) rootView.findViewById(R.id.list);

        //setting the WordAdapter for the list view
        colors_list.setAdapter(colorsAdapter);

        //the getSystemService is called to provide the audio service
        familyAudio = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        //creating an onCompleteListener Object to with the implementation of the onCompletion method
        //to release the memory allocated to miwokAudio on Completion
        final MediaPlayer.OnCompletionListener oncompletelistener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                releaseMediaPlayer();
            }
        };

        //setting the onItemClickListener for the list item above
        colors_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //to release the memory which was occupied by the audio which was not completely play
                //in case when user may have clicked another audio before completion of previous audio
                //this will also stop the previous audio and play the next audio clicked
                releaseMediaPlayer();

                //checking whether the audio focus request is granted or not
                //if request is granted then start the audio or else do nothing
                if(familyAudio.requestAudioFocus(
                        //AudioFocusListener for the current activity
                        familyAudioFocus,
                        //we are playing an audio of words being spoken so it can be categorized under music
                        AudioManager.STREAM_MUSIC,
                        //playing an audio of little duration
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    //creating an instance of MediaPlayer by passing the context and audio Resource Id as arguments
                    miwokAudio = MediaPlayer.create(getContext(), colorsAdapter.getItem(position).getnAudioSrc());

                    //play the miwok audio
                    miwokAudio.start();

                    //setting a onCompletionListener on the miwokAudio
                    miwokAudio.setOnCompletionListener(oncompletelistener);
                }
            }
        });
            return rootView;
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (miwokAudio != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            miwokAudio.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            miwokAudio = null;

            //abandon the audio focus when the audio has been completely played
            familyAudio.abandonAudioFocus(familyAudioFocus);

        }
    }

    @Override
    public void onStop() {
        super.onStop();
        // to release the memory allocated to any audio file
        //when the activity is being stopped or not seen by the user
        releaseMediaPlayer();
    }
}
