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
public class NumbersFragment extends Fragment {

    //a MediaPlayer Object to store and play the audio file
    private MediaPlayer miwokAudio;

    //creating an instance of the AudioManager Class
    AudioManager familyAudio;

    //creating an instance of onAudioFocusChangeListener to be supplied as argument to requestAudioFocus() method
    // to listen and notify us when Audio Focus is changed by calling onAudioFocusChange() method
    final AudioManager.OnAudioFocusChangeListener familyAudioFocus = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AUDIOFOCUS_GAIN)
                miwokAudio.start();
            else if (focusChange == AUDIOFOCUS_LOSS)
                releaseMediaPlayer();
            else if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
                miwokAudio.pause();
            miwokAudio.seekTo(0);
        }
    };

    //default constructor
    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //inflating the view with the words_list layout
        View rootView = inflater.inflate(R.layout.words_list,container,false);

        //finding the LinearLayout view which has the english and miwok TextViews
        //LinearLayout textLayout = (LinearLayout) findViewById(R.id.txtLayout);
        //setting the background of the above view
        // textLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.category_colors));
        //creating an array list of objects of the word class
        final ArrayList<Word> numbers = new ArrayList<Word>();

        //inserting the english and miwok words in @numbers array list of object of Word class
        numbers.add(new Word(getString(R.string.one),"lutti",R.drawable.number_one,R.raw.number_one));
        numbers.add(new Word(getString(R.string.two),"otiiko",R.drawable.number_two,R.raw.number_two));
        numbers.add(new Word(getString(R.string.three),"tolookosu",R.drawable.number_three,R.raw.number_three));
        numbers.add(new Word(getString(R.string.four),"oyyisa",R.drawable.number_four,R.raw.number_four));
        numbers.add(new Word(getString(R.string.five),"massokka",R.drawable.number_five,R.raw.number_five));
        numbers.add(new Word(getString(R.string.six),"temmokka",R.drawable.number_six,R.raw.number_six));
        numbers.add(new Word(getString(R.string.seven),"kenekaku",R.drawable.number_seven,R.raw.number_seven));
        numbers.add(new Word(getString(R.string.eight),"kawinta",R.drawable.number_eight,R.raw.number_eight));
        numbers.add(new Word(getString(R.string.nine),"wo’e",R.drawable.number_nine,R.raw.number_nine));
        numbers.add(new Word(getString(R.string.ten),"na’aacha",R.drawable.number_ten,R.raw.number_ten));

        //creating the WordAdapter which extends ArrayAdapter for the list view
        final WordAdapter numbersAdapter = new WordAdapter(getContext(),numbers,R.color.category_numbers);

        //the getSystemService is called to provide the audio service
        familyAudio = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        //finding the list view
        ListView numbers_list = (ListView) rootView.findViewById(R.id.list);

        //setting the WordAdapter for the list view
        numbers_list.setAdapter(numbersAdapter);

        //creating an onCompleteListener Object to with the implementation of the onCompletion method
        //to release the memory allocated to miwokAudio on Completion
        final MediaPlayer.OnCompletionListener oncompletelistener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                releaseMediaPlayer();
            }
        };


        //setting the onItemClickListener for the list item above
        numbers_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                    miwokAudio = MediaPlayer.create(getContext(), numbersAdapter.getItem(position).getnAudioSrc());

                    //play the miwok audio
                    miwokAudio.start();

                    //setting a onCompletionListener on the miwokAudio
                    miwokAudio.setOnCompletionListener(oncompletelistener);
                }
            }
        });

        return rootView;

    }

    @Override
    public void onStop() {
        super.onStop();
        // to release the memory allocated to any audio file
        //when the activity is being stopped or not seen by the user
        releaseMediaPlayer();
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
}