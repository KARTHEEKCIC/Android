package com.example.kartheek.quickdeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ElectronicActivity extends AppCompatActivity {

    private ElectronicAdapter adp;

    private ListView ls;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic);
        String[] categories = getApplicationContext().getResources().getStringArray(R.array.electronic_categories);
        adp = new ElectronicAdapter(getApplicationContext(),0,categories);
        ls = (ListView) findViewById(R.id.electronic_list);
        ls.setAdapter(adp);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.moveback);
        //setting the onclick listener on the Navigation icon on the left side of the Toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(ElectronicActivity.this);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //getting the item id
        int id = item.getItemId();
        //checking for the menu item
        if(id == R.id.chat) {
            Intent chatIntent = new Intent(getApplicationContext(),ChatActivity.class);
            startActivity(chatIntent);
        } else if(id == R.id.login) {
            Toast.makeText(getApplicationContext(),"For User Login",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}
