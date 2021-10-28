package com.PrepLite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Fragment selectedFragment = null;
    private String pagetitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Fragment_feed()).commit();
        getSupportActionBar().setTitle("Feed");

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId())
            {
                case R.id.nav_feed: selectedFragment = new Fragment_feed();getSupportActionBar().setTitle("Feed");
                break;
                case R.id.nav_institute: selectedFragment = new Fragment_institute();getSupportActionBar().setTitle("Institute");
                break;
                case R.id.nav_company: selectedFragment = new Fragment_company();getSupportActionBar().setTitle("Company");
                break;
                case R.id.nav_chat: selectedFragment = new Fragment_chat();getSupportActionBar().setTitle("Chat");
                break;
                case R.id.nav_profile: selectedFragment = new Fragment_profile();getSupportActionBar().setTitle("Profile");
                break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };

}