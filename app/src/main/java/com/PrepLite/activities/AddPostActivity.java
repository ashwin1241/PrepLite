package com.PrepLite.activities;

import static com.PrepLite.prefs.SharedPrefsConstants.ID;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.PrepLite.ApiCalls;
import com.PrepLite.Client;
import com.PrepLite.R;
import com.PrepLite.models.Company;
import com.PrepLite.models.ServerResponse;
import com.PrepLite.models.University;
import com.PrepLite.prefs.SharedPrefs;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPostActivity extends AppCompatActivity {

    private TextView send_post;
    private EditText post_content;
    private String content;
    private ImageButton addAttachments;
    private String attachments;

    private Company company;
    private University university;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        getSupportActionBar().setTitle("Create a Post");

        company = getIntent().getParcelableExtra("company");
        university = getIntent().getParcelableExtra("university");

        post_content = findViewById(R.id.add_post_et);
        send_post = findViewById(R.id.add_post_post);
        send_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content = post_content.getText().toString().trim();
                if (content.isEmpty()) {
                    Toast.makeText(AddPostActivity.this, "Cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                addPost(content, company, university);
            }
        });
        addAttachments = findViewById(R.id.file_attachments);
        addAttachments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("*/*");
                startActivityForResult(Intent.createChooser(intent,"Select files: "),103);
            }
        });

    }

    private void addPost(String content, Company company, University university) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_id", SharedPrefs.getIntParams(this, ID));
        if (company != null)
            map.put("company_id", company.getCompanyId());
        if (university != null)
            map.put("university_id", university.getUniversityId());
        map.put("content", content);

        Call<ServerResponse> call = Client.getRetrofitInstance().create(ApiCalls.class).createPost(map);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse = response.body();
                if(serverResponse != null) {
                    if (!serverResponse.isError()) {
                        Toast.makeText(AddPostActivity.this, "Post is up!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==103&&resultCode==RESULT_OK)
        {

        }
    }
}