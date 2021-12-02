package com.PrepLite.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;

import com.PrepLite.R;
import com.PrepLite.fragments.ChatFragment;
import com.PrepLite.fragments.CompanyFragment;
import com.PrepLite.fragments.FeedFragment;
import com.PrepLite.fragments.InstituteFragment;
import com.PrepLite.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Fragment selectedFragment = null;
    private String pagetitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FeedFragment()).commit();
        getSupportActionBar().setTitle("Feed");

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId())
            {
                case R.id.nav_feed: selectedFragment = new FeedFragment();getSupportActionBar().setTitle("Feed");
                break;
                case R.id.nav_institute: selectedFragment = new InstituteFragment();getSupportActionBar().setTitle("Institute");
                break;
                case R.id.nav_company: selectedFragment = new CompanyFragment();getSupportActionBar().setTitle("Company");
                break;
                case R.id.nav_chat: selectedFragment = new ChatFragment();getSupportActionBar().setTitle("Chat");
                break;
                case R.id.nav_profile: selectedFragment = new ProfileFragment();getSupportActionBar().setTitle("Profile");
                break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit")
        .setMessage("Are you sure you want to exit the app?")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                System.exit(0);
            }
        })
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}