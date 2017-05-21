package com.example.android.zailet;

import android.app.SearchManager;
import android.content.Context;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

public class MainActivity extends AppCompatActivity //implements SearchView.OnQueryTextListener
{

    SearchView searchView;
    MatrixCursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //inflating the search view in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.isSubmitButtonEnabled();
        //customizing the search bar
//        int SearchPlateId = searchView.getContext().getResources().getIdentifier("@android:id/search_plate",null,null);
//        View SearchPlate = searchView.findViewById(SearchPlateId);
//        if(SearchPlate != null) {
//            SearchPlate.setBackgroundColor(getResources().getColor(android.R.color.white));
//            int SearchTextId = SearchPlate.getContext().getResources().getIdentifier("@android:id/search_src_text",null,null);
//            TextView SearchText = (TextView) SearchPlate.findViewById(SearchTextId);
//            if(SearchText!=null) {
//                SearchText.setTextColor(getResources().getColor(android.R.color.darker_gray));
//            }
//        }

        return true;
    }


//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        String Query = newText;
//        LoginActivity.type = "Suggestions";
//        new CustomAsyncTask(getApplicationContext()).execute(Query);
//        String[] columnNames = {"Title","Topic"};
//        cursor = new MatrixCursor(columnNames);
//        cursor.addRow(Suggestions);
//        return false;
//    }
}
