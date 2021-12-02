package com.PrepLite.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.PrepLite.ApiCalls;
import com.PrepLite.Client;
import com.PrepLite.OnItemClickListener;
import com.PrepLite.R;
import com.PrepLite.adapters.PostAdapterCompInsti;
import com.PrepLite.models.Company;
import com.PrepLite.models.Post;
import com.PrepLite.models.ServerResponse;
import com.PrepLite.models.University;
import com.PrepLite.models.User;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyPreviewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostAdapterCompInsti postAdapter_compInsti;
    private ArrayList<Post> post_List;
    private Toolbar toolbar;
    private Company company;
    private ImageView company_logo;
    private ImageButton add_post;
    private ImageButton attachments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_preview);

        company = getIntent().getParcelableExtra("company");

        toolbar = findViewById(R.id.toolbar_title_company);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(company.getCompanyName());
        getSupportActionBar().setHomeButtonEnabled(false);


        company_logo = findViewById(R.id.toolbar_image_company);
        Glide.with(this).load(company.getCompanyLogo()).fitCenter().placeholder(R.drawable.ic_baseline_hourglass_top_24).into(company_logo);

        add_post = findViewById(R.id.add_company_post);
        add_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompanyPreviewActivity.this,AddPostActivity.class);
                intent.putExtra("company", company);
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

        post_List = new ArrayList<>();
        postAdapter_compInsti = new PostAdapterCompInsti(post_List,this);
        recyclerView = findViewById(R.id.company_post_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter_compInsti);

        postAdapter_compInsti.setOnPostCompInstiClickListener(new OnItemClickListener() {
            @Override
            public void OnItemLongClicked(int position) {
                //need to add backend code here to check if the user is the one who posted this post
                deletePost(position);
            }

            @Override
            public void OnCommentClicked(int position, int flag) {
                super.OnCommentClicked(position, flag);
                Intent intent = new Intent(CompanyPreviewActivity.this, CommentsActivity.class);
                intent.putExtra("postId", post_List.get(position).getPostId());
                startActivity(intent);
            }

            @Override
            public void OnItemClicked(int position, int flag) {
                super.OnItemClicked(position, flag);
                if(flag==1)
                {
                    Intent intent = new Intent(CompanyPreviewActivity.this,ViewProfileActivity.class);
                    intent.putExtra("user",post_List.get(position).getUser());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        post_List.clear();
        retrievePosts();
    }

    private void deletePost(int position)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Post")
        .setMessage("Are you sure you want to delete this post?")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //backend code to actually delete this post
                post_List.remove(position);
                postAdapter_compInsti.notifyItemRemoved(position);
            }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    private void retrievePosts() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("company_id", company.getCompanyId());

        Call<ServerResponse> call = Client.getRetrofitInstance().create(ApiCalls.class).retrieveCompanyPosts(map);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse = response.body();
                if (serverResponse != null) {
                    if (!serverResponse.isError()) {
                        post_List.addAll(serverResponse.getResult().getPosts());
                        postAdapter_compInsti.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }

}