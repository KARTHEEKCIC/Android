package com.example.android.zailet;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by kartheek on 19/5/17.
 */

public class SearchActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleSearchViewIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleSearchViewIntent(getIntent());
    }

    public void handleSearchViewIntent(Intent intent) {
        if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
            Toast.makeText(SearchActivity.this,intent.getStringExtra(SearchManager.QUERY),Toast.LENGTH_LONG).show();
        }
    }
}
