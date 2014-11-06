package com.example.Ebotics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.Ebotics.Utils.Constants;
import com.example.Ebotics.Utils.SessionManager;

import java.util.HashMap;

/**
 * Created by lilian on 10/30/14.
 */
public class Login extends Activity implements View.OnClickListener{
    EditText etUserName, etPassword;
    Button btnLogin;
    private static String TAG = Login.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etUserName = (EditText) findViewById(R.id.etUserNameToLogin);
        etPassword = (EditText) findViewById(R.id.etPasswordToLogin);

         btnLogin = (Button) findViewById(R.id.buttonSignIn);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> userDetails = sessionManager.getUserDetails();

        String userName = userDetails.get(Constants.KEY_USER_NAME);
        String password = userDetails.get(Constants.KEY_HM_PASSWORD);

        switch (view.getId()){
            case R.id.buttonSignIn:
                if(etUserName.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()){
                    displayToastMessage("You cannot have empty Fields!!!");
                }else if(!etUserName.getText().toString().equals(userName) ||
                        !etPassword.getText().toString().equals(password)){
                    displayToastMessage("Please the credentials you registered with!!!");
                }else{
                    startActivity(new Intent(getApplicationContext(), Robotics.class));
                }
                break;
            default:
                Log.e(TAG, "We have a problem!!!");
                break;
        }

        }

    /**
     * @param message Message to be displayed
     */
    public void displayToastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    }
