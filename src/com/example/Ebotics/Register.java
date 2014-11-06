package com.example.Ebotics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.Ebotics.Models.user;
import com.example.Ebotics.Utils.Constants;
import com.example.Ebotics.Utils.SessionManager;

/**
 * Created by lilian on 10/5/14.
 */
public class Register extends Activity implements View.OnClickListener {

    EditText etUserName, etPassword, etConfirmPassword;

    Constants myConstants;
    SessionManager sessionManager;

    private static final String TAG = Register.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        myConstants = new Constants();
        sessionManager = new SessionManager(getApplicationContext());

        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);

        Button register = (Button) findViewById(R.id.btnRegister);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegister:
                user userModel = new user();
                userModel.setPassword(etPassword.getText().toString());
                userModel.setUserName(etUserName.getText().toString());

                //Check if the data is null
                if (userModel.getUserName().isEmpty() || userModel.getPassword().isEmpty()
                        || userModel.getEmail().isEmpty() || userModel.getUserName().isEmpty()){
                    Toast.makeText(getApplicationContext(), "You cannot have blank fields!!", Toast.LENGTH_SHORT).show();
                } else if (!userModel.getPassword().equals(etConfirmPassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(),"Passwords Do not Match!!!",Toast.LENGTH_SHORT).show();
                } else {
                    /**
                     * We log what is happening. You will delete this before you publish your app.
                     * This is just for debugging purposes. :-)
                     */

                    Log.i(TAG, "@onClick: Storing user details.");
                    Log.i(TAG, "UserName: " + etUserName.getText().toString() + "\n"
                            + "Email: " + "Password: " + etPassword.getText().toString());
                    //Store user data in the session manager.
                    sessionManager.createUser(
                            userModel.getUserName(),
                            userModel.getUserName(),
                            userModel.getEmail(),
                            userModel.getPassword()
                    );
                    startActivity(new Intent(getApplicationContext(),Login.class));
                }
                break;
            default:
                Log.i(TAG, "@onClick: Wizard says NO!!!");
                break;
        }
            }
        }
