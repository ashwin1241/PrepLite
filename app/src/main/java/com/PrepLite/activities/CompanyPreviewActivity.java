package com.PrepLite.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.PrepLite.R;
import com.PrepLite.adapters.postAdapter_CompInsti;
import com.PrepLite.models.Post;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CompanyPreviewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private postAdapter_CompInsti postAdapter_compInsti;
    private ArrayList<Post> post_List;
    private Toolbar toolbar;
    private String title;
    private String logo;
    private ImageView company_logo;
    private ImageButton add_post;
    private ImageButton attachments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_preview);

        toolbar = findViewById(R.id.toolbar_title_company);
        setSupportActionBar(toolbar);

        title = getIntent().getStringExtra("name");
        logo = getIntent().getStringExtra("logo");

        company_logo = findViewById(R.id.toolbar_image_company);
        Glide.with(this).load(logo).fitCenter().placeholder(R.drawable.ic_baseline_hourglass_top_24).into(company_logo);

        getSupportActionBar().setTitle(title);
        getSupportActionBar().setHomeButtonEnabled(false);

        buildrecyclerView();

        add_post = findViewById(R.id.add_company_post);
        add_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompanyPreviewActivity.this,AddPostActivity.class);
                startActivity(intent);
            }
        });
        attachments = findViewById(R.id.company_attachments);
        attachments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompanyPreviewActivity.this,CompInstiAttachmentsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void buildrecyclerView()
    {
        //while fetching the posts from backend, make sure to fetch only those related to this company
        post_List = new ArrayList<>();
//        post_List.add(new postData("Ashwin","Amazon","24-11-2021","13:02","Hello 1","",true));
//        post_List.add(new postData("Aagam","Cisco","25-11-2021","13:02","Hello 2","",true));
//        post_List.add(new postData("Harsh","GE","26-11-2021","13:02","Hello 3","",true));
        postAdapter_compInsti = new postAdapter_CompInsti(post_List,this);
        recyclerView = findViewById(R.id.company_post_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter_compInsti);

    }

}