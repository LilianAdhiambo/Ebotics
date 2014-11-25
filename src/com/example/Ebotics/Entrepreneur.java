package com.example.Ebotics;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.ShareActionProvider;

/**
 * Created by lilian on 11/10/14.
 */
public class Entrepreneur extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entreprenuer);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.entrepreneur, menu);
        MenuItem item = menu.findItem(R.id.menu_item_share);

        getMenuInflater().inflate(R.menu.main, menu);

        ShareActionProvider myShareActionProvider = (ShareActionProvider) item.getActionProvider();
        Intent myIntent = new Intent();
        myIntent.setAction(Intent.ACTION_SEND);
        myIntent.putExtra(Intent.EXTRA_TEXT, "Whatever message you want to share");
        myIntent.setType("text/plain");
        myShareActionProvider.setShareIntent(myIntent);


        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_search:
           // search action
                return true;
            case R.id.action_location_found:
           // location found
                Intent i = new Intent(Entrepreneur.this, LocationFound.class);
                startActivity(i);
                return true;
            case R.id.action_video:
          // refresh
                Intent vid = new Intent(Entrepreneur.this, VideoActivity.class);
                startActivity(vid);
                return true;
            case R.id.action_help:
           // help action
                return true;
            case R.id.action_check_updates:
// check for updates action
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}