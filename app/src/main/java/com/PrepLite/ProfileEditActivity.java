package com.PrepLite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class ProfileEditActivity extends AppCompatActivity {

    private ArrayList<String[]> Company_pair;
    private String[] pair;
    private boolean[] isSelectedCompany;
    private int company_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setTitle("Edit Profile");

        Company_pair = new ArrayList<>();
        pair = new String[2];
        pair[0]="Microsoft";
        pair[1]=Integer.toString(company_id);
        Company_pair.add(pair);
        pair[0]="Amazon";
        pair[1]=Integer.toString(company_id);
        Company_pair.add(pair);
        pair[0]="Oracle";
        pair[1]=Integer.toString(company_id);
        Company_pair.add(pair);
        pair[0]="Cisco";
        pair[1]=Integer.toString(company_id);
        Company_pair.add(pair);

        isSelectedCompany = new boolean[Company_pair.size()];

    }
}