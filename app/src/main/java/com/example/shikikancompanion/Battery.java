package com.example.shikikancompanion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Battery extends AppCompatActivity {

    Spinner dorm;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        dorm = findViewById(R.id.dormNumber);
        String[] number = new String[]{"2","3","4","5","6","7","8","9","10"};

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, number);
        dorm.setAdapter(adapter);

    }
}
