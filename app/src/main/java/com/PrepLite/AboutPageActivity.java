package com.PrepLite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AboutPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);
        getSupportActionBar().setTitle("About");
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}