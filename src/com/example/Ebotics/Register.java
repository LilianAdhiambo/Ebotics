package com.example.Ebotics;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
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

/**
 * Created by lilian on 10/5/14.
 */
public class Register extends Activity implements View.OnClickListener {

    EditText etUserName, etPassword, etConfirmPassword;
    Button buttonLinkLogin,btnregister;
    Constants myConstants;
    SessionManager sessionManager;
    user userModel;

    private static final String TAG = Register.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        ActionBar actionBar = getActionBar();

// Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);

// actionBar.setIcon(R.drawable.ico_actionbar);

        userModel = new user();

        myConstants = new Constants();
        sessionManager = new SessionManager(getApplicationContext());

        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);

        Button register = (Button) findViewById(R.id.btnRegister);
        register.setOnClickListener(this);
        Button btnlinkLogin = (Button) findViewById(R.id.btnLinkLogin);
        btnlinkLogin.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register, menu);
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
            case R.id.btnRegister:
                /**
                 * Here we do some data Validation. You can do it better than this. I am just showing
                 * you the basic flow.
                 */

                //Check if the data is null
                if (etPassword.getText().toString().isEmpty()|| etUserName.getText().toString().isEmpty()) {
                    displayToastMessage("You cannot have blank fields!!");

                } else if (!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
                    displayToastMessage("Passwords Do not Match!!!");
                } else {
                    userModel.setPassword(etPassword.getText().toString());
                    userModel.setUserName(etUserName.getText().toString());
                    RegisterAsyncTask task = new RegisterAsyncTask();
                    task.execute();
                }
                break;
            case R.id.btnLinkLogin:
                startActivity(new Intent(getApplicationContext(),Login.class));
                break;
            default:
                Log.e(TAG, "unable to proceed!!!");
                break;
        }
    }

    /**
     * This class enables us to perform long operation in the background without affecting the UI.
     * <p>Title: Parameters passed in the AsyncTask.<p/>
     * 1. Params: Parameters sent to the background task for execution.
     * 2. Progress: Display when the task is happening
     * 3. Result: This is the type of result we expect once the background task is completed
     * <p/>
     * For more information check {@link android.os.AsyncTask}
     */
    private class RegisterAsyncTask extends AsyncTask<String, Void, JSONObject> {

        ProgressDialog progressDialog;


        /**
         * Invoked on the UI thread before the task is executed.
         * This step is normally used to setup the task,
         * for instance by showing a progress bar in the user interface.
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //Setup the progressBar
            progressDialog = new ProgressDialog(Register.this);
            progressDialog.setMessage("Please Wait....");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        /**
         * Invoked on the background thread immediately after onPreExecute() finishes executing.
         * This step is used to perform background computation that can take a long time.
         *
         * @param params URL
         * @return JsonObject
         */
        @Override
        protected JSONObject doInBackground(String... params) {

            UserFunctions userFunction = new UserFunctions();

            return userFunction.registerUser( userModel.getUserName(), userModel.getPassword());
        }


        /**
         * Invoked on the UI thread after the background computation finishes.
         *
         * @param jsonObjectResult Result from the background task
         */
        @Override
        protected void onPostExecute(JSONObject jsonObjectResult) {
            super.onPostExecute(jsonObjectResult);
            /**
             * Check if the dialog is showing and dismiss it.
             */
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            // check for login response
            try {
                if (jsonObjectResult.getString(Constants.KEY_JSON_SUCCESS) != null) {

                    String res = jsonObjectResult.getString(Constants.KEY_JSON_SUCCESS);
                    if (Integer.parseInt(res) == 1) {

                        JSONObject jsonObject = jsonObjectResult.getJSONObject("user");
                        /**
                         * user successfully registered
                         * Store user details in SQLite Database
                         */
                        DatabaseHandler db = new DatabaseHandler(getApplicationContext());


                        userModel.setUserName(jsonObject.getString(Constants.KEY_JSON_USER_NAME));

                        db.addUserDetails(userModel);

                        // Launch  LoginScreen
                        Intent loginIntent = new Intent(getApplicationContext(), Login.class);
                        // Close all views before launching the intent
                        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(loginIntent);

                        displayToastMessage("Registration was Successful");
                        // Close Registration Screen
                        finish();
                    } else {
                        // Error in registration
                        displayToastMessage("Error occurred in registration");
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e(TAG, "@onPostExecute JSONException Message: " + e.getMessage());
            } catch (NullPointerException np){
                Log.e(TAG, "@onPostExecute NullPointerException Message: " + np.getMessage());
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