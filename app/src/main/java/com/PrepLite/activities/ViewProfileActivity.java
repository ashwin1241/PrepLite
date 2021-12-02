package com.PrepLite.activities;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.PrepLite.R;
import com.PrepLite.models.User;

public class ViewProfileActivity extends AppCompatActivity {

    private TextView Name;
    private TextView batch;
    private TextView user_posts;
    private TextView send_messages;
    private String userImagePath;
    private ImageView userImage;
    private Uri userImageUri;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        user = getIntent().getParcelableExtra("user");

        user_posts= findViewById(R.id.user_posts_view);
        send_messages=findViewById(R.id.send_messages_view);
        userImage=findViewById(R.id.profile_pic);



    }


}