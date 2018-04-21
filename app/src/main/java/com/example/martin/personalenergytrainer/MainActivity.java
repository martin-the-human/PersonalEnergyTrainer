package com.example.martin.personalenergytrainer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
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
import java.util.Random;

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
                Intent settings = new Intent(MainActivity.this, POSTandGET.class);
                startActivity(settings);
            }
        });

        Button btnRefresh = findViewById(R.id.btnNavigationRefresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r1 = new Random();
                int i1 = r1.nextInt(4 - 1) + 1;
                shirt_colour = i1;

                Random r2 = new Random();
                int i2 = r2.nextInt(101 - 0) + 0;
                int test_energy_usage = i2;

                Toast.makeText(MainActivity.this, Integer.toString(i1), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, Integer.toString(i2), Toast.LENGTH_SHORT).show();

                set_image(shirt_colour, test_energy_usage);
            }
        });

        //disable the seekbar so the users cannot affect the current reading
        seekbar.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                return true;
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
    public void set_image(int c, int energyConsumption)
    {
        // 35 < skbar = lethargic, 35 > skbar < 75 = normal , 75 < skbar = overflow
        if (energyConsumption < 35) {
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
        } else if (energyConsumption >= 35 && energyConsumption < 75) {
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
}
