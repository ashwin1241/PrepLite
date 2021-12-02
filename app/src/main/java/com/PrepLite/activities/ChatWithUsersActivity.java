package com.PrepLite.activities;

import static com.PrepLite.prefs.SharedPrefsConstants.ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.PrepLite.ApiCalls;
import com.PrepLite.Client;
import com.PrepLite.OnItemClickListener;
import com.PrepLite.Progress;
import com.PrepLite.R;
import com.PrepLite.adapters.ChatDisplayAdapter;
import com.PrepLite.models.Chat;
import com.PrepLite.models.ServerResponse;
import com.PrepLite.prefs.SharedPrefs;
import com.PrepLite.models.User;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatWithUsersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChatDisplayAdapter adapter;
    private ArrayList<Chat> users;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_with_users);
        progress = Progress.getProgressDialog(this);
        Progress.showProgress(true,"Fetching Users...");
        buildRecyclerView();
        retrieveUsers();

    }

    private void retrieveUsers() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("user_id", SharedPrefs.getIntParams(this, ID));

        Call<ServerResponse> call = Client.getRetrofitInstance().create(ApiCalls.class).retrieveUsers(map);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                Progress.dismissProgress(progress);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });

    }

    private void buildRecyclerView()
    {
        users = new ArrayList<>();
        recyclerView = findViewById(R.id.chat_first_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChatDisplayAdapter(users,this);
        recyclerView.setAdapter(adapter);

        adapter.setOnChatClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClicked(int position, int flag) {
                super.OnItemClicked(position, flag);
            }
        });
    }

}