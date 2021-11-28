package com.PrepLite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.PrepLite.R;
import com.PrepLite.dataBindings.companyData;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class companyAdapter extends RecyclerView.Adapter<companyAdapter.Company_ViewHolder> {

    ArrayList<companyData> companydata;
    Context context;

    private OnCompanyClickListener cListener;

    public void setOnCompanyClickListener(OnCompanyClickListener listener)
    {
        this.cListener = listener;
    }

    public interface OnCompanyClickListener
    {
        void OnItemClicked(int position);
    }

    public companyAdapter(ArrayList<companyData> companydata, Context activity)   {

        this.companydata = companydata;
        this.context=activity;


    }

    @NonNull
    @Override
    public Company_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from((parent.getContext()));
        View view = layoutInflater.inflate(R.layout.compinsti_card,parent,false);
        Company_ViewHolder companyViewHolder = new Company_ViewHolder(view);

        return companyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Company_ViewHolder holder, int position) {
        final companyData companyList = companydata.get(position);
        holder.companyTitle.setText(companyList.getCompanyName());
        if(companyList.getCompanyImage().trim().length()>0)
        Glide.with(context).load(companyList.getCompanyImage()).placeholder(R.drawable.ic_baseline_hourglass_top_24).into(holder.companyImage);
        else
        Glide.with(context).load(R.drawable.ic_baseline_image_not_supported_24).into(holder.companyImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cListener!=null)
                {
                    int position = holder.getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION)
                    {
                        cListener.OnItemClicked(position);
                    }
                }
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
