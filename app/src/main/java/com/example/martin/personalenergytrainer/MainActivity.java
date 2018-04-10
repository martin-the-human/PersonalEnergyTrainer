package com.example.martin.personalenergytrainer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //Bundle get_colour = getIntent().getExtras();
    int shirt_colour; //number (1-3) determines shirt colour
    ImageView avatar_img;
    SeekBar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // formatting clock to 24 hour only
        TextClock tCT = findViewById(R.id.textClockTime);
        tCT.setFormat12Hour(null);
        tCT.setFormat24Hour("HH:mm");

        // set the date to current date
        TextView txtDate = findViewById(R.id.textClockDate);
        setDate(txtDate);

        //set the image of the avatar
        avatar_img = (ImageView)findViewById(R.id.img_avatar);

        //moved seekbar here, should be able to login
        //final SeekBar skBar = findViewById(R.id.seekBar);
        seekbar = findViewById(R.id.seekBar);
        final TextView txtValue = findViewById(R.id.txtBarValue);


        //navigation buttons, takes you to the respective pages
        Button btnProfile = findViewById(R.id.btnNavigationProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profile = new Intent(MainActivity.this, UserProfile.class);
                startActivity(profile);
            }
        });

        Button btnStats = findViewById(R.id.btnNavigationStats);
        btnStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stats = new Intent(MainActivity.this, Stats.class);
                startActivity(stats);
            }
        });

        Button btnSettings = findViewById(R.id.btnNavigationSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settings = new Intent(MainActivity.this, Settings.class);
                startActivity(settings);
            }
        });

        Button btnRefresh = findViewById(R.id.btnNavigationRefresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        //event for when seekbar is changed
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Integer sliderValue = seekbar.getProgress();
                txtValue.setText(sliderValue.toString());

                // multiply slider value to get rbg value and then change background colour
                RelativeLayout relMain = findViewById(R.id.relLayoutMain);
                Integer sliderValueAfter = Math.round(sliderValue * 2.55f);
                relMain.setBackgroundColor(Color.rgb(255, sliderValueAfter,0));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                set_colour(); //call the method
                set_image(shirt_colour); //pass in the value from the previous method
            }
        });
    }


    //easy way to create a toast message
    public void toast(String s)
    {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
    }

    public void setDate (TextView view){
        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String date = formatter.format(today);
        view.setText(date);
    }

    //
    public void set_image(int c) {
        // 35 < skbar = lethargic, 35 > skbar < 75 = normal , 75 < skbar = overflow
        if (seekbar.getProgress() < 35) {
            switch (c) {
                case 1:
                    avatar_img.setImageResource(R.drawable.pink_lethargic);
                    break;
                case 2:
                    avatar_img.setImageResource(R.drawable.white_lethargic);
                    break;
                case 3:
                    avatar_img.setImageResource(R.drawable.blue_lethargic);
                    break;
            }
        } else if (seekbar.getProgress() > 35 && seekbar.getProgress() < 75) {
            switch (c) {
                case 1:
                    avatar_img.setImageResource(R.drawable.pink_default_blink);
                    break;
                case 2:
                    avatar_img.setImageResource(R.drawable.white_default_blink);
                    break;
                case 3:
                    avatar_img.setImageResource(R.drawable.blue_default_blink);
                    break;
            }
        } else {
            switch (c) {
                case 1:
                    avatar_img.setImageResource(R.drawable.pink_overflow);
                    break;
                case 2:
                    avatar_img.setImageResource(R.drawable.white_overflow);
                    break;
                case 3:
                    avatar_img.setImageResource(R.drawable.blue_overflow);
                    break;
            }
        }
    }

    //set the colour using a key from settings page
    public void set_colour()
    {
        //add code: pull data from server
    }







    // takes in an integer
    // sets the bar progress relative to how much energy is being used against how much is being used
//    public void setBarProgress(int energyUsed, int expectedUsed)
//    {
//        // get values to determine how close the user is to the recommended usage
//        double expectedTenPercentUnder = expectedUsed * 0.9f;
//        double expectedTenPercentOver = expectedUsed * 1.1f;
//
//        // if the value is within
//        if (energyUsed > expectedTenPercentUnder && energyUsed < expectedTenPercentOver)
//        {
//            skBar.
//        }
//    }
}
