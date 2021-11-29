package com.PrepLite.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.PrepLite.R;
import com.PrepLite.models.University;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class instiAdapter<OnInstiClickListener> extends RecyclerView.Adapter<instiAdapter.Insti_ViewHolder> {

    ArrayList<University> universities;
    Context context;

    public instiAdapter(ArrayList<University> universities, Context activity)   {
        this.universities = universities;
        this.context=activity;
    }

    @NonNull
    @Override
    public Insti_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from((parent.getContext()));
        View view = layoutInflater.inflate(R.layout.compinsti_card,parent,false);
        Insti_ViewHolder instiViewHolder = new Insti_ViewHolder(view);

        return instiViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Insti_ViewHolder holder, int position) {
        final University university = universities.get(position);
        holder.universityTitle.setText(university.getUniversityName());
        if(university.getUniversityLogo().trim().length()>0)
        Glide.with(context).load(university.getUniversityLogo()).placeholder(R.drawable.ic_baseline_hourglass_top_24).into(holder.universityImage);
        else
        {
            Glide.with(context).load(R.drawable.ic_baseline_institute_40).into(holder.universityImage);
            holder.universityImage.setBackgroundColor(Color.parseColor("#A0A0A0"));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iListener!=null)
                {
                    int position = holder.getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION)
                    {
                        iListener.OnItemClicked(position);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return universities.size();
    }

    private instiAdapter.OnInstiClickListener iListener;


    public void setOnInstiClickListener(instiAdapter.OnInstiClickListener listener) {
        this.iListener = listener;
    }

    public interface OnInstiClickListener{

        void OnItemClicked(int position);

    }

    public static class Insti_ViewHolder extends RecyclerView.ViewHolder{

        private ImageView universityImage;
        private TextView universityTitle;
        public Insti_ViewHolder(@NonNull View itemView) {
            super(itemView);
            universityImage = itemView.findViewById(R.id.companyImage);
            universityTitle = itemView.findViewById(R.id.companyTitle);
        }
    }
}
