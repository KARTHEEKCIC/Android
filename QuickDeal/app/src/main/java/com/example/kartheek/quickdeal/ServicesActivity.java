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

import java.util.ArrayList;
import java.util.List;

public class ServicesActivity extends AppCompatActivity {

    private ServicesAdapter adp;

    private ListView ls;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.moveback);
        //setting the onclick listener on the Navigation icon on the left side of the Toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(ServicesActivity.this);
            }
        });

        adp = new ServicesAdapter(getApplicationContext(),0,createListItems());
        ls = (ListView) findViewById(R.id.service_list);
        ls.setAdapter(adp);
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

    public List<ServicesItem> createListItems() {
        // List to store the data required to display on the grid
        List<ServicesItem> categoryList = new ArrayList<ServicesItem>();
        // names of the service providers
        String[] ServicePovider = getApplicationContext().getResources().getStringArray(R.array.serviceprovider);
        // service provider location to be displayed on the list
        String[] location = getApplicationContext().getResources().getStringArray(R.array.location);

        // loop to add the objects of CategoryItem which contains the data of each grid to the list
        for(int i=0 ; i<location.length ; i++) {
            categoryList.add(new ServicesItem(ServicePovider[i],location[i]));
        }
        return categoryList;
    }
}
