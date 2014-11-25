package com.example.Ebotics;

import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.example.Ebotics.Swipeables.SimpleGestureFilter;

/**
 * Created by lilian on 10/15/14.
 */
public class Robotics extends Activity implements SimpleGestureFilter.SimpleGestureListener {

    //declaring variables
    TextView tvrequire,mSearchText;
    private MenuItem myActionMenuItem;
    private EditText myActionEditText;

    //declaring variables for the zooming effect
    private ImageView imageViewOne;
    private ScaleGestureDetector scaleGestureDetector;
    private Matrix matrix = new Matrix();
    public SimpleGestureFilter detector;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.robotics);

        // Detect touched area
        detector = new SimpleGestureFilter(this,this);

        //initializing variables
        imageViewOne = (ImageView)findViewById(R.id.imageViewOne);
        scaleGestureDetector = new ScaleGestureDetector(this,new ScaleListener());

        tvrequire = (TextView)findViewById(R.id.tvrequire);
        //Typeface custom_font = Typeface.createFromAsset(getAssets(),"fonts/HalleyveticaNBP.ttf");
        //tvrequire.setTypeface(custom_font);

    }

//This method helps zoom in and out.
    @Override
    public boolean onTouchEvent(MotionEvent motionevent) {
        scaleGestureDetector.onTouchEvent(motionevent);
        if(motionevent.getAction() == MotionEvent.ACTION_DOWN) {
            toggleActionBar();

    }return true;
    }


    public boolean onQueryTextSubmit(String s) {
        return false;
    }


    public boolean onQueryTextChange(String s) {
        return false;
    }

    @Override
    public void onSwipe(int direction) {

    }

    @Override
    public void onDoubleTap() {

    }

    public  class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            float scaleFactor = detector.getScaleFactor();
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));
            matrix.setScale(scaleFactor, scaleFactor);
            imageViewOne.setImageMatrix(matrix);
            return true;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.robotics, menu);
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



    void displayToast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }



    private void toggleActionBar() {
        ActionBar actionBar = getActionBar();
        if(actionBar != null) {
            if(actionBar.isShowing()) {
                actionBar.hide();
            }
            else {
                actionBar.show();
            }
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent me){
        // Call onTouchEvent of SimpleGestureFilter class
        this.detector.onTouchEvent(me);
        return super.dispatchTouchEvent(me);
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
                Intent i = new Intent(Robotics.this, LocationFound.class);
                startActivity(i);
                return true;
            case R.id.action_video:
// refresh
                Intent vid = new Intent(Robotics.this, VideoActivity.class);
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

    /*@Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if(keyEvent != null) {
            // When the return key is pressed, we get the text the user entered, display it and collapse the view
            if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                CharSequence textInput = textView.getText();
                // Do something useful with the text
                Toast.makeText(this, textInput, Toast.LENGTH_SHORT).show();
                MenuItem.collapseActionView(myActionMenuItem);
                //MenuItemCompat.collapseActionView(myActionMenuItem);
            }
        }
        return false;
    }*/
