package com.example.Ebotics;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

/**
 * Created by lilian on 10/8/14.
 */
public class Home extends Activity {
    ListView list;
    TextView tvhometxt;
    String[] Subject = {
            "Robotics",
            "Entreprenuer",
            "Contest",
            "View Points",
    } ;
    Integer[] imageId = {
            R.drawable.robot_female,
            R.drawable.money_symbol,
            R.drawable.child,
            R.drawable.application,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        CustomList adapter = new  CustomList(Home.this,Subject, imageId);
        //Typeface typeface = Typeface.createFromAsset(getAssets(),"roboto_regular.ttf");
        //tvhometxt.setTypeface(typeface);

        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String ListData = list.getItemAtPosition(position).toString();
                switch (position) {
                    case 0:
                        startActivity(new Intent(getApplicationContext(),Robotics.class));
                        break;

                    case 1:
                        startActivity(new Intent(getApplicationContext(),Entreprenuer.class));
                        break;

                    default:
                        break;

                }


                Toast.makeText(Home.this, "You Clicked at " + Subject[+position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}