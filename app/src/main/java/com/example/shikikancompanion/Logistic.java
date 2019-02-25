package com.example.shikikancompanion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Logistic extends AppCompatActivity {
    String[][] ch0, ch1, ch2, ch3, ch4, ch5, ch6, ch7, ch8, ch9,
            ch00h, ch01h, ch02h, ch03h, ch04h, ch05h, ch06h, ch07h, ch08h, ch09h;
    static int count;
    static ArrayList<String> logistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistic);
        init();
        setUpLogisticNormal();
        setUpCheckers();
        setTotals(0,0,0,0);

        CheckBox toggleBox = findViewById(R.id.toggle);
        toggleBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    setUpLogisticHour();
                }else{
                    setUpLogisticNormal();
                }
            }
        });
    }

    public void init() {
        logistics = new ArrayList<>();
        ch0 = new String[][]{
                {"0", "145", "145", "0", "1", "2", "0:50:00"},
                {"550", "0", "0", "350", "3", "0", "3:00:00"},
                {"900", "900", "900", "250", "2", "4", "12:00:00"},
                {"0", "1200", "800", "750", "5", "0", "24:00:00"}
        };
        ch1 = new String[][]{
                {"10", "1200", "15", "0", "0", "0", "0:15:00"},
                {"0", "40", "60", "0", "0", "0", "0:30:00"},
                {"30", "0", "30", "10", "2", "0", "1:00:00"},
                {"160", "160", "0", "0", "3", "0", "2:00:00"}
        };
        ch2 = new String[][]{
                {"100", "0", "0", "30", "0", "0", "0:40:00"},
                {"60", "200", "80", "0", "2", "0", "1:30:00"},
                {"10", "10", "10", "230", "1", "2", "4:00:00"},
                {"0", "250", "600", "60", "3", "0", "6:00:00"}
        };
        ch3 = new String[][]{
                {"50", "0", "75", "0", "0", "0", "0:20:00"},
                {"0", "120", "70", "30", "0", "0", "0:45:00"},
                {"0", "300", "0", "0", "1", "2", "1:30:00"},
                {"0", "0", "300", "300", "3", "4", "5:00:00"}

        };
        ch4 = new String[][]{
                {"0", "185", "185", "0", "4", "0", "1:00:00"},
                {"0", "0", "0", "210", "1", "0", "2:00:00"},
                {"800", "550", "0", "0", "3", "2", "6:00:00"},
                {"400", "400", "400", "150", "1", "0", "8:00:00"}
        };
        ch5 = new String[][]{
                {"0", "0", "100", "45", "0", "0", "0:30:00"},
                {"0", "600", "300", "0", "2", "0", "2:30:00"},
                {"800", "400", "400", "0", "4", "0", "4:00:00"},
                {"100", "0", "0", "700", "3", "0", "7:00:00"}
        };
        ch6 = new String[][]{
                {"300", "300", "0", "100", "0", "0", "2:00:00"},
                {"0", "200", "550", "100", "1", "2", "3:00:00"},
                {"0", "0", "200", "500", "4", "0", "5:00:00"},
                {"800", "800", "800", "0", "5", "0", "12:00:00"}
        };
        ch7 = new String[][]{
                {"650", "0", "650", "0", "0", "0", "2:30:00"},
                {"0", "650", "0", "300", "2", "3", "4:00:00"},
                {"900", "600", "600", "0", "4", "0", "5:30:00"},
                {"250", "250", "250", "600", "1", "0", "8:00:00"}
        };
        ch8 = new String[][]{
                {"150", "150", "150", "0", "4", "0", "1:00:00"},
                {"0", "0", "0", "450", "2", "0", "3:00:00"},
                {"400", "800", "800", "0", "1", "2", "6:00:00"},
                {"1500", "400", "400", "100", "3", "0", "9:00:00"}
        };
        ch9 = new String[][]{
                {"0", "0", "100", "50", "0", "0", "0:30:00"},
                {"180", "0", "180", "100", "1", "0", "1:30:00"},
                {"750", "750", "0", "0", "3", "0", "4:30:00"},
                {"500", "900", "900", "0", "4", "0", "7:00:00"}
        };

        ch00h = new String[][]{
                {"0", "174", "174", "0", "1", "2", "0:50:00"},
                {"183.33", "0", "0", "116.66", "3", "0", "3:00:00"},
                {"75", "75", "75", "20.83", "2", "4", "12:00:00"},
                {"0", "50", "33.33", "31.25", "5", "0", "24:00:00"}
        };

        ch01h = new String[][]{
                {"40", "120", "60", "0", "0", "0", "0:15:00"},
                {"0", "80", "120", "0", "0", "0", "0:30:00"},
                {"30", "0", "30", "10", "2", "0", "1:00:00"},
                {"80", "80", "0", "0", "3", "0", "2:00:00"}
        };

        ch02h = new String[][]{
                {"150", "0", "0", "45", "0", "0", "0:40:00"},
                {"40", "133.33", "53.33", "0", "2", "0", "1:30:00"},
                {"2.5", "2.5", "2.5", "57.5", "1", "2", "4:00:00"},
                {"0", "41.66", "100", "10", "3", "0", "6:00:00"}
        };

        ch03h = new String[][]{
                {"150", "0", "225", "0", "0", "0", "0:20:00"},
                {"0", "160", "93.33", "40", "0", "0", "0:45:00"},
                {"0", "200", "0", "0", "1", "2", "1:30:00"},
                {"0", "0", "60", "60", "3", "4", "5:00:00"}
        };

        ch04h = new String[][]{
                {"0", "185", "185", "0", "4", "0", "1:00:00"},
                {"0", "0", "0", "105", "1", "0", "2:00:00"},
                {"133.33", "91.66", "0", "0", "3", "2", "6:00:00"},
                {"50", "50", "50", "18.75", "1", "0", "8:00:00"}
        };

        ch05h = new String[][]{
                {"0", "0", "200", "90", "0", "0", "0:30:00"},
                {"0", "240", "120", "0", "2", "0", "2:30:00"},
                {"200", "100", "100", "0", "4", "0", "4:00:00"},
                {"14.285", "0", "0", "100", "3", "0", "7:00:00"}
        };

        ch06h = new String[][]{
                {"150", "150", "0", "50", "0", "0", "2:00:00"},
                {"0", "66.66", "183.33", "33.33", "1", "2", "3:00:00"},
                {"0", "0", "40", "100", "4", "0", "5:00:00"},
                {"66.66", "66.66", "66.66", "0", "5", "0", "12:00:00"}
        };

        ch07h = new String[][]{
                {"260", "0", "260", "0", "0", "0", "2:30:00"},
                {"0", "216.66", "0", "100", "2", "3", "4:00:00"},
                {"163.63", "109.09", "109.09", "0", "4", "0", "5:30:00"},
                {"31.25", "31.25", "31.25", "75", "1", "0", "8:00:00"}
        };

        ch08h = new String[][]{
                {"150", "150", "150", "0", "4", "0", "1:00:00"},
                {"0", "0", "0", "150", "2", "0", "3:00:00"},
                {"66.67", "133.33", "133.33", "0", "1", "2", "6:00:00"},
                {"166.67", "44.44", "44.44", "11.11", "3", "0", "9:00:00"}
        };

        ch09h = new String[][]{
                {"0", "0", "200", "100", "0", "0", "0:30:00"},
                {"120", "0", "120", "66.67", "1", "0", "1:30:00"},
                {"166.67", "166.67", "0", "0", "3", "0", "4:30:00"},
                {"71.43", "128.57", "128.57", "0", "4", "0", "7:00:00"}
        };
    }
    public void setUpLogisticNormal() {
        fillLogistic(0, ch0);
        fillLogistic(1, ch1);
        fillLogistic(2, ch2);
        fillLogistic(3, ch3);
        fillLogistic(4, ch4);
        fillLogistic(5, ch5);
        fillLogistic(6, ch6);
        fillLogistic(7, ch7);
        fillLogistic(8, ch8);
        fillLogistic(9, ch9);
    }
    public void setUpLogisticHour() {
        fillLogisticH(0, ch00h);
        fillLogisticH(1, ch01h);
        fillLogisticH(2, ch02h);
        fillLogisticH(3, ch03h);
        fillLogisticH(4, ch04h);
        fillLogisticH(5, ch05h);
        fillLogisticH(6, ch06h);
        fillLogisticH(7, ch07h);
        fillLogisticH(8, ch08h);
        fillLogisticH(9, ch09h);
    }


    public void setTotals(double manpower, double ammo, double ration, double part){
        TextView manpower_h, ammo_h, ration_h, part_h,
                manpower_t, ammo_t, ration_t, part_t;

        manpower_h = findViewById(R.id.mpHour);
        ammo_h = findViewById(R.id.amHour);
        ration_h = findViewById(R.id.rtHour);
        part_h = findViewById(R.id.ptHour);

        manpower_t = findViewById(R.id.mpTotal);
        ammo_t = findViewById(R.id.amTotal);
        ration_t = findViewById(R.id.rtTotal);
        part_t = findViewById(R.id.ptTotal);

        //
        manpower_h.setText(new DecimalFormat("#").format(manpower));
        ammo_h.setText(new DecimalFormat("#").format(ammo));
        ration_h.setText(new DecimalFormat("#").format(ration));
        part_h.setText(new DecimalFormat("#").format(part));

        manpower_t.setText(new DecimalFormat("#").format(manpower*24));
        ammo_t.setText(new DecimalFormat("#").format(ammo*24));
        ration_t.setText(new DecimalFormat("#").format(ration*24));
        part_t.setText(new DecimalFormat("#").format(part*24));
    }

    public void setUpCheckers() {
        CheckBox checkers;
        count = 0;
        for (int ch = 0; ch < 10; ch++) {
            for (int c = 1; c < 5; c++) {
                String box = "c0" + ch + "_" + c;
                int id = getResources().getIdentifier(box,
                        "id", getPackageName());

                checkers = findViewById(id);
                checkers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            if (count < 4) {
                                count++;
                                String value = buttonView.getResources().getResourceEntryName(buttonView.getId());
                                Log.e("value", value);
                                logistics.add(value);
                                update();
                            } else {
                                buttonView.setChecked(false);
                                Toast.makeText(getApplicationContext(), "Can't have more than 4 logistics", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            count--;
                            String value = buttonView.getResources().getResourceEntryName(buttonView.getId());
                            Log.e("value", value);
                            logistics.remove(value);
                            update();
                        }
                    }
                });
            }
        }
    }

    public void fillLogistic(int chapter, String[][] logistic) {
        TextView manpower, ammo, ration, part, time;
        int manID, ammoID, rationID, partID, timeID;

        for (int i = 0; i < 4; i++) {
            manID = getResources().getIdentifier("manpower_0" + chapter + "_" + (i + 1),
                    "id", getPackageName());

            ammoID = getResources().getIdentifier("ammo_0" + chapter + "_" + (i + 1),
                    "id", getPackageName());

            rationID = getResources().getIdentifier("ration_0" + chapter + "_" + (i + 1),
                    "id", getPackageName());

            partID = getResources().getIdentifier("part_0" + chapter + "_" + (i + 1),
                    "id", getPackageName());

            timeID = getResources().getIdentifier("c0" + chapter + "_" + (i + 1) + "_time",
                    "id", getPackageName());

            manpower = findViewById(manID);
            ammo = findViewById(ammoID);
            ration = findViewById(rationID);
            part = findViewById(partID);
            time = findViewById(timeID);

            manpower.setText(logistic[i][0]);
            ammo.setText(logistic[i][1]);
            ration.setText(logistic[i][2]);
            part.setText(logistic[i][3]);
            time.setText(logistic[i][6]);
        }
    }

    public void fillLogisticH(int chapter, String[][] logistic) {
        TextView manpowerT, ammoT, rationT, partT, time;
        int manID, ammoID, rationID, partID, timeID;
        Double manpower, ammo, ration, part;

        for (int i = 0; i < 4; i++) {
            manID = getResources().getIdentifier("manpower_0" + chapter + "_" + (i + 1),
                    "id", getPackageName());

            ammoID = getResources().getIdentifier("ammo_0" + chapter + "_" + (i + 1),
                    "id", getPackageName());

            rationID = getResources().getIdentifier("ration_0" + chapter + "_" + (i + 1),
                    "id", getPackageName());

            partID = getResources().getIdentifier("part_0" + chapter + "_" + (i + 1),
                    "id", getPackageName());

            timeID = getResources().getIdentifier("c0" + chapter + "_" + (i + 1) + "_time",
                    "id", getPackageName());

            manpowerT = findViewById(manID);
            ammoT = findViewById(ammoID);
            rationT = findViewById(rationID);
            partT = findViewById(partID);
            time = findViewById(timeID);

            manpower = Double.parseDouble(logistic[i][0]);
            ammo = Double.parseDouble(logistic[i][1]);
            ration = Double.parseDouble(logistic[i][2]);
            part = Double.parseDouble(logistic[i][3]);


            manpowerT.setText(new DecimalFormat("#").format(manpower));
            ammoT.setText(new DecimalFormat("#").format(ammo));
            rationT.setText(new DecimalFormat("#").format(ration));
            partT.setText(new DecimalFormat("#").format(part));
            time.setText(logistic[i][6]);
        }
    }

    public void update() {
        double manpower = 0, ammo = 0, ration = 0, part = 0;
        for(String s: logistics){
            switch(s){
                case "c00_1":
                    manpower += Double.parseDouble(ch00h[0][0]);
                    ammo += Double.parseDouble(ch00h[0][1]);
                    ration += Double.parseDouble(ch00h[0][2]);
                    part += Double.parseDouble(ch00h[0][3]);
                    break;
                case "c00_2":
                    manpower += Double.parseDouble(ch00h[1][0]);
                    ammo += Double.parseDouble(ch00h[1][1]);
                    ration += Double.parseDouble(ch00h[1][2]);
                    part += Double.parseDouble(ch00h[1][3]);
                    break;
                case "c00_3":
                    manpower += Double.parseDouble(ch00h[2][0]);
                    ammo += Double.parseDouble(ch00h[2][1]);
                    ration += Double.parseDouble(ch00h[2][2]);
                    part += Double.parseDouble(ch00h[2][3]);
                    break;
                case "c00_4":
                    manpower += Double.parseDouble(ch00h[3][0]);
                    ammo += Double.parseDouble(ch00h[3][1]);
                    ration += Double.parseDouble(ch00h[3][2]);
                    part += Double.parseDouble(ch00h[3][3]);
                    break;
                case "c01_1":
                    manpower += Double.parseDouble(ch01h[0][0]);
                    ammo += Double.parseDouble(ch01h[0][1]);
                    ration += Double.parseDouble(ch01h[0][2]);
                    part += Double.parseDouble(ch01h[0][3]);
                    break;
                case "c01_2":
                    manpower += Double.parseDouble(ch01h[1][0]);
                    ammo += Double.parseDouble(ch01h[1][1]);
                    ration += Double.parseDouble(ch01h[1][2]);
                    part += Double.parseDouble(ch01h[1][3]);
                    break;
                case "c01_3":
                    manpower += Double.parseDouble(ch01h[2][0]);
                    ammo += Double.parseDouble(ch01h[2][1]);
                    ration += Double.parseDouble(ch01h[2][2]);
                    part += Double.parseDouble(ch01h[2][3]);
                    break;
                case "c01_4":
                    manpower += Double.parseDouble(ch01h[3][0]);
                    ammo += Double.parseDouble(ch01h[3][1]);
                    ration += Double.parseDouble(ch01h[3][2]);
                    part += Double.parseDouble(ch01h[3][3]);
                    break;
                case "c02_1":
                    manpower += Double.parseDouble(ch02h[0][0]);
                    ammo += Double.parseDouble(ch02h[0][1]);
                    ration += Double.parseDouble(ch02h[0][2]);
                    part += Double.parseDouble(ch02h[0][3]);
                    break;
                case "c02_2":
                    manpower += Double.parseDouble(ch02h[1][0]);
                    ammo += Double.parseDouble(ch02h[1][1]);
                    ration += Double.parseDouble(ch02h[1][2]);
                    part += Double.parseDouble(ch02h[1][3]);
                    break;
                case "c02_3":
                    manpower += Double.parseDouble(ch02h[2][0]);
                    ammo += Double.parseDouble(ch02h[2][1]);
                    ration += Double.parseDouble(ch02h[2][2]);
                    part += Double.parseDouble(ch02h[2][3]);
                    break;
                case "c02_4":
                    manpower += Double.parseDouble(ch02h[3][0]);
                    ammo += Double.parseDouble(ch02h[3][1]);
                    ration += Double.parseDouble(ch02h[3][2]);
                    part += Double.parseDouble(ch02h[3][3]);
                    break;
                case "c03_1":
                    manpower += Double.parseDouble(ch03h[0][0]);
                    ammo += Double.parseDouble(ch03h[0][1]);
                    ration += Double.parseDouble(ch03h[0][2]);
                    part += Double.parseDouble(ch03h[0][3]);
                    break;
                case "c03_2":
                    manpower += Double.parseDouble(ch03h[1][0]);
                    ammo += Double.parseDouble(ch03h[1][1]);
                    ration += Double.parseDouble(ch03h[1][2]);
                    part += Double.parseDouble(ch03h[1][3]);
                    break;
                case "c03_3":
                    manpower += Double.parseDouble(ch03h[2][0]);
                    ammo += Double.parseDouble(ch03h[2][1]);
                    ration += Double.parseDouble(ch03h[2][2]);
                    part += Double.parseDouble(ch03h[2][3]);
                    break;
                case "c03_4":
                    manpower += Double.parseDouble(ch03h[3][0]);
                    ammo += Double.parseDouble(ch03h[3][1]);
                    ration += Double.parseDouble(ch03h[3][2]);
                    part += Double.parseDouble(ch03h[3][3]);
                    break;
                case "c04_1":
                    manpower += Double.parseDouble(ch04h[0][0]);
                    ammo += Double.parseDouble(ch04h[0][1]);
                    ration += Double.parseDouble(ch04h[0][2]);
                    part += Double.parseDouble(ch04h[0][3]);
                    break;
                case "c04_2":
                    manpower += Double.parseDouble(ch04h[1][0]);
                    ammo += Double.parseDouble(ch04h[1][1]);
                    ration += Double.parseDouble(ch04h[1][2]);
                    part += Double.parseDouble(ch04h[1][3]);
                    break;
                case "c04_3":
                    manpower += Double.parseDouble(ch04h[2][0]);
                    ammo += Double.parseDouble(ch04h[2][1]);
                    ration += Double.parseDouble(ch04h[2][2]);
                    part += Double.parseDouble(ch04h[2][3]);
                    break;
                case "c04_4":
                    manpower += Double.parseDouble(ch04h[3][0]);
                    ammo += Double.parseDouble(ch04h[3][1]);
                    ration += Double.parseDouble(ch04h[3][2]);
                    part += Double.parseDouble(ch04h[3][3]);
                    break;
                case "c05_1":
                    manpower += Double.parseDouble(ch05h[0][0]);
                    ammo += Double.parseDouble(ch05h[0][1]);
                    ration += Double.parseDouble(ch05h[0][2]);
                    part += Double.parseDouble(ch05h[0][3]);
                    break;
                case "c05_2":
                    manpower += Double.parseDouble(ch05h[1][0]);
                    ammo += Double.parseDouble(ch05h[1][1]);
                    ration += Double.parseDouble(ch05h[1][2]);
                    part += Double.parseDouble(ch05h[1][3]);
                    break;
                case "c05_3":
                    manpower += Double.parseDouble(ch05h[2][0]);
                    ammo += Double.parseDouble(ch05h[2][1]);
                    ration += Double.parseDouble(ch05h[2][2]);
                    part += Double.parseDouble(ch05h[2][3]);
                    break;
                case "c05_4":
                    manpower += Double.parseDouble(ch05h[3][0]);
                    ammo += Double.parseDouble(ch05h[3][1]);
                    ration += Double.parseDouble(ch05h[3][2]);
                    part += Double.parseDouble(ch05h[3][3]);
                    break;
                case "c06_1":
                    manpower += Double.parseDouble(ch06h[0][0]);
                    ammo += Double.parseDouble(ch06h[0][1]);
                    ration += Double.parseDouble(ch06h[0][2]);
                    part += Double.parseDouble(ch06h[0][3]);
                    break;
                case "c06_2":
                    manpower += Double.parseDouble(ch06h[1][0]);
                    ammo += Double.parseDouble(ch06h[1][1]);
                    ration += Double.parseDouble(ch06h[1][2]);
                    part += Double.parseDouble(ch06h[1][3]);
                    break;
                case "c06_3":
                    manpower += Double.parseDouble(ch06h[2][0]);
                    ammo += Double.parseDouble(ch06h[2][1]);
                    ration += Double.parseDouble(ch06h[2][2]);
                    part += Double.parseDouble(ch06h[2][3]);
                    break;
                case "c06_4":
                    manpower += Double.parseDouble(ch06h[3][0]);
                    ammo += Double.parseDouble(ch06h[3][1]);
                    ration += Double.parseDouble(ch06h[3][2]);
                    part += Double.parseDouble(ch06h[3][3]);
                    break;
                case "c07_1":
                    manpower += Double.parseDouble(ch07h[0][0]);
                    ammo += Double.parseDouble(ch07h[0][1]);
                    ration += Double.parseDouble(ch07h[0][2]);
                    part += Double.parseDouble(ch07h[0][3]);
                    break;
                case "c07_2":
                    manpower += Double.parseDouble(ch07h[1][0]);
                    ammo += Double.parseDouble(ch07h[1][1]);
                    ration += Double.parseDouble(ch07h[1][2]);
                    part += Double.parseDouble(ch07h[1][3]);
                    break;
                case "c07_3":
                    manpower += Double.parseDouble(ch07h[2][0]);
                    ammo += Double.parseDouble(ch07h[2][1]);
                    ration += Double.parseDouble(ch07h[2][2]);
                    part += Double.parseDouble(ch07h[2][3]);
                    break;
                case "c07_4":
                    manpower += Double.parseDouble(ch07h[3][0]);
                    ammo += Double.parseDouble(ch07h[3][1]);
                    ration += Double.parseDouble(ch07h[3][2]);
                    part += Double.parseDouble(ch07h[3][3]);
                    break;
                case "c08_1":
                    manpower += Double.parseDouble(ch08h[0][0]);
                    ammo += Double.parseDouble(ch08h[0][1]);
                    ration += Double.parseDouble(ch08h[0][2]);
                    part += Double.parseDouble(ch08h[0][3]);
                    break;
                case "c08_2":
                    manpower += Double.parseDouble(ch08h[1][0]);
                    ammo += Double.parseDouble(ch08h[1][1]);
                    ration += Double.parseDouble(ch08h[1][2]);
                    part += Double.parseDouble(ch08h[1][3]);
                    break;
                case "c08_3":
                    manpower += Double.parseDouble(ch08h[2][0]);
                    ammo += Double.parseDouble(ch08h[2][1]);
                    ration += Double.parseDouble(ch08h[2][2]);
                    part += Double.parseDouble(ch08h[2][3]);
                    break;
                case "c08_4":
                    manpower += Double.parseDouble(ch08h[3][0]);
                    ammo += Double.parseDouble(ch08h[3][1]);
                    ration += Double.parseDouble(ch08h[3][2]);
                    part += Double.parseDouble(ch08h[3][3]);
                    break;
                case "c09_1":
                    manpower += Double.parseDouble(ch09h[0][0]);
                    ammo += Double.parseDouble(ch09h[0][1]);
                    ration += Double.parseDouble(ch09h[0][2]);
                    part += Double.parseDouble(ch09h[0][3]);
                    break;
                case "c09_2":
                    manpower += Double.parseDouble(ch09h[1][0]);
                    ammo += Double.parseDouble(ch09h[1][1]);
                    ration += Double.parseDouble(ch09h[1][2]);
                    part += Double.parseDouble(ch09h[1][3]);
                    break;
                case "c09_3":
                    manpower += Double.parseDouble(ch09h[2][0]);
                    ammo += Double.parseDouble(ch09h[2][1]);
                    ration += Double.parseDouble(ch09h[2][2]);
                    part += Double.parseDouble(ch09h[2][3]);
                    break;
                case "c09_4":
                    manpower += Double.parseDouble(ch09h[3][0]);
                    ammo += Double.parseDouble(ch09h[3][1]);
                    ration += Double.parseDouble(ch09h[3][2]);
                    part += Double.parseDouble(ch09h[3][3]);
                    break;
            }
        }
        setTotals(manpower, ammo, ration, part);
    }
}
