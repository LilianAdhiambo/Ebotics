package com.example.Ebotics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.Toast;

/**
 * Created by lilian on 10/15/14.
 */
public class Robotics extends Activity implements View.OnClickListener{

    Button btnLinkToentre;
    private ShareActionProvider mShareActionProvider;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.robotics);

        btnLinkToentre=(Button)findViewById(R.id.btnLinkToentre);
        btnLinkToentre.setOnClickListener(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu resource file.
        getMenuInflater().inflate(R.menu.robotics, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) item.getActionProvider();

        // Return true to display menu
        return true;
    }

    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }




    void displayToast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }




    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnLinkToentre:
                startActivity(new Intent(getApplicationContext(),Entreprenuer.class));
                break;
        }

    }
}