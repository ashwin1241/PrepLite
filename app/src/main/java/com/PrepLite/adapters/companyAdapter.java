package com.PrepLite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.PrepLite.OnItemClickListener;
import com.PrepLite.R;
import com.PrepLite.models.Company;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class companyAdapter extends RecyclerView.Adapter<companyAdapter.Company_ViewHolder> {

    ArrayList<Company> companydata;
    Context context;

    private OnItemClickListener listener;

    public void setOnCompanyClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public companyAdapter(ArrayList<Company> companydata, Context activity) {

        this.companydata = companydata;
        this.context = activity;


    }

    @NonNull
    @Override
    public Company_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from((parent.getContext()));
        View view = layoutInflater.inflate(R.layout.compinsti_card, parent, false);
        Company_ViewHolder companyViewHolder = new Company_ViewHolder(view, listener);

        return companyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Company_ViewHolder holder, int position) {
        final Company companyList = companydata.get(position);
        holder.companyTitle.setText(companyList.getCompanyName());
        if (companyList.getCompanyLogo().trim().length() > 0)
            Glide.with(context).load(companyList.getCompanyLogo()).placeholder(R.drawable.ic_baseline_hourglass_top_24).into(holder.companyImage);
        else
            Glide.with(context).load(R.drawable.ic_baseline_image_not_supported_24).into(holder.companyImage);
    }

    @Override
    public int getItemCount() {

        return companydata.size();
    }

    public static class Company_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView companyImage;
        TextView companyTitle;

        OnItemClickListener listener;

        public Company_ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            this.listener = listener;

            companyImage = itemView.findViewById(R.id.companyImage);
            companyTitle = itemView.findViewById(R.id.companyTitle);

            itemView.findViewById(R.id.card_comp_insti).setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClicked(position);
                }
            }
        }
    }
}
