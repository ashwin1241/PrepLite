package com.PrepLite.fragments;

import static com.PrepLite.app.Constants.AMAZON_LOGO;
import static com.PrepLite.app.Constants.CISCO_LOGO;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.PrepLite.R;
import com.PrepLite.adapters.postAdapter_Home;
import com.PrepLite.dataBindings.postData;

import java.util.ArrayList;

public class FeedFragment extends Fragment {

    private ArrayList<postData> postList;
    private RecyclerView recyclerView;
    private postAdapter_Home postAdapter_home;
    private ViewGroup container12;
    private View view12;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed,container,false);
        container12=container;
        view12=view;
        buildRecyclerView();
        return view;
    }

    private void buildRecyclerView() {
        postList = new ArrayList<>();
        postList.add(new postData("Ashwin","Amazon","24-11-2021","13:02","Hello 1",AMAZON_LOGO));
        postList.add(new postData("Aagam","Cisco","25-11-2021","13:02","Hello 2",CISCO_LOGO));
        postList.add(new postData("Harsh","GE","26-11-2021","13:02","Hello 3",""));
        postAdapter_home = new postAdapter_Home(postList, container12.getContext());
        recyclerView = view12.findViewById(R.id.feed_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(container12.getContext()));
        recyclerView.setAdapter(postAdapter_home);

    }

}
