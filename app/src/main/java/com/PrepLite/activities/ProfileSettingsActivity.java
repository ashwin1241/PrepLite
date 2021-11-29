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
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.PrepLite.R;

public class ProfileSettingsActivity extends AppCompatActivity {

    private Switch notifs;
    private RatingBar rating;
    private String rating_value;
    private TextView feedback;
    private TextView about;
    private TextView delete_acct;
    private TextView change_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Settings");

        notifs = findViewById(R.id.notif_switch);
        rating = findViewById(R.id.rating);
        rating_value=String.valueOf(rating.getRating());
        feedback = findViewById(R.id.feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = getLayoutInflater().inflate(R.layout.feedback_edittext,null);
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileSettingsActivity.this);
                builder.setTitle("Feedback")
                .setMessage("Enter your feedback here: ")
                .setView(v)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText fm = v.findViewById(R.id.feedback_et);
                        String feedback_text = fm.getText().toString().trim();
                        if(feedback_text.length()>0)
                        {
                            //backend code to send feedback
                            Toast.makeText(ProfileSettingsActivity.this, "Feedback submitted successfully!", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(ProfileSettingsActivity.this, "Cannot send empty feedback!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                ;
                builder.create().show();
            }
        });
        about = findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileSettingsActivity.this, AboutPageActivity.class);
                startActivity(intent);
            }
        });
        delete_acct = findViewById(R.id.delete_account);
        delete_acct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = getLayoutInflater().inflate(R.layout.pswrd_et,null);
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileSettingsActivity.this);
                builder.setTitle("Delete account")
                .setMessage("Are you sure you want to delete this account?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialog.Builder pswrd_confirmation = new AlertDialog.Builder(ProfileSettingsActivity.this);
                        pswrd_confirmation.setTitle("Confirm your password")
                        .setView(view1)
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText password = view1.findViewById(R.id.pswrd_et);
                                String pswrd = password.getText().toString().trim();
                                //backend code to validate password and delete account
                                //Toast.makeText(Profile_Settings.this, pswrd, Toast.LENGTH_SHORT).show();
                                Toast.makeText(ProfileSettingsActivity.this, "Account deleted successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ProfileSettingsActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        pswrd_confirmation.create().show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            }
        });
        change_password = findViewById(R.id.change_password);
        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view12 = getLayoutInflater().inflate(R.layout.password_change_view,null);
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileSettingsActivity.this);
                builder.setTitle("Change Password")
                .setView(view12)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText old_pswrd = view12.findViewById(R.id.old_pswrd);
                        EditText new_pswrd = view12.findViewById(R.id.new_pswrd);
                        String old_password,new_password;
                        old_password=old_pswrd.getText().toString().trim();
                        new_password=new_pswrd.getText().toString().trim();
                        if(old_password.equals(new_password))
                        {
                            //backend code to update password
                            Toast.makeText(ProfileSettingsActivity.this, "Password updated successfully!", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(ProfileSettingsActivity.this, "Old and new passwords do not match!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            }
        });

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
                Toast.makeText(ProfileSettingsActivity.this, "Changes saved successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProfileSettingsActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
            default:return super.onOptionsItemSelected(item);
        }
    }
}