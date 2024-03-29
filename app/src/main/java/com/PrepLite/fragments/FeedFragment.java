package com.PrepLite.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.PrepLite.ApiCalls;
import com.PrepLite.Client;
import com.PrepLite.OnItemClickListener;
import com.PrepLite.Progress;
import com.PrepLite.R;
import com.PrepLite.activities.CommentsActivity;
import com.PrepLite.activities.CompanyPreviewActivity;
import com.PrepLite.activities.InstitutePreviewActivity;
import com.PrepLite.activities.ViewProfileActivity;
import com.PrepLite.adapters.PostAdapterHome;
import com.PrepLite.models.Company;
import com.PrepLite.models.Post;
import com.PrepLite.models.ServerResponse;
import com.PrepLite.models.University;
import com.PrepLite.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedFragment extends Fragment {

    private ArrayList<Post> postList;
    private PostAdapterHome postAdapter_home;
    private ProgressDialog progress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed,container,false);
        progress = Progress.getProgressDialog(requireContext());
        Progress.showProgress(true,"Fetching feed...");
        retrievePosts();

        postList = new ArrayList<>();
        postAdapter_home = new PostAdapterHome(postList, requireContext());
        RecyclerView recyclerView = view.findViewById(R.id.feed_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(postAdapter_home);

        postAdapter_home.setOnPostHomeClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClicked(int position, int flag) {
                super.OnItemClicked(position, flag);
                switch (flag) {
                    case 1:
                        Intent intent = new Intent(requireContext(), ViewProfileActivity.class);
                        intent.putExtra("user",postList.get(position).getUser());
                        startActivity(intent);
                        break;
                    case 2:
                        if(postList.get(position).getCompany().getCompanyName()==null)
                        {
                            Intent intent1 = new Intent(requireContext(), InstitutePreviewActivity.class);
                            intent1.putExtra("university",postList.get(position).getUniversity());
                            startActivity(intent1);
                        }
                        else if(postList.get(position).getUniversity().getUniversityName()==null)
                        {
                            Intent intent1 = new Intent(requireContext(), CompanyPreviewActivity.class);
                            intent1.putExtra("company",postList.get(position).getCompany());
                            startActivity(intent1);
                        }
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

            @Override
            public void OnItemLongClicked(int position) {
                //need to add backend code here to check if the user is the one who posted this post
                deletePost(position);
            }
        });

        return view;
    }

    private void retrievePosts() {
        postList = new ArrayList<>();
        Call<ServerResponse> call = Client.getRetrofitInstance().create(ApiCalls.class).retrievePosts();
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse = response.body();
                if (serverResponse != null) {
                    if (!serverResponse.isError()) {
                        postList.addAll(serverResponse.getResult().getPosts());
                        postAdapter_home.notifyItemRangeInserted(0, postList.size());
                        Progress.dismissProgress(progress);
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }

    private void deletePost(int position)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Delete Post")
                .setMessage("Are you sure you want to delete this post?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //backend code to actually delete this post
                        postList.remove(position);
                        postAdapter_home.notifyItemRemoved(position);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.create().show();
    }

}
