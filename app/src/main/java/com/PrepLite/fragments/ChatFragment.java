package com.PrepLite.fragments;

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

import com.PrepLite.ChatActivity;
import com.PrepLite.R;
import com.PrepLite.adapters.chatDisplayAdapter;
import com.PrepLite.models.Chat;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    private View view12;
    private ViewGroup container12;
    private RecyclerView recyclerView;
    private ArrayList<Chat> chatList;
    private chatDisplayAdapter chatDisplayAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compinstichat,container,false);
        this.view12 = view;
        this.container12 = container;
        buildRecyclerView();
        return view;
    }

    private void buildRecyclerView()
    {
        chatList = new ArrayList<>();
//        chatList.add(new Chat("Aagam","","","Yesterday"));
//        chatList.add(new Chat("Harsh","","","26/11/21"));
//        chatList.add(new Chat("Prathamesh","","","14/11/21"));
        recyclerView = view12.findViewById(R.id.compinatichat_recyclerView);
        chatDisplayAdapter = new chatDisplayAdapter(chatList,container12.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(container12.getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(chatDisplayAdapter);

        chatDisplayAdapter.setOnChatClickListener(new chatDisplayAdapter.OnChatClickListener() {
            @Override
            public void OnItemClicked(int position) {
                Intent intent = new Intent(container12.getContext(), ChatActivity.class);
                intent.putExtra("username", chatList.get(position).getUser().getUsername());
                startActivity(intent);
            }
        });
    }

}
