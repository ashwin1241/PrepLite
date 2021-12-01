package com.PrepLite.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.PrepLite.R;

public class AddPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        getSupportActionBar().setTitle("Create a Post");
    }
}