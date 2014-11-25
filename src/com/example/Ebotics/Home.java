package com.example.Ebotics;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.example.Ebotics.Expandables.ExpandableListAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lilian on 10/8/14.
 */



public class Home extends Activity  {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_list);


        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
       public boolean onGroupClick(ExpandableListView parent, View v,int groupPosition, long id) {
        Toast.makeText(getApplicationContext(),"Group Clicked " + listDataHeader.get(groupPosition),
                Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
      public boolean onChildClick(ExpandableListView parent,View v,int groupPosition, int childPosition, long id) {

                Toast.makeText(getApplicationContext(),listDataHeader.get(groupPosition)+ " : "+ listDataChild.get(
                                listDataHeader.get(groupPosition)).get( childPosition), Toast.LENGTH_SHORT).show();

              String item=parent.getItemAtPosition(childPosition).toString();
                switch(groupPosition){
                    //intent for robotics
                    case 0:
                        switch(childPosition){
                            case 0:
                                startActivity(new Intent(getApplicationContext(),Robotics.class));
                                break;
                            case 1:
                                startActivity(new Intent(getApplicationContext(),VideoActivity.class));
                                break;
                            case 2:

                                break;
                            case 3:

                                break;

                            case 4:

                                break;

                        }
                        //intent for entrepreneur
                    case 1:
                        switch(childPosition){
                            case 0:

                                break;
                            case 1:

                                break;
                            case 2:

                                break;
                            case 3:

                                break;
                            case 4:

                                break;

                        }
                        //intent for ask a teacher
                    case 2:
                        switch(childPosition){
                            case 0:

                                break;
                            case 1:

                                break;
                            case 2:

                                break;
                            case 3:

                                break;
                            case 4:

                                break;

                        }
                }
                return true;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        MenuItem item = menu.findItem(R.id.action_search);

        getMenuInflater().inflate(R.menu.main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;

    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Robotics");
        listDataHeader.add("Entrepreneur");
        listDataHeader.add("Contests");
        listDataHeader.add("Ask a Teacher");

        // Adding child data
        List<String> Robotics = new ArrayList<String>();
        Robotics.add("Steps of creating  a simple Robot");
        Robotics.add("Understanding Robotics");
        Robotics.add("How to solder");
        Robotics.add("Fixing materials ");
        Robotics.add("Understanding and using motors");
        Robotics.add("Familiarising with batteries");
        Robotics.add("Robotics Technology");

        List<String> Entrepreneur = new ArrayList<String>();
        Entrepreneur.add("Understanding Entrepreneurship");
        Entrepreneur.add("Thinking for a solution");
        Entrepreneur.add("Thinking outside the box");
        Entrepreneur.add("How to manage money");
        Entrepreneur.add("Getting up from your failures");
        Entrepreneur.add("Being a successful entrepreneur");

        List<String> Contests = new ArrayList<String>();
        Contests.add("Questions");
        Contests.add("Group Contests");
        Contests.add("View Points");

        List<String> AskTeacher = new ArrayList<String>();
        AskTeacher.add("Send a message");
        AskTeacher.add("Call Teacher");
        AskTeacher.add("Chat with Teacher");


        listDataChild.put(listDataHeader.get(0), Robotics); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Entrepreneur);
        listDataChild.put(listDataHeader.get(2), Contests);
        listDataChild.put(listDataHeader.get(3), AskTeacher);
    }
}