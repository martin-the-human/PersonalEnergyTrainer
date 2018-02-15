package com.example.martin.personalenergytrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnProfile = (Button) findViewById(R.id.btnNavigationProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profile = new Intent(MainActivity.this, UserProfile.class);
                startActivity(profile);
            }
        });

        Button btnStats = (Button) findViewById(R.id.btnNavigationStats);
        btnStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stats = new Intent(MainActivity.this, Stats.class);
                startActivity(stats);
            }
        });

        Button btnSettings = (Button) findViewById(R.id.btnNavigationSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settings = new Intent(MainActivity.this, Settings.class);
                startActivity(settings);
            }
        });
    }
}
