package com.PrepLite.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.PrepLite.R;

public class MainActivity extends AppCompatActivity {

    protected ImageView logo;
    protected TextView tagline;
    protected Animation logo_anim;
    protected Animation tagline_anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.logo_splashscreen);
        tagline = findViewById(R.id.tagline_splashscreen);

        logo_anim = AnimationUtils.loadAnimation(this,R.anim.logo_animation);
        tagline_anim = AnimationUtils.loadAnimation(this,R.anim.tagline_animation);

        logo.setAnimation(logo_anim);
        tagline.setAnimation(tagline_anim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        },3000);

    }
}