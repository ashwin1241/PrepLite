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
import com.PrepLite.activities.CompanyPreviewActivity;
import com.PrepLite.R;
import com.PrepLite.adapters.CompanyAdapter;
import com.PrepLite.models.Company;
import com.PrepLite.models.ServerResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyFragment extends Fragment {

    private ArrayList<Company> companies;
    private RecyclerView recyclerView;
    private CompanyAdapter companyAdapter;
    private ProgressDialog progress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compinsti, container, false);
        progress = Progress.getProgressDialog(requireContext());
        Progress.showProgress(true,"Fetching Companies...");
        retrieveCompanies();

        companyAdapter = new CompanyAdapter(companies,requireContext());
        recyclerView = view.findViewById(R.id.compinatichat_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(companyAdapter);

        companyAdapter.setOnCompanyClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                super.onItemClicked(position);
                Intent intent = new Intent(requireContext(), CompanyPreviewActivity.class);
                intent.putExtra("company", companies.get(position));
                startActivity(intent);
            }
        });

        return view;
    }

    private void retrieveCompanies() {
        companies = new ArrayList<>();
        Call<ServerResponse> call = Client.getRetrofitInstance().create(ApiCalls.class).retrieveCompanies();
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse = response.body();
                if (serverResponse != null) {
                    if (!serverResponse.isError()) {
                        companies.addAll(serverResponse.getResult().getCompanies());
                        companyAdapter.notifyItemRangeInserted(0, companies.size());
                        Progress.dismissProgress(progress);
                        //Build the recycler view here
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }

}
