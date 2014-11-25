package com.example.Ebotics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.TextView;


/**
 * Created by lilian on 11/14/14.
 */
public class VideoActivity extends Activity implements View.OnClickListener {

    //declaring variables
    WebView wbtutor;
    Button btn_view;
    TextView tvvid_step1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videos);

        //initializing variables

        btn_view=(Button)findViewById(R.id.btn_view);
        btn_view.setOnClickListener(this);

        wbtutor=(WebView)findViewById(R.id.wbtutor);
        tvvid_step1=(TextView)findViewById(R.id.tvvid_step1);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.videos, menu);
        MenuItem item = menu.findItem(R.id.menu_item_share);

        getMenuInflater().inflate(R.menu.main, menu);

        ShareActionProvider myShareActionProvider = (ShareActionProvider) item.getActionProvider();
        Intent myIntent = new Intent();
        myIntent.setAction(Intent.ACTION_SEND);
        myIntent.putExtra(Intent.EXTRA_TEXT, "Whatever message you want to share");
        myIntent.setType("text/plain");
        myShareActionProvider.setShareIntent(myIntent);

        return true;
    }


        @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_view:

                //streaming a youtube video by the click of a button
                wbtutor.getSettings().setJavaScriptEnabled(true);
                wbtutor.loadUrl("http://www.youtube.com/watch?v=8H4Y1TF4p4c");

                break;
            default:
                break;
        }

    }
}