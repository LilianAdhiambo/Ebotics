package com.example.Ebotics;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by lilian on 10/13/14.
 */
public class Tabs extends TabActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);

        //instantiating the tab host
        Resources resources=getResources();
        TabHost tabhost=getTabHost();


        Intent robotsActivity = new Intent().setClass(getApplicationContext(), Robotics.class);
        TabHost.TabSpec robotstabspec = tabhost.newTabSpec("Robotics").setIndicator("Robotics", resources.getDrawable(R.drawable.ic_launcher))//setting UI for a specific tab
                .setContent(robotsActivity);

        Intent entreprenuer = new Intent().setClass(getApplicationContext(),Entreprenuer.class);
        TabHost.TabSpec enterTabspec = tabhost.newTabSpec("Entreprenuer").setIndicator("Entreprenuer",resources.getDrawable(R.drawable.ic_launcher))
                .setContent(entreprenuer);


        tabhost.addTab(robotstabspec);
        tabhost.addTab(enterTabspec);

    }
}