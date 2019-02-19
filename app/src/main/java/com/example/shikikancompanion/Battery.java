package com.example.shikikancompanion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Battery extends AppCompatActivity {

    Spinner dorm;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        setTitle("Battery Calculator");

        dorm = findViewById(R.id.dormNumber);
        String[] number = new String[]{"2","3","4","5","6","7","8","9","10"};

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, number);
        dorm.setAdapter(adapter);
        dorm.setSelection(0);

        final EditText comfortTotal = findViewById(R.id.totalComfort);
        final TextView result = findViewById(R.id.result);

        final Button calculate = findViewById(R.id.dormButton);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comfortTotal.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please comfort the girls :<", Toast.LENGTH_SHORT).show();
                }else {
                    int dormNum = Integer.parseInt(dorm.getSelectedItem().toString());
                    calculate(dormNum, Double.parseDouble(comfortTotal.getText().toString()), result);
                }
            }
        });

    }

    private void calculate(int dorm, double comfort, TextView result){
        double base = 0;
        switch(dorm){
            case 2:
                base = 50;
                break;
            case 3:
                base = 85;
                break;
            case 4:
                base = 95;
                break;
            case 5:
                base = 99;
                break;
            case 6:
                base = 101;
                break;
            case 7:
                base = 102;
                break;
            case 8:
                base = 102.5;
                break;
            case 9:
                base = 103;
                break;
            case 10:
                base = 103.5;
                break;
        }

        double finalResult = base + ((11* comfort)/10000) - (0.1*((comfort/10000)*(comfort/10000)));
//        String output = "<b>" + String.format("%.1f", finalResult) + "</b>" + " ±8 batteries every 24 hours";
        String output = "<b>" + new DecimalFormat("#.0").format(finalResult) + "</b>" + " ±8 batteries every 24 hours";
        result.setText(Html.fromHtml(output));
    }
}
