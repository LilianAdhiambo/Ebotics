package com.example.Ebotics.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.example.Ebotics.Login;

import java.util.HashMap;

/**
 * Created by lilian on 10/30/14.
 */
public class SessionManager {


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context _context;

    //Constructor
    public SessionManager(Context context) {
        this._context = context;
        sharedPreferences = _context.getSharedPreferences(Constants.PREFS_NAME, 0);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    /**
     * This method stores data in the shared preferences
     *me Users names
     * @param userName User Name
     * @param email    User Email
     * @param password User Password
     */
    public void createUser(String fullName, String userName, String email, String password) {

        //Store Data in the shared preferences
        editor.putString(Constants.KEY_USER_NAME, userName);
        editor.putString(Constants.KEY_EMAIL, email);
        editor.putString(Constants.KEY_PASSWORD, password);
        editor.putBoolean(Constants.KEY_STATE, false);

        editor.commit();
    }

    /**
     * This method gets user details. This will be used to login in.
     *
     * @return Data for the shared preferences
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(Constants.KEY_HM_USER_NAME, sharedPreferences.getString(Constants.KEY_USER_NAME, null));
        user.put(Constants.KEY_HM_PASSWORD, sharedPreferences.getString(Constants.KEY_PASSWORD, null));

        return user;
    }

    /**
     * This method clears data from the shared preferences and starts the Login Activity
     */
    public void logoutUser() {
        editor.clear();
        editor.commit();

        //Start Login in Activity
        _context.startActivity(new Intent(_context, Login.class));
    }

    /**
     * This method checks if the user has logged in.
     *
     * @return state
     */
    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(Constants.KEY_STATE, false);
    }

    /**
     * This method is used to set the state when the user logs in.
     *
     * @param loginState Boolean value
     */
    public void setLoginState(boolean loginState){
        editor.putBoolean(Constants.KEY_STATE, loginState);
        editor.apply();
    }

}


