package com.PrepLite.fragments;

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
import com.PrepLite.dataBindings.companyData;

import java.util.ArrayList;

public class CompanyFragment extends Fragment {

    private ArrayList<companyData> companydata;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        companydata = new ArrayList<>();
        companydata.add(new companyData("Microsoft",R.drawable.default_companypreview));
        companydata.add(new companyData("Amazon",R.drawable.amazon));
        companydata.add(new companyData("Oracle",R.drawable.oracle));
        companydata.add(new companyData("Code Nation",R.drawable.codenation));

        return view;
    }



}
