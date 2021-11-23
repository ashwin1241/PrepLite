package com.PrepLite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Profile_Settings extends AppCompatActivity {

    private Switch notifs;
    private RatingBar rating;
    private String rating_value;
    private TextView feedback;

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
                AlertDialog.Builder builder = new AlertDialog.Builder(Profile_Settings.this);
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
                            Toast.makeText(Profile_Settings.this, "Feedback submitted successfully!", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(Profile_Settings.this, "Cannot send empty feedback!", Toast.LENGTH_SHORT).show();
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

    }
}