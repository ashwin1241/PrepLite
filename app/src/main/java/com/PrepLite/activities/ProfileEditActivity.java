package com.PrepLite.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.PrepLite.R;

import java.util.ArrayList;

public class ProfileEditActivity extends AppCompatActivity {

    private ArrayList<String> companies;
    private ArrayList<String> selectedCompanies;
    private boolean[] isSelectedCompany;
    private TextView SelectCompany;
    private ArrayList<String> institutes;
    private ArrayList<String> selectedInstitutes;
    private boolean[] isSelectedInstitute;
    private TextView SelectInstitute;
    private ArrayList<String> editedCompanies;
    private ArrayList<String> editedInstitutes;
    private TextView insti_hdg;
    private TextView company_hdg;
    private RadioGroup alumnusSeletorGroup;
    private RadioButton alumnusSelection;
    private EditText name_edit;
    private EditText username_edit;
    private EditText contact_edit;
    private EditText email_edit;
    private EditText dob_edit;
    private EditText about_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setTitle("Edit Profile");

        SelectInstitute = findViewById(R.id.alumnus_institute_select_tv);
        SelectCompany = findViewById(R.id.alumnus_company_select_tv);
        insti_hdg = findViewById(R.id.alumnus_insti_edit_hdg);
        company_hdg = findViewById(R.id.alumnus_company_edit_hdg);
        alumnusSeletorGroup = findViewById(R.id.alumnus_select_spinner);

        name_edit = findViewById(R.id.name_edit_et);
        username_edit = findViewById(R.id.username_edit_et);
        contact_edit = findViewById(R.id.contact_edit_et);
        email_edit = findViewById(R.id.email_edit_et);
        dob_edit = findViewById(R.id.dob_edit_et);
        about_edit = findViewById(R.id.about_edit_et);

    }

    public void radioChecked(View v)
    {
        alumnusSelection = findViewById(alumnusSeletorGroup.getCheckedRadioButtonId());
        if(alumnusSelection.getText().equals("No"))
        {
            insti_hdg.setVisibility(View.INVISIBLE);
            company_hdg.setVisibility(View.INVISIBLE);
            SelectInstitute.setVisibility(View.INVISIBLE);
            SelectCompany.setVisibility(View.INVISIBLE);
            selectedInstitutes = new ArrayList<>();
            selectedCompanies = new ArrayList<>();
        }
        else
        {
            insti_hdg.setVisibility(View.VISIBLE);
            company_hdg.setVisibility(View.VISIBLE);
            SelectInstitute.setVisibility(View.VISIBLE);
            SelectCompany.setVisibility(View.VISIBLE);
            editedCompanies = fetch_selected_companies();
            editedInstitutes = fetch_selected_institutes();
        }
    }

    private ArrayList<String> fetch_selected_companies()
    {
        companies = new ArrayList<>();
        companies.add("Microsoft");
        companies.add("Amazon");
        companies.add("CodeNation");
        companies.add("Cisco");
        companies.add("Oracle");
        selectedCompanies = new ArrayList<>();

        isSelectedCompany = new boolean[companies.size()];
        for(int i=0;i<isSelectedCompany.length;i++)
            isSelectedCompany[i]=false;

        SelectCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileEditActivity.this);
                builder.setTitle("Select Company(s)")
                        .setMultiChoiceItems(companies.toArray(new String[companies.size()]), isSelectedCompany, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                if(b)
                                {
                                    selectedCompanies.add(companies.get(i));
                                }
                                else
                                {
                                    selectedCompanies.remove(companies.get(i));
                                }
                            }
                        })
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(selectedCompanies.size()>0)
                                {
                                    String s=selectedCompanies.get(0);
                                    for(int k=1;k<selectedCompanies.size();k++)
                                    {
                                        s+="\n"+selectedCompanies.get(k);
                                    }
                                    SelectCompany.setText(s);
                                }
                                else
                                {
                                    selectedCompanies = new ArrayList<>();
                                    SelectCompany.setText("Select Company");
                                }
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
                                isSelectedCompany = new boolean[companies.size()];
                                for(int l=0;l<isSelectedCompany.length;l++)
                                    isSelectedCompany[l]=false;
                                selectedCompanies = new ArrayList<>();
                                SelectCompany.setText("Select Company");
                            }
                        });
                builder.create().show();
            }
        });
        return selectedCompanies;
    }

    private ArrayList<String> fetch_selected_institutes()
    {
        institutes = new ArrayList<>();
        institutes.add("Harvard");
        institutes.add("Yale");
        institutes.add("Ivy League");
        institutes.add("Oxford");
        institutes.add("CIT");
        selectedInstitutes = new ArrayList<>();

        isSelectedInstitute = new boolean[institutes.size()];
        for(int i=0;i<isSelectedInstitute.length;i++)
            isSelectedInstitute[i]=false;

        SelectInstitute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileEditActivity.this);
                builder.setTitle("Select Institute(s)")
                        .setMultiChoiceItems(institutes.toArray(new String[institutes.size()]), isSelectedInstitute, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                if(b)
                                {
                                    selectedInstitutes.add(institutes.get(i));
                                }
                                else
                                {
                                    selectedInstitutes.remove(institutes.get(i));
                                }
                            }
                        })
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(selectedInstitutes.size()>0)
                                {
                                    String s=selectedInstitutes.get(0);
                                    for(int k=1;k<selectedInstitutes.size();k++)
                                    {
                                        s+="\n"+selectedInstitutes.get(k);
                                    }
                                    SelectInstitute.setText(s);
                                }
                                else
                                {
                                    selectedInstitutes = new ArrayList<>();
                                    SelectInstitute.setText("Select Institute");
                                }
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
                                isSelectedInstitute = new boolean[institutes.size()];
                                for(int l=0;l<isSelectedInstitute.length;l++)
                                    isSelectedInstitute[l]=false;
                                selectedInstitutes = new ArrayList<>();
                                SelectInstitute.setText("Select Institute");
                            }
                        });
                builder.create().show();
            }
        });
        return selectedInstitutes;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.save_changes_profile_settings://backend code to save rating and notifications status
                Toast.makeText(ProfileEditActivity.this, "Changes saved successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProfileEditActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
            default:return super.onOptionsItemSelected(item);
        }
    }

}