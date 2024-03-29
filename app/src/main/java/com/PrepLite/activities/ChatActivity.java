package com.PrepLite.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.PrepLite.R;
import com.PrepLite.adapters.ChatMainAdapter;
import com.PrepLite.models.ChatP2P;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ChatActivity extends AppCompatActivity {

    private ArrayList<ChatP2P> messageList;
    private String username;
    private RecyclerView recyclerView;
    private ChatMainAdapter chatMainAdapter;
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

                date = new SimpleDateFormat("dd/mm/yy", Locale.getDefault()).format(new Date());
                time = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                message = chat_message.getText().toString().trim();
                if(messageList.size()==0||messageList.get(messageList.size()-1).isSender())
                messageList.add(new ChatP2P("28/11/2021 19:20",message,true,false));
                else
                messageList.add(new ChatP2P("28/11/2021 19:21",message,true,true));
                chatMainAdapter.notifyItemInserted(messageList.size());
                send_message(username,message);
                chat_message.setText("");
            }
        });

    }

    private void buildRecyclerView()
    {
        messageList = new ArrayList<>();
        messageList.add(new ChatP2P("28/11/2021 19:20","Test message 1 sender",true,true));
        messageList.add(new ChatP2P("28/11/2021 19:21","Test message 2 receiver",false,true));
        messageList.add(new ChatP2P("28/11/2021 19:22","Test message 3 receiver",false,false));
        recyclerView = findViewById(R.id.comments_recycler_view);
        chatMainAdapter = new ChatMainAdapter(messageList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(chatMainAdapter);
    }

    private void send_message(String to_username, String message)
    {
        //backend code to actually send "message" to "to_username"
    }

}