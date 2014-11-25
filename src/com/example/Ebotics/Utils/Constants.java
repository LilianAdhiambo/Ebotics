package com.example.Ebotics.Utils;

/**
 * Created by lilian on 10/30/14.
 */
public class Constants {

    public static final int DELAY_TIME = 2500;


    // Testing in localhost using wamp or xampp
    // use http://10.0.2.2/ to connect to your localhost ie http://localhost/
    public static final String LOCALHOST_URL = "http://192.168.44.52/android_api/";

    public static final String LOGIN_TAG = "login";
    public static final String REGISTER_TAG = "register";

    //Shared Preferences Keys
    public static final String PREFS_NAME = "myPrefs";
    public static final String KEY_USER_NAME = "userName";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_STATE = "state";

    //HashMap Keys
    public static final String KEY_HM_USER_NAME = "userName";
    public static final String KEY_HM_PASSWORD = "password";

    // JSON Response node names
    public static String KEY_JSON_SUCCESS = "success";
    public static String KEY_JSON_ERROR = "error";
    public static String KEY_JSON_ERROR_MSG = "error_msg";
    public static String KEY_JSON_UID = "uid";
    public static String KEY_JSON_USER_NAME = "username";
    public static String KEY_JSON_PASSWORD = "password";
    public static String KEY_JSON_CREATED_AT = "created_at";
}
