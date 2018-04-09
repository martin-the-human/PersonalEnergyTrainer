package com.example.martin.personalenergytrainer;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final RadioButton radio12 = findViewById(R.id.rad_12hr);
        final RadioButton radio24 = findViewById(R.id.rad_24hr);

        radio12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio12.setChecked(true);
                radio24.setChecked(false);
            }
        });
        radio24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio24.setChecked(true);
                radio12.setChecked(false);
            }
        });
    }
}
