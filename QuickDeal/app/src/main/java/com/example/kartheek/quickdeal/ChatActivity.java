package com.example.kartheek.quickdeal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.kartheek.quickdeal.R.id.toolbar;

public class ChatActivity extends AppCompatActivity {

    private Toolbar chatToolbar;
    private ListView gridLayout;
    static List<ChatItem> ls;
    static ChatAdapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("ChatActivity","Entering chat list");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chatToolbar = (Toolbar) findViewById(toolbar);
        setSupportActionBar(chatToolbar);
        chatToolbar.setNavigationIcon(R.drawable.moveback);

        gridLayout = (ListView) findViewById(R.id.chat_list);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Deals",MODE_PRIVATE);
        if(sharedPreferences.getInt("position_size",0) == 0){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("position_size",0);
        }

        //data set for the adapter
        ls = createChatListItems();

        //checking for deals and updating the data set
        checkDeals(sharedPreferences);
        adp = new ChatAdapter(getApplicationContext(),0,ls);
        gridLayout.setAdapter(adp);
        adp.notifyDataSetChanged();
    }

    public void checkDeals(SharedPreferences sharedPreferences) {
        String position = "position";
        String deals = "deals";
        int length = sharedPreferences.getInt(position + "_size",0);
        for(int i=0;i<length;i++) {
            ls.get(sharedPreferences.getInt(position + "_" + i,0)).setmDealDone(sharedPreferences.getBoolean(deals + "_" + i,true));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater chatMenuInflater = getMenuInflater();
        chatMenuInflater.inflate(R.menu.chat_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home) {
            // so that the activity trigered it is show instead of any particular parent activity
            finish();
        } else if(id == R.id.search) {
            Toast.makeText(getApplicationContext(),"Search for the chat",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public List<ChatItem> createChatListItems() {
        List<ChatItem> list = new ArrayList<ChatItem>();

        // titles of the chat list
        String [] chatTitle = getApplicationContext().getResources().getStringArray(R.array.chat_list_title);
        // number of unread messages of the chats
        int [] chatUnreadMessages = getApplicationContext().getResources().getIntArray(R.array.chat_list_unread_messages);
        // body of the chats
        String [] chatBody = getApplicationContext().getResources().getStringArray(R.array.chat_list_body);
        // time when the last message came
        String [] chatTime = getApplicationContext().getResources().getStringArray(R.array.chat_list_time);

        // loop to add the objects of ChatItem which contains the data of each chat to the list
        for(int i=0 ; i<chatBody.length ; i++) {
            Log.e("ChatActivity",chatUnreadMessages[i] + "");
            list.add(new ChatItem(chatTitle[i],chatUnreadMessages[i],chatBody[i],chatTime[i],false));
        }
        return list;
    }
}
