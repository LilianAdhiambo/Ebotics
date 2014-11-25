package com.example.Ebotics.Utils;

import android.content.Context;
import com.example.Ebotics.Database.DatabaseHandler;
import com.example.Ebotics.Networks.JSONParser;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lilian on 11/14/14.
 */
public class UserFunctions {

    private JSONParser jsonParser;

    // constructor
    public UserFunctions() {
        jsonParser = new JSONParser();
    }

    /**
     * function make Login Request
     *
     * @param userName    username
     * @param password Password
     */
    public JSONObject loginUser(String userName, String password) {
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", Constants.LOGIN_TAG));
        params.add(new BasicNameValuePair("username", userName));
        params.add(new BasicNameValuePair("password", password));

        /**
         * Returns a JSON Object
         */
        return jsonParser.getJSONFromUrl(Constants.LOCALHOST_URL, params);
    }

    /**
     * function make Register Request
     *
     * @param userName Users userName
     * @param password Password
     */
    public JSONObject registerUser (String userName, String password) {
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", Constants.REGISTER_TAG));
        params.add(new BasicNameValuePair("userName", userName));
        params.add(new BasicNameValuePair("password", password));

        // getting JSON Object
        // return json
        return jsonParser.getJSONFromUrl(Constants.LOCALHOST_URL, params);
    }

    /**
     * Function get Login status for
     */
    public boolean isUserLoggedIn(Context context) {
        DatabaseHandler db = new DatabaseHandler(context);
        int count = db.getRowCount();
        return count > 0;
    }

    /**
     * Function to logout user
     * Reset Database
     */
    public boolean logoutUser(Context context) {
        DatabaseHandler db = new DatabaseHandler(context);
        db.resetTables();
        return true;
    }
}