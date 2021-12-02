package com.PrepLite.fragments;

import static com.PrepLite.prefs.SharedPrefsConstants.ID;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.PrepLite.ApiCalls;
import com.PrepLite.Client;
import com.PrepLite.OnItemClickListener;
import com.PrepLite.activities.ChatActivity;
import com.PrepLite.R;
import com.PrepLite.adapters.chatDisplayAdapter;
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
    private chatDisplayAdapter chatDisplayAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compinstichat,container,false);
        retrieveChats();

        chatList = new ArrayList<>();
        RecyclerView recyclerView = view.findViewById(R.id.compinatichat_recyclerView);
        chatDisplayAdapter = new chatDisplayAdapter(chatList, requireContext());
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
                    }
                }

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }


}
