package com.PrepLite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileEditActivity extends AppCompatActivity {

    private String[] Company_pair;
    private String[] pair;
    private boolean[] isSelectedCompany;
    private int company_id;
    private TextView SelectCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setTitle("Edit Profile");

        Company_pair = new String[5];
        Company_pair[0]="Microsoft";

        isSelectedCompany = new boolean[Company_pair.length];
        for(int i=0;i<isSelectedCompany.length;i++)
            isSelectedCompany[i]=false;

        SelectCompany = findViewById(R.id.alumnus_company_select_tv);
        SelectCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileEditActivity.this);
                builder.setTitle("Select Company(s)");
                //.setMultiChoiceItems()
                builder.create().show();
            }
        });

    }
}