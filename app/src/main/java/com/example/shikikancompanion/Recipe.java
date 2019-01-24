package com.example.shikikancompanion;

import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.shikikancompanion.Database.DatabaseHandler;

import java.io.IOException;
import java.util.ArrayList;

public class Recipe extends AppCompatActivity {

    EditText mp, am, ra, pa;
    Button submit;
    RadioGroup productionGroup;
    RadioButton productionType;
    RadioButton standard;
    RadioButton heavy;
    private static recipeHolder bot;
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        bot = new recipeHolder();
        tabs = findViewById(R.id.tabGroup);

        //input
        mp = findViewById(R.id.manpower_input);
        am = findViewById(R.id.ammo_input);
        ra = findViewById(R.id.ration_input);
        pa = findViewById(R.id.part_input);

        //initial setup
        mp.setText("30");
        am.setText("30");
        ra.setText("30");
        pa.setText("30");

        //Radio buttons
        productionGroup = findViewById(R.id.production);
        standard = findViewById(R.id.standard);
        heavy = findViewById(R.id.heavy);

        standard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int maxLength = 3;
                InputFilter[] FilterArray = new InputFilter[1];
                FilterArray[0] = new InputFilter.LengthFilter(maxLength);

                mp.setFilters(FilterArray);
                am.setFilters(FilterArray);
                ra.setFilters(FilterArray);
                pa.setFilters(FilterArray);
                mp.setText("30");
                am.setText("30");
                ra.setText("30");
                pa.setText("30");
            }
        });

        heavy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int maxLength = 4;
                InputFilter[] FilterArray = new InputFilter[1];
                FilterArray[0] = new InputFilter.LengthFilter(maxLength);

                mp.setFilters(FilterArray);
                am.setFilters(FilterArray);
                ra.setFilters(FilterArray);
                pa.setFilters(FilterArray);
                mp.setText("1000");
                am.setText("1000");
                ra.setText("1000");
                pa.setText("1000");
            }
        });

        //submit
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bot.clear();

                if (mp.getText().toString().equals("") || am.getText().toString().equals("") || ra.getText().toString().equals("") || pa.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please input in numbers", Toast.LENGTH_SHORT).show();
                } else {
                    int manpower = Integer.valueOf(mp.getText().toString());
                    int ammo = Integer.valueOf(am.getText().toString());
                    int ration = Integer.valueOf(ra.getText().toString());
                    int part = Integer.valueOf(pa.getText().toString());

                    int selectedProduction = productionGroup.getCheckedRadioButtonId();
                    productionType = findViewById(selectedProduction);

                    tabs.setVisibility(View.VISIBLE);
                    if (productionType.getText().equals("Standard Production")) {
                        if (manpower < 30 || ammo < 30 || ration < 30 || part < 30) {
                            Toast.makeText(getApplicationContext(), "Make sure the numbers are higher than 30", Toast.LENGTH_SHORT).show();
                        } else {
                            displayStandardRecipe(manpower, ammo, ration, part);
                        }
                    } else {
                        if (manpower < 1000 || ammo < 1000 || ration < 1000 || part < 1000) {
                            Toast.makeText(getApplicationContext(), "Make sure the numbers are higher than 1000", Toast.LENGTH_SHORT).show();
                        } else {
                            displayHeavyRecipe(manpower, ammo, ration, part);
                        }
                    }
                }
            }
        });

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String tabName = tab.getText().toString();
                if (!tabName.equals("")) {
                    clearTab();
                    bot.display(tabName);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void displayStandardRecipe(int m, int a, int r, int p) {
        ArrayList<String> sets;
        sets = getSetS(m, a, r, p);
        new getRecipe("standard", sets).execute();
    }

    private void displayHeavyRecipe(int m, int a, int r, int p) {
        ArrayList<String> sets;
        sets = getSetH(m, a, r, p);
        new getRecipe("heavy", sets).execute();
    }

    public void clearTab() {
        TextView one = findViewById(R.id.displayR2N);
        TextView two = findViewById(R.id.displayR3N);
        TextView three = findViewById(R.id.displayR4N);
        TextView four = findViewById(R.id.displayR5N);

        one.setText("");
        two.setText("");
        three.setText("");
        four.setText("");
    }

    private class getRecipe extends AsyncTask<Void, Void, String> {
        ArrayList<String> sets;
        String type;

        private getRecipe(String type, ArrayList<String> s) {
            this.type = type;
            sets = s;
        }

        protected String doInBackground(Void... nah) {
            String temp = "";
            DatabaseHandler db = new DatabaseHandler(getApplicationContext());

            for (String set : sets) {
                for (int i = 2; i < 6; i++) {
                    if (type.equals("standard")) {
                        bot.storeData(i, set, db.getNameS(i, set), db.getTimeS(i, set));
                    } else {
                        bot.storeData(i, set, db.getNameH(i, set), db.getTimeH(i, set));
                    }
                }
            }
            return temp;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            bot.setTabs();
        }
    }

    private ArrayList<String> getSetS(int m, int a, int r, int p) {
        int total = m + a + r + p;
        ArrayList<String> sets = new ArrayList<>();
        if (total <= 920) {
            sets.add("HG1");
            sets.add("SMG1");

            if (m >= 130 && a >= 130 && r >= 130 && p >= 130) {
                sets.add("HG2");
            }

            if (m >= 400 && a >= 400 && r >= 30 && p >= 30) {
                sets.add("SMG2");
            }
            if (total >= 800) {
                sets.add("AR1");
            }
            if (m >= 30 && a >= 400 && r >= 400 && p >= 30) {
                sets.add("AR2");
            }
            if (m >= 300 && a >= 30 && r >= 300 && p >= 30) {
                sets.add("RF1");
            }
            if (m >= 400 && a >= 30 && r >= 400 && p >= 30) {
                sets.add("RF2");
            }
        } else {
            sets.add("AR1");
            sets.add("SMG1");

            if (m >= 400 && a >= 400 && r >= 30 && p >= 30) {
                sets.add("SMG2");
            }
            if (m >= 30 && a >= 400 && r >= 400 && p >= 30) {
                sets.add("AR2");
            }
            if (m >= 300 && a >= 30 && r >= 300 && p >= 30) {
                sets.add("RF1");
            }
            if (m >= 400 && a >= 30 && r >= 400 && p >= 30) {
                sets.add("RF2");
            }
            if (m >= 400 && a >= 600 && r >= 30 && p >= 300) {
                sets.add("MG1");
            }
            if (m >= 600 && a >= 600 && r >= 100 && p >= 400) {
                sets.add("MG2");
            }
        }
        return sets;
    }

    private ArrayList<String> getSetH(int m, int a, int r, int p) {
        ArrayList<String> sets = new ArrayList<>();
        sets.add("SMG1");
        sets.add("AR1");
        if (m >= 4000 && a >= 4000 && r >= 1000 && p >= 100) {
            sets.add("SMG2");
        }
        if (m >= 1000 && a >= 4000 && r >= 4000 && p >= 1000) {
            sets.add("AR2");
        }

        if (m >= 3000 && a >= 1000 && r >= 3000 && p >= 1000) {
            sets.add("RF1");
        }

        if (m >= 4000 && a >= 1000 && r >= 4000 && p >= 1000) {
            sets.add("RF2");
        }
        if (m >= 4000 && a >= 6000 && r >= 1000 && p >= 3000) {
            sets.add("MG1");
        }
        if (m >= 6000 && a >= 6000 && r >= 1000 && p >= 4000) {
            sets.add("MG2");
        }
        if (m >= 4000 && a >= 1000 && r >= 6000 && p >= 3000) {
            sets.add("SG1");
        }
        if (m >= 6000 && a >= 1000 && r >= 6000 && p >= 4000) {
            sets.add("SG2");
        }
        return sets;
    }

    public class recipeHolder {
        String HG2, HG3, HG4, HG5;
        String SMG2, SMG3, SMG4, SMG5;
        String AR2, AR3, AR4, AR5;
        String RF2, RF3, RF4, RF5;
        String MG2, MG3, MG4, MG5;
        String SG3, SG4, SG5;

        String HG2T, HG3T, HG4T, HG5T;
        String SMG2T, SMG3T, SMG4T, SMG5T;
        String AR2T, AR3T, AR4T, AR5T;
        String RF2T, RF3T, RF4T, RF5T;
        String MG2T, MG3T, MG4T, MG5T;
        String SG3T, SG4T, SG5T;

        boolean HGC, SMGC, ARC, RFC, MGC, SGC;

        private recipeHolder() {
            clear();
        }

        private void setTabs() {
            int position = 0;
            LinearLayout result = findViewById(R.id.displayResult);
            result.setVisibility(View.VISIBLE);
            if (HGC) {
                tabs.getTabAt(position).setText("HG");
                position++;
            }
            if (SMGC) {
                tabs.getTabAt(position).setText("SMG");
                position++;
            }
            if (ARC) {
                tabs.getTabAt(position).setText("AR");
                position++;
            }
            if (RFC) {
                tabs.getTabAt(position).setText("RF");
                position++;
            }
            if (MGC) {
                tabs.getTabAt(position).setText("MG");
                position++;
            }
            if (SGC) {
                tabs.getTabAt(position).setText("SG");
                position++;
            }

            while (position < 5) {
                tabs.getTabAt(position).setText("");
                position++;
            }
            display(tabs.getTabAt(0).getText().toString());
        }

        public void display(String type) {
            TextView one = findViewById(R.id.displayR2N);
            TextView two = findViewById(R.id.displayR3N);
            TextView three = findViewById(R.id.displayR4N);
            TextView four = findViewById(R.id.displayR5N);
            TextView timeOne = findViewById(R.id.displayR2T);
            TextView timeTwo = findViewById(R.id.displayR3T);
            TextView timeThree = findViewById(R.id.displayR4T);
            TextView timeFour = findViewById(R.id.displayR5T);

            switch (type) {
                case "HG":
                    one.setText(HG2.trim());
                    two.setText(HG3.trim());
                    three.setText(HG4.trim());
                    four.setText(HG5.trim());
                    timeOne.setText(HG2T.trim());
                    timeTwo.setText(HG3T.trim());
                    timeThree.setText(HG4T.trim());
                    timeFour.setText(HG5T.trim());
                    break;
                case "SMG":
                    one.setText(SMG2.trim());
                    two.setText(SMG3.trim());
                    three.setText(SMG4.trim());
                    four.setText(SMG5.trim());
                    timeOne.setText(SMG2T.trim());
                    timeTwo.setText(SMG3T.trim());
                    timeThree.setText(SMG4T.trim());
                    timeFour.setText(SMG5T.trim());
                    break;
                case "AR":
                    one.setText(AR2.trim());
                    two.setText(AR3.trim());
                    three.setText(AR4.trim());
                    four.setText(AR5.trim());
                    timeOne.setText(AR2T.trim());
                    timeTwo.setText(AR3T.trim());
                    timeThree.setText(AR4T.trim());
                    timeFour.setText(AR5T.trim());
                    break;
                case "RF":
                    one.setText(RF2.trim());
                    two.setText(RF3.trim());
                    three.setText(RF4.trim());
                    four.setText(RF5.trim());
                    timeOne.setText(RF2T.trim());
                    timeTwo.setText(RF3T.trim());
                    timeThree.setText(RF4T.trim());
                    timeFour.setText(RF5T.trim());
                    break;
                case "MG":
                    one.setText(MG2.trim());
                    two.setText(MG3.trim());
                    three.setText(MG4.trim());
                    four.setText(MG5.trim());
                    timeOne.setText(MG2T.trim());
                    timeTwo.setText(MG3T.trim());
                    timeThree.setText(MG4T.trim());
                    timeFour.setText(MG5T.trim());
                    break;
                case "SG":
                    two.setText(SG3.trim());
                    three.setText(SG4.trim());
                    four.setText(SG5.trim());
                    timeTwo.setText(SG3T.trim());
                    timeThree.setText(SG4T.trim());
                    timeFour.setText(SG5T.trim());
                    break;
            }
        }

        public void storeData(int rarity, String set, String name, String time) {
            switch (set) {
                case "HG1":
                case "HG2":
                    storeHG(rarity, name, time);
                    HGC = true;
                    break;
                case "SMG1":
                case "SMG2":
                    storeSMG(rarity, name, time);
                    SMGC = true;
                    break;
                case "AR1":
                case "AR2":
                    storeAR(rarity, name, time);
                    ARC = true;
                    break;
                case "RF1":
                case "RF2":
                    storeRF(rarity, name, time);
                    RFC = true;
                    break;
                case "MG1":
                case "MG2":
                    storeMG(rarity, name, time);
                    MGC = true;
                    break;
                case "SG1":
                case "SG2":
                    storeSG(rarity, name, time);
                    SGC = true;
                    break;

            }
        }

        public void storeHG(int rarity, String data, String time) {
            switch (rarity) {
                case 2:
                    HG2 += data;
                    HG2T += time;
                    break;
                case 3:
                    HG3 += data;
                    HG3T += time;
                    break;
                case 4:
                    HG4 += data;
                    HG4T += time;
                    break;
                case 5:
                    HG5 += data;
                    HG5T += time;
                    break;
            }
        }

        public void storeSMG(int rarity, String data, String time) {
            switch (rarity) {
                case 2:
                    SMG2 += data;
                    SMG2T += time;
                    break;
                case 3:
                    SMG3 += data;
                    SMG3T += time;
                    break;
                case 4:
                    SMG4 += data;
                    SMG4T += time;
                    break;
                case 5:
                    SMG5 += data;
                    SMG5T += time;
                    break;
            }
        }

        public void storeAR(int rarity, String data, String time) {
            switch (rarity) {
                case 2:
                    AR2 += data;
                    AR2T += time;
                    break;
                case 3:
                    AR3 += data;
                    AR3T += time;
                    break;
                case 4:
                    AR4 += data;
                    AR4T += time;
                    break;
                case 5:
                    AR5 += data;
                    AR5T += time;
                    break;
            }
        }

        public void storeRF(int rarity, String data, String time) {
            switch (rarity) {
                case 2:
                    RF2 += data;
                    RF2T += time;
                    break;
                case 3:
                    RF3 += data;
                    RF3T += time;
                    break;
                case 4:
                    RF4 += data;
                    RF4T += time;
                    break;
                case 5:
                    RF5 += data;
                    RF5T += time;
                    break;
            }
        }

        public void storeMG(int rarity, String data, String time) {
            switch (rarity) {
                case 2:
                    MG2 += data;
                    MG2T += time;
                    break;
                case 3:
                    MG3 += data;
                    MG3T += time;
                    break;
                case 4:
                    MG4 += data;
                    MG4T += time;
                    break;
                case 5:
                    MG5 += data;
                    MG5T += time;
                    break;
            }
        }

        public void storeSG(int rarity, String data, String time) {
            switch (rarity) {
                case 3:
                    SG3 += data;
                    SG3T += time;
                    break;
                case 4:
                    SG4 += data;
                    SG4T += time;
                    break;
                case 5:
                    SG5 += data;
                    SG5T += time;
                    break;
            }
        }

        private void clear() {
            HG2 = "";
            HG3 = "";
            HG4 = "";
            HG5 = "";
            SMG2 = "";
            SMG3 = "";
            SMG4 = "";
            SMG5 = "";
            AR2 = "";
            AR3 = "";
            AR4 = "";
            AR5 = "";
            RF2 = "";
            RF3 = "";
            RF4 = "";
            RF5 = "";
            MG2 = "";
            MG3 = "";
            MG4 = "";
            MG5 = "";
            SG3 = "";
            SG4 = "";
            SG5 = "";

            HG2T = "";
            HG3T = "";
            HG4T = "";
            HG5T = "";
            SMG2T = "";
            SMG3T = "";
            SMG4T = "";
            SMG5T = "";
            AR2T = "";
            AR3T = "";
            AR4T = "";
            AR5T = "";
            RF2T = "";
            RF3T = "";
            RF4T = "";
            RF5T = "";
            MG2T = "";
            MG3T = "";
            MG4T = "";
            MG5T = "";
            SG3T = "";
            SG4T = "";
            SG5T = "";

            HGC = false;
            SMGC = false;
            ARC = false;
            RFC = false;
            MGC = false;
            SGC = false;
        }
    }
}
