package com.example.android.zailet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    //layout variables
    private EditText mUsername;
    private EditText mPassword;
    private EditText mEmailId;
    private Button mRegister;

    //stores the username, password and Email ID entered by the user
    private String username;
    private String password;
    private String emailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //finding the layout elements
        mUsername = (EditText) findViewById(R.id.enterusername);
        mPassword = (EditText) findViewById(R.id.enterpassword);
        mEmailId = (EditText) findViewById(R.id.enteremail);
        mRegister = (Button) findViewById(R.id.register);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getting the username, password and email ID written by the user
                username = mUsername.getText().toString();
                password = mPassword.getText().toString();
                emailId = mEmailId.getText().toString();

                //checking for empty fields
                if(username.isEmpty() || password.isEmpty() || emailId.isEmpty()) {
                    //Toast.makeText(RegisterActivity.this,"Empty Field(s)",Toast.LENGTH_LONG).show();
                    if(username.isEmpty()) {
                        mUsername.setError("Field cannot be left blank");
                    }
                    if(password.isEmpty()) {
                        mPassword.setError("Field cannot be left blank");
                    }
                    if(emailId.isEmpty()) {
                        mEmailId.setError("Field cannot be left blank");
                    }
                    return;
                }

                //checking for length of password
                if(password.length()<6) {
                    mPassword.setError("Password must contain at least 6 characters");
                    return;
                }

                new CustomAsyncTask(RegisterActivity.this).execute(username,password,emailId);
            }
        });
    }
}
