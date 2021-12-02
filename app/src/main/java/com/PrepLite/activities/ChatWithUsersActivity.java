package com.PrepLite.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.PrepLite.ApiCalls;
import com.PrepLite.Client;
import com.PrepLite.R;
import com.PrepLite.models.ServerResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatWithUsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_with_users);

        retrieveUsers();

    }

    private void retrieveUsers() {

        HashMap<String, Integer> map = new HashMap<>();
        Call<ServerResponse> call = Client.getRetrofitInstance().create(ApiCalls.class).retrieveUsers(map);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });

    }
}