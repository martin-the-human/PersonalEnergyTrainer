package com.example.martin.personalenergytrainer;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.martin.personalenergytrainer.POSTandGET;
import com.example.martin.personalenergytrainer.retrofit.APIRelated;


public class SettingsOld extends AppCompatActivity {

    int colour;
    int time;

    RadioGroup radgrp_time;
    RadioButton radio_12;
    RadioButton radio_24;

    RadioGroup radgrp_colour;
    RadioButton blue;
    RadioButton pink;
    RadioButton white;

    Button save_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        radgrp_time = (RadioGroup)findViewById(R.id.rad_grp_time);
        radio_12 = findViewById(R.id.rad_12hr);
        radio_24 = findViewById(R.id.rad_24hr);

        radgrp_colour = (RadioGroup)findViewById(R.id.rad_grp_shirt);
        blue = findViewById(R.id.rad_blue);
        pink = findViewById(R.id.rad_pink);
        white = findViewById(R.id.rad_white);

        save_settings = (Button)findViewById(R.id.btnSaveSettings);
        radgrp_time.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                time_format();
                toast("Time format has been changed");
            }
        });

        radgrp_colour.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                shirt_colour();
                toast("Colour has been changed!");
            }
        });

        save_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void toast(String s)
    {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    public void time_format(){
        //1 = 12hr, 2 = 24hr, 3 = nothing checked
        if (radio_12.isChecked()) {
            time = 1;
        }
        else if (radio_24.isChecked()){
            time = 2;
        }
        else if (!radio_12.isChecked() && !radio_24.isChecked()){
            time = 3;
        }
    }

    public void shirt_colour(){
        // 1 = pink, 2 = white, 3 = blue/default
        if (pink.isChecked()){
            colour = 1;
        }
        else if (white.isChecked()){
            colour = 2;
        }
        else if (blue.isChecked()){
            colour = 3;
        }
        else if (!pink.isChecked() && !white.isChecked() && !blue.isChecked()){
            colour = 3;
        }
    }

    public void save()
    {
        //add code: save to server – POST button code

    }
}
