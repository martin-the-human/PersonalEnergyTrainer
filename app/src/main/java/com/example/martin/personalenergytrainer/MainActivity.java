package com.example.martin.personalenergytrainer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextClock tCT = findViewById(R.id.textClockTime);

        tCT.setFormat12Hour(null);
        tCT.setFormat24Hour("HH:mm");

        TextView txtDate = findViewById(R.id.textClockDate);
        setDate(txtDate);

        final TextView txtValue = findViewById(R.id.txtBarValue);

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

        final SeekBar skBar = findViewById(R.id.seekBar);
        skBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Integer sliderValue = skBar.getProgress();
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

            }
        });
    }

    public void setDate (TextView view){

        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String date = formatter.format(today);
        view.setText(date);
    }
}
