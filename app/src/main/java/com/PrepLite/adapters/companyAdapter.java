package com.PrepLite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.PrepLite.R;
import com.PrepLite.dataBindings.companyData;

import java.util.ArrayList;

public class companyAdapter extends RecyclerView.Adapter<companyAdapter.Company_ViewHolder> {

    ArrayList<companyData> companydata;
    Context context;

    public companyAdapter(ArrayList<companyData> companydata, Context activity)   {

        this.companydata = companydata;
        this.context=activity;


    }

    @NonNull
    @Override
    public Company_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from((parent.getContext()));
        View view = layoutInflater.inflate(R.layout.company_card,parent,false);
        Company_ViewHolder companyViewHolder = new Company_ViewHolder(view);

        return companyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Company_ViewHolder holder, int position) {
        final companyData companyList = companydata.get(position);
        holder.companyTitle.setText(companyList.getCompanyName());
        holder.companyImage.setImageResource(companyList.getCompanyImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, companyList.getCompanyName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {

        return companydata.size();
    }

    public static class Company_ViewHolder extends RecyclerView.ViewHolder{

        ImageView companyImage;
        TextView companyTitle;
        public Company_ViewHolder(@NonNull View itemView) {
            super(itemView);
            companyImage = itemView.findViewById(R.id.companyImage);
            companyTitle = itemView.findViewById(R.id.companyTitle);
        }
    }
}