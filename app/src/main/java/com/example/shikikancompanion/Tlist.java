package com.example.shikikancompanion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shikikancompanion.Database.DatabaseHandler;

import java.util.ArrayList;

public class Tlist extends AppCompatActivity {
    ArrayList<indexHolder> indexList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tlist);


        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        indexList = db.getIndex();

        GridView grid = findViewById(R.id.grid);
        GridAdapter adapter = new GridAdapter(getApplicationContext(), indexList);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView dude = view.findViewById(R.id.index);
                Intent tactAct = new Intent(Tlist.this, TacticalDisplay.class);
                tactAct.putExtra("index", Integer.parseInt(dude.getText().toString()));
                startActivity(tactAct);
            }
        });
    }
}
