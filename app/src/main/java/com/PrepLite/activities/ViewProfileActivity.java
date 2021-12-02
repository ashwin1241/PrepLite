package com.PrepLite.activities;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.PrepLite.R;

public class ViewProfileActivity extends AppCompatActivity {

    private TextView user_companies;
    private TextView user_institutes;
    private TextView user_posts;
    private TextView send_messages;
    private String userImagePath;
    private ImageView userImage;
    private Uri userImageUri;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);


        user_companies= findViewById(R.id.user_companies);
        user_institutes=findViewById(R.id.user_institutes);
        user_posts= findViewById(R.id.user_posts);
        send_messages=findViewById(R.id.send_messages);
        userImage=findViewById(R.id.user_pic);















    }


}