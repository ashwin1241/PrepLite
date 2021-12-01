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
import com.PrepLite.models.University;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class instiAdapter extends RecyclerView.Adapter<instiAdapter.Insti_ViewHolder> {

    ArrayList<University> universities;
    Context context;

    public instiAdapter(ArrayList<University> universities, Context activity) {
        this.universities = universities;
        this.context = activity;
    }

    @NonNull
    @Override
    public Insti_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from((parent.getContext()));
        View view = layoutInflater.inflate(R.layout.compinsti_card, parent, false);
        Insti_ViewHolder instiViewHolder = new Insti_ViewHolder(view, listener);

        return instiViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Insti_ViewHolder holder, int position) {
        final University university = universities.get(position);
        holder.universityTitle.setText(university.getUniversityName());
        if (university.getUniversityLogo().trim().length() > 0)
            Glide.with(context).load(university.getUniversityName()).placeholder(R.drawable.ic_baseline_hourglass_top_24).into(holder.universityImage);
        else
            Glide.with(context).load(R.drawable.ic_baseline_image_not_supported_24).into(holder.universityImage);
    }

    @Override
    public int getItemCount() {

        return universities.size();
    }

    private OnItemClickListener listener;


    public void setOnInstiClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class Insti_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView universityImage;
        private TextView universityTitle;

        OnItemClickListener listener;

        public Insti_ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            this.listener = listener;

            universityImage = itemView.findViewById(R.id.companyImage);
            universityTitle = itemView.findViewById(R.id.companyTitle);

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
