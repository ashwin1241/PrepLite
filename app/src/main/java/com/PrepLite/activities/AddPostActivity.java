package com.PrepLite.activities;

import static com.PrepLite.prefs.SharedPrefsConstants.ID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.PrepLite.ApiCalls;
import com.PrepLite.Client;
import com.PrepLite.OnItemClickListener;
import com.PrepLite.R;
import com.PrepLite.adapters.AddPostAttachmentAdapter;
import com.PrepLite.models.Attachment;
import com.PrepLite.models.Company;
import com.PrepLite.models.ServerResponse;
import com.PrepLite.models.University;
import com.PrepLite.prefs.SharedPrefs;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPostActivity extends AppCompatActivity {

    private TextView send_post;
    private EditText post_content;
    private String content;
    private ArrayList<Attachment> attachments124;
    private RecyclerView recyclerView;
    private AddPostAttachmentAdapter adapter;
    private Company company;
    private University university;
    private MultipartBody.Part multipartBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        getSupportActionBar().setTitle("Create a Post");

        attachments124 = new ArrayList<>();
        company = getIntent().getParcelableExtra("company");
        university = getIntent().getParcelableExtra("university");

        buildrecyclerView();

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
                //use this attachmentList to upload the file(s) (pdf/ppt/docx/txt/etc..)
                addPost(content, company, university);
            }
        });

    }

    private void buildrecyclerView()
    {
        recyclerView = findViewById(R.id.add_post_attachment_list);
        adapter = new AddPostAttachmentAdapter(attachments124,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListenerAttachment(new OnItemClickListener() {
            @Override
            public void OnItemClicked(int position, int flag) {
                switch (flag)
                {
                    case 0:
                        attachments124.remove(position);
                        adapter.notifyItemRemoved(position);
                        break;
                    case 1:
                        openFile(attachments124.get(position));
                }
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
                        addAttachments();
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

    private void addAttachments() {

    }

    private void openFile(Attachment attachment)
    {
        try
        {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(attachment.getDownloadedFile());
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(intent,"Open file with:"));
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 103 && resultCode == RESULT_OK)
        {
            try
            {
                assert data != null;
                Uri fileData = data.getData();
                String filePath = fileData.getPath();

                String fileName = queryName(getContentResolver(),fileData);
                attachments124.add(new Attachment(fileName,true,fileData,filePath));
                adapter.notifyItemInserted(attachments124.size()-1);

                //File file = new File(filePath);

                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), filePath);
                multipartBody = MultipartBody.Part.createFormData("file", fileName, requestFile);

            }
            catch (Exception e)
            {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String queryName(ContentResolver resolver, Uri uri) {
        Cursor returnCursor =
                resolver.query(uri, null, null, null, null);
        assert returnCursor != null;
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        String name = returnCursor.getString(nameIndex);
        returnCursor.close();
        return name;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_post_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.file_attachments) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            startActivityForResult(Intent.createChooser(intent, "Select files: "), 103);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}