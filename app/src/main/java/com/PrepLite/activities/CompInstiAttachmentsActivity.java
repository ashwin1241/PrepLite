package com.PrepLite.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.PrepLite.R;

public class CompInstiAttachmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_insti_attachments);
        getSupportActionBar().setTitle("Attachments");
    }
}