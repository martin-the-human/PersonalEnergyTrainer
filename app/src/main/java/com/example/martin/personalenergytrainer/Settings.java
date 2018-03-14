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

        final RadioButton radio12 = findViewById(R.id.rad12hr);
        final RadioButton radio24 = findViewById(R.id.rad24hr);

    }
}
