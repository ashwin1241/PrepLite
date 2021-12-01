package com.PrepLite.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.PrepLite.R;

public class AddPostActivity extends AppCompatActivity {

    private TextView send_post;
    private EditText post_content;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        getSupportActionBar().setTitle("Create a Post");

        post_content = findViewById(R.id.add_post_et);
        send_post = findViewById(R.id.add_post_post);
        send_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content = post_content.getText().toString().trim();
                // backend code to actually send the post
                Toast.makeText(AddPostActivity.this, "Post is up!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}