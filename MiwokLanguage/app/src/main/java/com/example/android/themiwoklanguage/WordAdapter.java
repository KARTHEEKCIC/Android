package com.example.android.themiwoklanguage;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kartheek on 24/12/16.
 * a class which extends the ArrayAdapter class to override the method getView() to handle layout
 * files with view groups
 */

public class WordAdapter extends ArrayAdapter<Word>{

    //a variable to store the Resource id for backgorund color for the textviews in list item
    private int nColorResourceId;

    @NonNull
    //over writing the getview class to handle layout with more than one view
    @Override
    //@param position is the position of the data which the user is currently viewing and is to displayed
    //@param convertView is the view to be recycled and used again to display the new data
    //@param parent is the parent view group (@ListView words_list.xml in this case) to which the list items are to be added
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        //checking if there is any previously used view
        if(listItem==null)
        {
            //inflating a new layout from list_item as there is no previous layout to use
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        //get the words object at the given position from the given data source
        Word words = getItem(position);

        //finding the textview storing the english translation in the listitem Xml file
        TextView english = (TextView) listItem.findViewById(R.id.eng_word);
        //setting the text for the english translation in the list item
        english.setText(words.getEnglishTranslation());

        //finding the textview storing the miwok translation in the listitem Xml file
        TextView miwok =  (TextView) listItem.findViewById(R.id.miwok_word);
        //setting the text for the miwok translation in the list item
        miwok.setText(words.getMiwokTranslation());

        //finding the ImageView storing the image
        ImageView img = (ImageView) listItem.findViewById(R.id.image);
        //checking whether image is supplied or not
        if(words.getImgSrc()!=View.GONE) {
            //setting the image source
            img.setImageResource(words.getImgSrc());
            //setting the view to be visible if by chance it's not visible previously
            img.setVisibility(View.VISIBLE);
        }
        else
        {
            //setting the ImageView to display no image and not to be present in the view
            img.setVisibility(View.GONE);
        }

        //finding the LinearLayout which contain the english and miwok translations
        LinearLayout txtLayout = (LinearLayout) listItem.findViewById(R.id.txtLayout);
        //setting the background color for the layout above
        txtLayout.setBackgroundResource(nColorResourceId);

        //return the list item to be added to the list view
        return listItem;
    }

    //public constructor with context of the current activity and array list of data as arguments
    public WordAdapter(Context context, ArrayList<Word> word, int colorResourceId)
    {
        //the parameterized constructor of the ArrayAdapter class
        //context of the current activity as argument
        //the resource file is not required as argument as we have used it directly in the modified version of getView()
        //word is the list of words of english and miwok translation passed as arguments
        super(context,0,word);
        nColorResourceId=colorResourceId; }

}
