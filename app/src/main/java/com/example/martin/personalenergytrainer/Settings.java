package com.example.martin.personalenergytrainer;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class Settings extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        String[] listOfSettings = {"Colour Blind Mode", "12 Hour Time Format", "24 Hour Time Format"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getListView().getContext(), android.R.layout.simple_list_item_1, listOfSettings);
        getListView().setAdapter(adapter);
    }
}
