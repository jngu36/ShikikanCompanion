package com.example.shikikancompanion;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        Intent intent;

                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        switch (menuItem.getItemId()) {
                            case R.id.recipe:
                                intent = new Intent(MainActivity.this, Recipe.class);
                                startActivity(intent);
                                break;
                            case R.id.logistic:
                                intent = new Intent(MainActivity.this, Logistic.class);
                                startActivity(intent);
                                break;
                            case R.id.battery:
                                intent = new Intent(MainActivity.this, Battery.class);
                                startActivity(intent);
                                break;
                            case R.id.List:
                                intent = new Intent(MainActivity.this, Tlist.class);
                                startActivity(intent);
                                break;
                            case R.id.about:
                                intent = new Intent(MainActivity.this, About.class);
                                startActivity(intent);
                                break;
                        }
                        return true;
                    }
                });
        WebView wb = findViewById(R.id.web);
        wb.loadUrl("https://mobile.twitter.com/GirlsFrontlineE");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}