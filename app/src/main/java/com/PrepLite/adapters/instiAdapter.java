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
import com.PrepLite.dataBindings.instiData;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class instiAdapter<OnInstiClickListener> extends RecyclerView.Adapter<instiAdapter.Insti_ViewHolder> {

    ArrayList<instiData> instiData;
    Context context;

    public instiAdapter(ArrayList<instiData> instiData, Context activity)   {

        this.instiData = instiData;
        this.context=activity;


    }

    @NonNull
    @Override
    public Insti_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from((parent.getContext()));
        View view = layoutInflater.inflate(R.layout.insti_card,parent,false);
        Insti_ViewHolder instiViewHolder = new Insti_ViewHolder(view);

        return instiViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Insti_ViewHolder holder, int position) {
        final instiData instiList = instiData.get(position);
        holder.instiTitle.setText(instiList.getInstiName());
        if(instiList.getInstiImage().trim().length()>0)
        Glide.with(context).load(instiList.getInstiImage().trim()).placeholder(R.drawable.ic_baseline_hourglass_top_24).into(holder.instiImage);
        else
        Glide.with(context).load(R.drawable.ic_baseline_image_not_supported_24).into(holder.instiImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, instiList.getInstiName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {

        return instiData.size();
    }

    private instiAdapter.OnInstiClickListener iListener;


    public void setOnInstiClickListener(instiAdapter.OnInstiClickListener listener) {
        this.iListener = listener;
    }

    public interface OnInstiClickListener{

        void OnItemClicked(int position);

    }

    public static class Insti_ViewHolder extends RecyclerView.ViewHolder{

        private ImageView instiImage;
        private TextView instiTitle;
        public Insti_ViewHolder(@NonNull View itemView) {
            super(itemView);
            instiImage = itemView.findViewById(R.id.instiImage);
            instiTitle = itemView.findViewById(R.id.instiTitle);
        }
    }
}
