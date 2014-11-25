package com.example.Ebotics;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.Ebotics.Database.DatabaseHandler;
import com.example.Ebotics.Models.user;
import com.example.Ebotics.Utils.Constants;
import com.example.Ebotics.Utils.SessionManager;
import com.example.Ebotics.Utils.UserFunctions;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

/**
 * Created by lilian on 10/30/14.
 */
public class Login extends Activity implements View.OnClickListener {

    private static String TAG = Login.class.getSimpleName();
    EditText etUserName, etPassword;
    Button btnLogin, buttonLinkRegister;
    user userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        ActionBar actionBar = getActionBar();

// Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
// actionBar.setIcon(R.drawable.ico_actionbar);

        userModel = new user();

        etUserName = (EditText) findViewById(R.id.etUserNameToLogin);
        etPassword = (EditText) findViewById(R.id.etPasswordToLogin);

        Button btnLogin = (Button) findViewById(R.id.buttonSignIn);
        btnLogin.setOnClickListener(this);
        Button btnlinkregister = (Button) findViewById(R.id.btnLinkRegister);
        btnlinkregister.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSignIn:
                if (etUserName.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()) {
                    displayToastMessage("You cannot have empty Fields!!!");
                } else {
                    LoginAsyncTask loginTask = new LoginAsyncTask();
                    loginTask.execute();
                }
                break;
            case R.id.btnLinkRegister:
                startActivity(new Intent(getApplicationContext(), Home.class));
                break;
            default:
                Log.e(TAG, "We have a problem.!!!");
                break;
        }
    }

    private class LoginAsyncTask extends AsyncTask<String, Void, JSONObject> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(Login.this);
            progressDialog.setMessage("Logging in ....");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... params) {

            UserFunctions userFunction = new UserFunctions();
            return userFunction.loginUser(userModel.getUserName(), userModel.getPassword());
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);

            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }

            try {
                if (jsonObject.getString(Constants.KEY_JSON_SUCCESS) != null) {
                    String res = jsonObject.getString(Constants.KEY_JSON_SUCCESS);
                    if(Integer.parseInt(res) == 1){
                        // user successfully logged in
                        // Store user details in SQLite Database
                        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                        JSONObject json_user = jsonObject.getJSONObject("user");

                        userModel.setUserName(json_user.getString(Constants.KEY_JSON_USER_NAME));
                        userModel.setPassword(json_user.getString(Constants.KEY_JSON_PASSWORD));

                        // Clear all previous data in database
                        db.addUserDetails(userModel);

                        // Launch Home Screen
                        Intent intentHomeScreen = new Intent(getApplicationContext(), Home.class);

                        // Close all views before launching Dashboard
                        intentHomeScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentHomeScreen);

                        displayToastMessage("Welcome");
                        // Close Login Screen
                        finish();
                    }else{
                        // Error in login
                        displayToastMessage("Incorrect username/password");
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * @param message Message to be displayed
     */
    public void displayToastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}