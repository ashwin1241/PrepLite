package com.PrepLite.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.PrepLite.OnItemClickListener;
import com.PrepLite.R;
import com.PrepLite.adapters.attachmentAdapter;
import com.PrepLite.models.Attachment;

import java.util.ArrayList;

public class CompInstiAttachmentsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Attachment> attachmentList;
    private attachmentAdapter attachmentAdapter;

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
        attachmentList.add(new Attachment("File1.pdf",false));
        attachmentList.add(new Attachment("File1.txt",false));
        attachmentList.add(new Attachment("File1.ppt",false));
        attachmentList.add(new Attachment("File1.docx",false));
        recyclerView = findViewById(R.id.attachments_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        attachmentAdapter = new attachmentAdapter(attachmentList,this);
        recyclerView.setAdapter(attachmentAdapter);

        attachmentAdapter.setOnAttachmentClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                boolean isDownloaded = true;
                //code to check if attachment is downloaded and update the boolean isDownloaded
                if(!isDownloaded)
                {
                    attachmentList.get(position).setDownloadStat(true);
                    attachmentAdapter.notifyDataSetChanged();
                }
                else
                {
                    //code to view the attachment using appropriate app
                }
            }
        });

    }

}