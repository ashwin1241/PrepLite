package com.PrepLite.fragments;

import android.app.ProgressDialog;
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
import com.PrepLite.Progress;
import com.PrepLite.activities.InstitutePreviewActivity;
import com.PrepLite.R;
import com.PrepLite.adapters.InstiAdapter;
import com.PrepLite.models.ServerResponse;
import com.PrepLite.models.University;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstituteFragment extends Fragment {
    private ArrayList<University> universities;
    private RecyclerView recyclerView;
    private InstiAdapter instiAdapter;
    private ProgressDialog progress;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compinsti, container, false);
        progress = Progress.getProgressDialog(requireContext());
        Progress.showProgress(true,"Fetching Institutes...");
        retrieveUniversities();

        universities = new ArrayList<>();
        instiAdapter = new InstiAdapter(universities, requireContext());
        recyclerView = view.findViewById(R.id.compinatichat_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(instiAdapter);


        instiAdapter.setOnInstiClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                super.onItemClicked(position);
                Intent intent = new Intent(requireContext(), InstitutePreviewActivity.class);
                intent.putExtra("university", universities.get(position));
                startActivity(intent);
            }
        });

        return view;
    }

    private void retrieveUniversities() {

        Call<ServerResponse> call = Client.getRetrofitInstance().create(ApiCalls.class).retrieveUniversities();
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse = response.body();
                if (serverResponse != null) {
                    if (!serverResponse.isError()) {
                        universities.addAll(serverResponse.getResult().getUniversities());
                        instiAdapter.notifyItemRangeInserted(0, universities.size());
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
