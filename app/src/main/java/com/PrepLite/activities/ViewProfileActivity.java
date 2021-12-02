package com.PrepLite.activities;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.PrepLite.R;
import com.PrepLite.models.User;
import com.bumptech.glide.Glide;

public class ViewProfileActivity extends AppCompatActivity {

    private TextView name;
    private TextView batch;
    private TextView email;
    private TextView contact;
    private TextView user_posts;
    private TextView send_messages;
    private ImageView userImage;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        user = getIntent().getParcelableExtra("user");

        user_posts= findViewById(R.id.user_posts_view);
        send_messages=findViewById(R.id.send_messages_view);
        userImage=findViewById(R.id.profile_pic);
        name = findViewById(R.id.full_name_profile);
        batch = findViewById(R.id.batch_profile);
        email = findViewById(R.id.email_profile);
        contact = findViewById(R.id.contact_profile);

        name.setText(user.getUsername());
        batch.setText(user.getBatch());
        email.setText(user.getEmail());
        contact.setText(user.getPhone());
        Glide.with(this).load(user.getProfileImage()).placeholder(R.drawable.ic_baseline_person_24).into(userImage);

    }


}