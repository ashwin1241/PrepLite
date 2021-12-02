package com.PrepLite.fragments;

import static com.PrepLite.prefs.SharedPrefsConstants.ID;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.PrepLite.ApiCalls;
import com.PrepLite.Client;
import com.PrepLite.OnItemClickListener;
import com.PrepLite.Progress;
import com.PrepLite.activities.ChatActivity;
import com.PrepLite.R;
import com.PrepLite.activities.ChatWithUsersActivity;
import com.PrepLite.adapters.ChatDisplayAdapter;
import com.PrepLite.models.Chat;
import com.PrepLite.models.ServerResponse;
import com.PrepLite.prefs.SharedPrefs;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatFragment extends Fragment {

    private ArrayList<Chat> chatList;
    private ChatDisplayAdapter chatDisplayAdapter;
    private ProgressDialog progress;
    private ImageButton chat_with_user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat,container,false);
        chat_with_user = view.findViewById(R.id.chat_with_users);
        progress = Progress.getProgressDialog(requireContext());
        Progress.showProgress(true,"Fetching Chats...");
        retrieveChats();
        chatList = new ArrayList<>();
        RecyclerView recyclerView = view.findViewById(R.id.chat_first_recyclerView);
        chatDisplayAdapter = new ChatDisplayAdapter(chatList,requireContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(chatDisplayAdapter);

        chatDisplayAdapter.setOnChatClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                super.onItemClicked(position);
                Intent intent = new Intent(requireContext(), ChatActivity.class);
                intent.putExtra("username", chatList.get(position).getUser().getUsername());
                startActivity(intent);
            }
        });
        chat_with_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), ChatWithUsersActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void retrieveChats() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("user_id", SharedPrefs.getIntParams(requireContext(), ID));

        Call<ServerResponse> call = Client.getRetrofitInstance().create(ApiCalls.class).retrieveChats(map);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse = response.body();
                if (serverResponse != null) {
                    if (!serverResponse.isError()) {
                        chatList.addAll(serverResponse.getResult().getChats());
                        chatDisplayAdapter.notifyItemRangeInserted(0, chatList.size());
                        Progress.dismissProgress(progress);
                    }
                }

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }


}
