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

import com.PrepLite.Company_Preview;
import com.PrepLite.R;
import com.PrepLite.adapters.companyAdapter;
import com.PrepLite.dataBindings.companyData;

import java.util.ArrayList;

public class CompanyFragment extends Fragment {

    private ArrayList<companyData> companydata;
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

    private void buildRecyclerView()
    {
        companydata = new ArrayList<>();
        companydata.add(new companyData("Microsoft",R.drawable.default_companypreview));
        companydata.add(new companyData("Amazon",R.drawable.amazon));
        companydata.add(new companyData("Oracle",R.drawable.oracle));
        companydata.add(new companyData("Code Nation",R.drawable.codenation));
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

}
