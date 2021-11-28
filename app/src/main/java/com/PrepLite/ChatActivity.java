package com.PrepLite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.PrepLite.adapters.chatMainAdapter;
import com.PrepLite.dataBindings.chatMainData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    private ArrayList<chatMainData> messageList;
    private String username;
    private RecyclerView recyclerView;
    private chatMainAdapter chatMainAdapter;
    private ImageButton send_message;
    private EditText chat_message;
    private String message;
    private String date;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        username = getIntent().getStringExtra("username");
        getSupportActionBar().setTitle(username);
        getSupportActionBar().setHomeButtonEnabled(false);

        buildRecyclerView();

        chat_message = findViewById(R.id.comment_text);
        send_message = findViewById(R.id.comment_send);
        send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date = new SimpleDateFormat("dd/mm/yy").format(new Date());
                time = new SimpleDateFormat("HH:mm").format(new Date());
                message = chat_message.getText().toString().trim();
                if(messageList.size()==0||messageList.get(messageList.size()-1).isSender())
                messageList.add(new chatMainData(date,time,message,true,false));
                else
                messageList.add(new chatMainData(date,time,message,true,true));
                chatMainAdapter.notifyItemInserted(messageList.size());
                send_message(username,message);
                chat_message.setText("");
            }
        });

    }

    private void buildRecyclerView()
    {
        messageList = new ArrayList<>();
        messageList.add(new chatMainData("28/11/2021","19:20","Test message 1 sender",true,true));
        messageList.add(new chatMainData("28/11/2021","19:21","Test message 2 receiver",false,true));
        messageList.add(new chatMainData("28/11/2021","19:22","Test message 3 receiver",false,false));
        recyclerView = findViewById(R.id.comments_recycler_view);
        chatMainAdapter = new chatMainAdapter(messageList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(chatMainAdapter);
    }

    private void send_message(String to_username, String message)
    {
        //backend code to actually send "message" to "to_username"
    }

}