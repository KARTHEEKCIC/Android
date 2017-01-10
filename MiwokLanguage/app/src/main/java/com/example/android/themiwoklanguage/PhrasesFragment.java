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
public class PhrasesFragment extends Fragment {

    //a MediaPlayer Object to store and play the audio file
    private MediaPlayer miwokAudio;

    //creating an instance of the AudioManager Class
    //the getSystemService is called to provide the audio service
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




    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflating the words_list layout xml file
        View rootView = inflater.inflate(R.layout.words_list,container,false);
        //creating an array list of objects of the word class
        ArrayList<Word> numbers = new ArrayList<Word>();

        //inserting the english and miwok words in @numbers array list of object of Word class
        numbers.add(new Word("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going));
        numbers.add(new Word("What is your name?","tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        numbers.add(new Word("My name is...","oyaaset...",R.raw.phrase_my_name_is));
        numbers.add(new Word("How are you feeling?","michәksәs?",R.raw.phrase_how_are_you_feeling));
        numbers.add(new Word("I’m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        numbers.add(new Word("Are you coming?","әәnәs'aa?",R.raw.phrase_are_you_coming));
        numbers.add(new Word("Yes, I’m coming.","hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        numbers.add(new Word("I’m coming.","әәnәm",R.raw.phrase_im_coming));
        numbers.add(new Word("Let’s go.","yoowutis",R.raw.phrase_lets_go));
        numbers.add(new Word("Come here.","әnni'nem",R.raw.phrase_come_here));

        //creating the WordAdapter which extends ArrayAdapter for the list view
        final WordAdapter phrasesAdapter = new WordAdapter(getActivity(),numbers,R.color.category_phrases);

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

        //finding the list view
        ListView phrases_list = (ListView) rootView.findViewById(R.id.list);

        //setting the onItemClickListener for the list item above
        phrases_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                    //requesting the Audio Focus for the audio

                    miwokAudio = MediaPlayer.create(getContext(), phrasesAdapter.getItem(position).getnAudioSrc());

                    //play the miwok audio
                    miwokAudio.start();

                    //setting a onCompletionListener on the miwokAudio
                    miwokAudio.setOnCompletionListener(oncompletelistener);
                }

            }
        });

        //setting the WordAdapter for the list view
        phrases_list.setAdapter(phrasesAdapter);

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
