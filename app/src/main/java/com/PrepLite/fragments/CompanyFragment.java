package com.PrepLite.fragments;

import static com.PrepLite.app.Constants.AMAZON_LOGO;
import static com.PrepLite.app.Constants.CISCO_LOGO;
import static com.PrepLite.app.Constants.CODE_NATION_LOGO;
import static com.PrepLite.app.Constants.GENERAL_MOTORS_LOGO;
import static com.PrepLite.app.Constants.MICROSOFT_LOGO;
import static com.PrepLite.app.Constants.ORACLE_LOGO;
import static com.PrepLite.app.Constants.SERVICE_NOW_LOGO;
import static com.PrepLite.app.Constants.SPOTIFY_LOGO;

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
import com.PrepLite.Company_Preview;
import com.PrepLite.R;
import com.PrepLite.adapters.companyAdapter;
import com.PrepLite.models.Company;
import com.PrepLite.models.ServerResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyFragment extends Fragment {

    private ArrayList<Company> companydata;
    private RecyclerView recyclerView;
    private companyAdapter companyAdapter;
    private ViewGroup container12;
    private View view12;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company,container,false);
        container12=container;
        view12=view;
        buildRecyclerView();
        return view;
    }

    private void buildRecyclerView() {

        retrieveCompanies();

        companydata = new ArrayList<>();
        companydata.add(new Company("Microsoft",MICROSOFT_LOGO));
        companydata.add(new Company("Amazon",AMAZON_LOGO));
        companydata.add(new Company("Oracle",ORACLE_LOGO));
        companydata.add(new Company("Code Nation",CODE_NATION_LOGO));
        companydata.add(new Company("Service Now",SERVICE_NOW_LOGO));
        companydata.add(new Company("Cisco",CISCO_LOGO));
        companydata.add(new Company("Spotify",SPOTIFY_LOGO));
        companydata.add(new Company("General Motors",GENERAL_MOTORS_LOGO));
        companyAdapter = new companyAdapter(companydata,container12.getContext());
        recyclerView = view12.findViewById(R.id.company_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(container12.getContext()));
        recyclerView.setAdapter(companyAdapter);

        companyAdapter.setOnCompanyClickListener(new companyAdapter.OnCompanyClickListener() {
            @Override
            public void OnItemClicked(int position) {
                Intent intent = new Intent(container12.getContext(), Company_Preview.class);
                intent.putExtra("name",companydata.get(position).getCompanyName());
                startActivity(intent);
            }
        });
    }

    private void retrieveCompanies() {
        Call<ServerResponse> call = Client.getRetrofitInstance().create(ApiCalls.class).retrieveCompanies();
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse = response.body();
                if (serverResponse != null) {
                    if(!serverResponse.isError()) {
                        ArrayList<Company> companies = serverResponse.getResult().getCompanies();
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
