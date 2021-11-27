package com.PrepLite;

import static com.PrepLite.app.Constants.STANFORD_LOGO;
import static com.PrepLite.app.Constants.YALE_LOGO;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.PrepLite.adapters.postAdapter_CompInsti;
import com.PrepLite.dataBindings.postData;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class InstitutePreviewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private postAdapter_CompInsti postAdapter_compInsti;
    private ArrayList<postData> post_List;
    private Toolbar toolbar;
    private String title;
    private String logo;
    private ImageView insti_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_preview);

        toolbar = findViewById(R.id.toolbar_title_insti);
        setSupportActionBar(toolbar);

        title = getIntent().getStringExtra("name");
        logo = getIntent().getStringExtra("logo");

        insti_logo = findViewById(R.id.toolbar_image_insti);
        Glide.with(this).load(logo).fitCenter().placeholder(R.drawable.ic_baseline_hourglass_top_24).into(insti_logo);

        getSupportActionBar().setTitle(title);
        getSupportActionBar().setHomeButtonEnabled(false);

        buildrecyclerView();
    }

    private void buildrecyclerView()
    {

        post_List = new ArrayList<>();
        post_List.add(new postData("Ashwin","Stanford","24-11-2021","13:02","Hello 1",""));
        post_List.add(new postData("Aagam","Yale","25-11-2021","13:02","Hello 2",""));
        post_List.add(new postData("Harsh","Oxford","26-11-2021","13:02","Hello 3",""));
        postAdapter_compInsti = new postAdapter_CompInsti(post_List,this);
        recyclerView = findViewById(R.id.institute_post_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter_compInsti);

    }
}