package com.example.martin.personalenergytrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.URL;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        Button btn_login = (Button) findViewById(R.id.buttonLogin);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainActivity = new Intent(LoginScreen.this, MainActivity.class);
                startActivity(mainActivity);
            }
        });
    }

    //send login details to the server and load the accounts details
    public void login_save(){

    }

    public void login_load(){

    }
}
