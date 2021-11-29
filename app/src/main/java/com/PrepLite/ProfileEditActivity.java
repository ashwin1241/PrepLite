package com.PrepLite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class ProfileEditActivity extends AppCompatActivity {

    private String[] companies;
    private ArrayList<Integer> compList;
    private boolean[] isSelectedCompany;
    private int company_id;
    private TextView SelectCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setTitle("Edit Profile");

        companies = new String[5];
        companies[0]="Microsoft";
        companies[1]="Amazon";
        companies[2]="Oracle";
        companies[3]="CodeNation";
        companies[4]="Cisco";

        compList = new ArrayList<>();

        isSelectedCompany = new boolean[companies.length];
        for(int i=0;i<isSelectedCompany.length;i++)
            isSelectedCompany[i]=false;

        SelectCompany = findViewById(R.id.alumnus_company_select_tv);
        SelectCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileEditActivity.this);
                builder.setTitle("Select Company(s)")
                .setMultiChoiceItems(companies, isSelectedCompany, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b)
                        {
                            compList.add(i);
                        }
                        else
                        {
                            compList.remove(i);
                        }
                    }
                })
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(compList.size()>0)
                        {
                            String s=companies[compList.get(0)];
                            for(int k=1;k<compList.size();k++)
                            {
                                s+=", "+companies[compList.get(k)];
                            }
                            SelectCompany.setText(s);
                        }
                        else
                            SelectCompany.setText("Select Company");
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        isSelectedCompany = new boolean[companies.length];
                        compList = new ArrayList<>();
                        SelectCompany.setText("Select Company");
                    }
                });
                builder.create().show();
            }
        });

    }
}