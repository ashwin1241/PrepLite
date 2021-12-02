package com.PrepLite.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.PrepLite.OnItemClickListener;
import com.PrepLite.R;
import com.PrepLite.adapters.AttachmentAdapter;
import com.PrepLite.models.Attachment;

import java.util.ArrayList;

public class CompInstiAttachmentsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Attachment> attachmentList;
    private AttachmentAdapter attachmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_insti_attachments);
        getSupportActionBar().setTitle("Attachments");

        buildRecyclerView();

    }

    private void buildRecyclerView()
    {
        attachmentList = new ArrayList<>();
        attachmentList.add(new Attachment("File1.pdf",true,null,""));
        attachmentList.add(new Attachment("File1.txt",false,null,""));
        attachmentList.add(new Attachment("File1.ppt",false,null,""));
        attachmentList.add(new Attachment("File1.docx",false,null,""));
        recyclerView = findViewById(R.id.attachments_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        attachmentAdapter = new AttachmentAdapter(attachmentList,this);
        recyclerView.setAdapter(attachmentAdapter);

        attachmentAdapter.setOnAttachmentClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                boolean isDownloaded = attachmentList.get(position).isDownloadStat();
                //code to check if attachment is downloaded and update the boolean isDownloaded
                if(!isDownloaded)
                {
                    Toast.makeText(CompInstiAttachmentsActivity.this, "Downloading "+attachmentList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                    attachmentList.get(position).setDownloadStat(true);
                    attachmentAdapter.notifyDataSetChanged();
                }
                else
                {
                    //code to view the attachment using appropriate app
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(attachmentList.get(position).getDownloadedFile());
                    intent.setType("*/*");
                    startActivity(Intent.createChooser(intent,"Open file with"));
                }
            }
        });

    }

}