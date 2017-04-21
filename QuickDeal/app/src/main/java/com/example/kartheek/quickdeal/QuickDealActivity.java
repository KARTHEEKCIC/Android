package com.example.kartheek.quickdeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuickDealActivity extends AppCompatActivity {

    private GridView gridView;

    private CategoryAdapter adapter;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_deal);

        //finding the grid view to set the adapter to it
        gridView = (GridView) findViewById(R.id.gridview);

        //creating the custom adapter by the feeding the data obtained above
        adapter = new CategoryAdapter(getApplicationContext(),0,createGridItems());

        //setting the adapter to the grid view
        gridView.setAdapter(adapter);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_filter);
        //setting the onclick listener on the Navigation icon on the left side of the Toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "For opening settings menu", Toast.LENGTH_SHORT).show();
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



    public List<CategoryItem> createGridItems() {
        // List to store the data required to display on the grid
        List<CategoryItem> categoryList = new ArrayList<CategoryItem>();
        // Resource id of all the images required in the grids
        int[] categoryImages = {R.drawable.ic_appareel,R.drawable.ic_auto,R.drawable.ic_electronics,R.drawable.ic_hardware,R.drawable.ic_appareel,R.drawable.ic_auto};
        // category names to be displayed on the grid
        String[] categories = getApplicationContext().getResources().getStringArray(R.array.categories);

        // loop to add the objects of CategoryItem which contains the data of each grid to the list
        for(int i=0 ; i<categories.length ; i++) {
                categoryList.add(new CategoryItem(categories[i],categoryImages[i]));
        }
        return categoryList;
    }
}