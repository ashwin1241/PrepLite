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

import com.PrepLite.ApiCalls;
import com.PrepLite.Client;
import com.PrepLite.OnItemClickListener;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compinstichat, container, false);
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
                intent.putExtra("name", companies.get(position).getCompanyName());
                intent.putExtra("logo", companies.get(position).getCompanyLogo());
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
