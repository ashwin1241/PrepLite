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
import com.PrepLite.adapters.instiAdapter;
import com.PrepLite.dataBindings.companyData;
import com.PrepLite.dataBindings.instiData;

import java.util.ArrayList;

public class InstituteFragment extends Fragment {
    private ArrayList<instiData> instidata;
    private RecyclerView recyclerView;
    private com.PrepLite.adapters.instiAdapter instiAdapter;
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
        instidata = new ArrayList<>();
        instidata.add(new instiData("CalTech",R.drawable.caltech,0));
        instidata.add(new instiData("Stanford",R.drawable.stanford,0));
        instidata.add(new instiData("IIT Delhi",R.drawable.iitd,0));
        instidata.add(new instiData("Tech Uni",R.drawable.tu,0));
        instidata.add(new instiData("BITS Pilani",R.drawable.bits,0));
        instidata.add(new instiData("Cambridge",R.drawable.cambridge,0));
        instidata.add(new instiData("Yale",R.drawable.yale,0));
        instidata.add(new instiData("Tsinghua",R.drawable.tsinghua,0));

        instiAdapter = new instiAdapter(instidata,container12.getContext());
        recyclerView = view12.findViewById(R.id.company_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(container12.getContext()));
        recyclerView.setAdapter(instiAdapter);

        instiAdapter.setOnInstiClickListener(new instiAdapter.OnInstiClickListener() {
            @Override
            public void OnItemClicked(int position) {
                Intent intent = new Intent(container12.getContext(), Company_Preview.class);
                intent.putExtra("name",instidata.get(position).getInstiName());
                startActivity(intent);
            }
        });
    }
}
