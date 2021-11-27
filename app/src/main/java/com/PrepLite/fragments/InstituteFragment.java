package com.PrepLite.fragments;

import static com.PrepLite.app.Constants.BITS_PILANI_LOGO;
import static com.PrepLite.app.Constants.CAL_TECH_LOGO;
import static com.PrepLite.app.Constants.CAMBRIDGE_LOGO;
import static com.PrepLite.app.Constants.IIT_DELHI_LOGO;
import static com.PrepLite.app.Constants.STANFORD_LOGO;
import static com.PrepLite.app.Constants.TECH_UNI_LOGO;
import static com.PrepLite.app.Constants.TSINGHUA_LOGO;
import static com.PrepLite.app.Constants.YALE_LOGO;

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
import com.PrepLite.InstitutePreviewActivity;
import com.PrepLite.R;
import com.PrepLite.adapters.instiAdapter;
import com.PrepLite.dataBindings.instiData;
import com.PrepLite.models.ServerResponse;
import com.PrepLite.models.University;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        retrieveUniversities();
        instidata = new ArrayList<>();
        instidata.add(new instiData("CalTech",CAL_TECH_LOGO,0));
        instidata.add(new instiData("Stanford",STANFORD_LOGO,0));
        instidata.add(new instiData("IIT Delhi",IIT_DELHI_LOGO,0));
        instidata.add(new instiData("Tech Uni",TECH_UNI_LOGO,0));
        instidata.add(new instiData("BITS Pilani",BITS_PILANI_LOGO,0));
        instidata.add(new instiData("Cambridge",CAMBRIDGE_LOGO,0));
        instidata.add(new instiData("Yale",YALE_LOGO,0));
        instidata.add(new instiData("Tsinghua",TSINGHUA_LOGO,0));

        instiAdapter = new instiAdapter(instidata,container12.getContext());
        recyclerView = view12.findViewById(R.id.company_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(container12.getContext()));
        recyclerView.setAdapter(instiAdapter);

        instiAdapter.setOnInstiClickListener(new instiAdapter.OnInstiClickListener() {
            @Override
            public void OnItemClicked(int position) {
                Intent intent = new Intent(container12.getContext(), InstitutePreviewActivity.class);
                intent.putExtra("name",instidata.get(position).getInstiName());
                intent.putExtra("logo",instidata.get(position).getInstiImage());
                startActivity(intent);
            }
        });
    }

    private void retrieveUniversities() {
        Call<ServerResponse> call = Client.getRetrofitInstance().create(ApiCalls.class).retrieveUniversities();
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse = response.body();
                if (serverResponse != null) {
                    ArrayList<University> universities = serverResponse.getResult().getUniversities();
                    //build the recycler view here
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }

}
