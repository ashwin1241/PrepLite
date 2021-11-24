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
import com.PrepLite.fragments.CompanyFragment;

public class companyAdapter extends RecyclerView.Adapter<companyAdapter.ViewHolder> {

    companyData[] companydata;
    Context context;

    public companyAdapter(companyData[] companydata, Context activity)   {

        this.companydata = companydata;
        this.context=activity;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from((parent.getContext()));
        View view = layoutInflater.inflate(R.layout.company_card,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final companyData companyList = companydata[position];
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

        return companydata.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView companyImage;
        TextView companyTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            companyImage = itemView.findViewById(R.id.companyImage);
            companyTitle = itemView.findViewById(R.id.companyTitle);
        }
    }
}
