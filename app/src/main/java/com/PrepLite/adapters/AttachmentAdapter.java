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
import com.PrepLite.models.Attachment;

import java.util.ArrayList;

public class AttachmentAdapter extends RecyclerView.Adapter<AttachmentAdapter.attachmentViewHolder>{

    private ArrayList<Attachment> attachmentList;
    private Context context;

    private OnItemClickListener listener;

    public void setOnAttachmentClickListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }

    public AttachmentAdapter(ArrayList<Attachment> attachmentList, Context context) {
        this.attachmentList = attachmentList;
        this.context = context;
    }

    @NonNull
    @Override
    public attachmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attachment_card,parent,false);
        return new attachmentViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull attachmentViewHolder holder, int position) {
        Attachment currentAttachment = attachmentList.get(position);
        holder.attachmentTitle.setText(currentAttachment.getTitle());
        if(currentAttachment.isDownloadStat())
        {
            holder.downloadButton.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
        }
        else
        {
            holder.downloadButton.setImageResource(R.drawable.ic_baseline_arrow_circle_down_24);
        }

    }

    @Override
    public int getItemCount() {
        return attachmentList.size();
    }

    public static class attachmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        private TextView attachmentTitle;
        private ImageView downloadButton;

        OnItemClickListener listener;

        public attachmentViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            this.listener = listener;

            attachmentTitle = itemView.findViewById(R.id.attachment_title);
            downloadButton = itemView.findViewById(R.id.attachment_download);
            downloadButton.setOnClickListener(this);

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
