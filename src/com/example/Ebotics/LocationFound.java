package com.example.Ebotics;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

/**
 * Created by lilian on 11/19/14.
 */
public class LocationFound extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_found);

        ActionBar actionBar = getActionBar();

// Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);

// actionBar.setIcon(R.drawable.ico_actionbar);
    }
}