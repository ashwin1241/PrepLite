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

import com.PrepLite.OnItemClickListener;
import com.PrepLite.R;
import com.PrepLite.models.Attachment;

import java.util.ArrayList;

public class AddPostAttachmentAdapter extends RecyclerView.Adapter<AddPostAttachmentAdapter.AddPostAttachmentViewHolder>{

    private ArrayList<Attachment> attachmentList;
    private Context context;

    private OnItemClickListener listener;

    public void setOnItemClickListenerAttachment(OnItemClickListener listener)
    {
        this.listener = listener;
    }

    public AddPostAttachmentAdapter(ArrayList<Attachment> attachmentList, Context context) {
        this.attachmentList = attachmentList;
        this.context = context;
    }

    @NonNull
    @Override
    public AddPostAttachmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attachment_card,parent,false);
        return new AddPostAttachmentViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AddPostAttachmentViewHolder holder, int position) {
        Attachment currentAttachment = attachmentList.get(position);
        holder.cancelImage.setImageResource(R.drawable.ic_baseline_close_24);
        holder.title.setText(currentAttachment.getTitle());
        holder.title.setTextColor(Color.parseColor("#0000FF"));
    }

    @Override
    public int getItemCount() {
        return attachmentList.size();
    }

    public static class AddPostAttachmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private ImageView cancelImage;
        private TextView title;

        OnItemClickListener attlistener;
        public AddPostAttachmentViewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            this.attlistener = listener;
            title = itemView.findViewById(R.id.attachment_title);
            cancelImage = itemView.findViewById(R.id.attachment_download);
            cancelImage.setOnClickListener(this);
            title.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (attlistener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    int id = view.getId();
                    if (id == R.id.attachment_download) {
                        attlistener.OnItemClicked(position,0);
                    }
                    else if(id == R.id.attachment_title)
                    {
                        attlistener.OnItemClicked(position,1);
                    }
                }
            }
        }
    }


}
