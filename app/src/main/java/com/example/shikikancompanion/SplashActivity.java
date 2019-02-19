package com.example.shikikancompanion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shikikancompanion.Database.DatabaseHandler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity.class);
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        db.createDataBase();
        startActivity(intent);
        finish();
    }
}
