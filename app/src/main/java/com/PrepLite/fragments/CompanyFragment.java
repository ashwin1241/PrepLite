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
import com.PrepLite.OnItemClickListener;
import com.PrepLite.activities.CompanyPreviewActivity;
import com.PrepLite.R;
import com.PrepLite.adapters.companyAdapter;
import com.PrepLite.models.Company;
import com.PrepLite.models.ServerResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyFragment extends Fragment {

    private ArrayList<Company> companies;
    private RecyclerView recyclerView;
    private companyAdapter companyAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compinstichat, container, false);
        retrieveCompanies();

        companies = new ArrayList<>();
        companyAdapter = new companyAdapter(companies,getContext());
        recyclerView = view.findViewById(R.id.compinatichat_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(companyAdapter);

        companyAdapter.setOnCompanyClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                super.onItemClicked(position);
                Intent intent = new Intent(getContext(), CompanyPreviewActivity.class);
                intent.putExtra("name", companies.get(position).getCompanyName());
                intent.putExtra("logo", companies.get(position).getCompanyLogo());
                startActivity(intent);
            }
        });

        return view;
    }

    private void retrieveCompanies() {
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
