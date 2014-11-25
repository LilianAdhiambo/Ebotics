package com.example.Ebotics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * Created by lilian on 10/13/14.
 */
public class SplashScreen extends Activity {

    //swapping the splash screen
    Float x1,x2;
    float y1,y2;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);


        Thread logoTimer=new Thread(){
            public void run(){
                try{
                    int logoTimer=0;
                    while(logoTimer<2000){
                        sleep(100);
                        logoTimer=logoTimer+100;
                    };
                     startActivity(new Intent(getApplicationContext(),Register.class));
                }

                catch (InterruptedException e){
                    e.printStackTrace();
                }

                finally {
                    finish();
                }
            }

        };
   logoTimer.start();
    }



    // onTouchEvent () method gets called when User performs any touch event on screen
    // Method to handle touch event like left to right swap and right to left swap
    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            // when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN: {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP: {
                x2 = touchevent.getX();
                y2 = touchevent.getY();

                break;
            }
        }
        return false;
    }
}