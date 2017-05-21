package com.example.android.zailet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    final static String TAG = "LoginActivity";

    //layout variables
    private EditText mUsername;
    private EditText mPassword;
    private Button mLogin;
    private TextView mCreateAccount;

    //stores the username and password entered by the user
    private String username;
    private String password;

    //type of task to bbe performed
    static String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //finding the layout elements
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mLogin = (Button) findViewById(R.id.login);
        mCreateAccount = (TextView) findViewById(R.id.createAccount);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting the username and password written by the user
                username = mUsername.getText().toString();
                password = mPassword.getText().toString();
                type = "Login";
                if(!username.isEmpty() && !password.isEmpty()) {
                    new CustomAsyncTask(LoginActivity.this).execute(username,password);
                } else {
                    if(username.isEmpty()) {
                        mUsername.setError("Field cannot be left blank");
                    }
                    if(password.isEmpty()) {
                        mPassword.setError("Field cannot be left blank");
                    }
                }
            }
        });

        mCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Register";
                Intent RegisterIntent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(RegisterIntent);
            }
        });
    }
}
