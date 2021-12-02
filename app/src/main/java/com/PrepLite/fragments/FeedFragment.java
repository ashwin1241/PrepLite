package com.PrepLite.fragments;

import static com.PrepLite.app.Constants.AMAZON_LOGO;
import static com.PrepLite.app.Constants.CISCO_LOGO;

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
import com.PrepLite.R;
import com.PrepLite.activities.CommentsActivity;
import com.PrepLite.adapters.postAdapter_Home;
import com.PrepLite.models.Post;
import com.PrepLite.models.ServerResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedFragment extends Fragment {

    private ArrayList<Post> postList;
    private postAdapter_Home postAdapter_home;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed,container,false);
        retrievePosts();

        postList = new ArrayList<>();
        postAdapter_home = new postAdapter_Home(postList, requireContext());
        RecyclerView recyclerView = view.findViewById(R.id.feed_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(postAdapter_home);

        postAdapter_home.setOnPostHomeClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClicked(int position, int flag) {
                super.OnItemClicked(position, flag);
                switch (flag) {
                    case 0:

                        break;
                    case 1:
                        break;
                }
            }

            @Override
            public void OnCommentClicked(int position, int flag) {
                super.OnCommentClicked(position, flag);
                switch (flag) {
                    case 0:
                        Intent intent = new Intent(requireContext(), CommentsActivity.class);
                        intent.putExtra("postId", postList.get(position).getPostId());
                        startActivity(intent);
                        break;
                    case 1:
                        break;
                }
            }
        });

        return view;
    }

    private void retrievePosts() {
        Call<ServerResponse> call = Client.getRetrofitInstance().create(ApiCalls.class).retrievePosts();
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse = response.body();
                if (serverResponse != null) {
                    if (!serverResponse.isError()) {
                        postList.addAll(serverResponse.getResult().getPosts());
                        postAdapter_home.notifyItemRangeInserted(0, postList.size());
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }

}
