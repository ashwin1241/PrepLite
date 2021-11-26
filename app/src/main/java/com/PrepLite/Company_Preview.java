package com.PrepLite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.PrepLite.adapters.postAdapter_CompInsti;
import com.PrepLite.adapters.postAdapter_Home;
import com.PrepLite.dataBindings.postData;

import java.util.ArrayList;

public class Company_Preview extends AppCompatActivity {

    private RecyclerView recyclerView;
    private postAdapter_CompInsti postAdapter_compInsti;
    private ArrayList<postData> post_List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_preview);
        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));
        getSupportActionBar().setHomeButtonEnabled(false);

        buildrecyclerView();
    }

    private void buildrecyclerView()
    {
        //while fetching the posts from backend, make sure to fetch only those related to this company
        post_List = new ArrayList<>();
        post_List.add(new postData("Ashwin",getIntent().getStringExtra("name"),"24-11-2021","13:02","Hello 1"));
        post_List.add(new postData("Aagam",getIntent().getStringExtra("name"),"25-11-2021","13:02","Hello 2"));
        post_List.add(new postData("Harsh",getIntent().getStringExtra("name"),"26-11-2021","13:02","Hello 3"));
        postAdapter_compInsti = new postAdapter_CompInsti(post_List,this);
        recyclerView = findViewById(R.id.company_post_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter_compInsti);

    }

}