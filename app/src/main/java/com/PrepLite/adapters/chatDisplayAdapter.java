package com.PrepLite.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.PrepLite.R;
import com.PrepLite.dataBindings.chatDisplayData;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class chatDisplayAdapter extends RecyclerView.Adapter<chatDisplayAdapter.chatViewHolder>{

    private ArrayList<chatDisplayData> chatList;
    private Context context;

    private OnChatClickListener cListener;

    public void setOnChatClickListener(OnChatClickListener listener)
    {
        this.cListener = listener;
    }

    public interface OnChatClickListener
    {
        void OnItemClicked(int position);
    }

    public chatDisplayAdapter(ArrayList<chatDisplayData> chatList, Context context)
    {
        this.chatList = chatList;
        this.context = context;
    }

    @NonNull
    @Override
    public chatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_user_layout,parent,false);
        return new chatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull chatViewHolder holder, int position) {
        chatDisplayData current_chat = chatList.get(position);
        holder.username.setText(current_chat.getUsername());
        holder.chat_preview.setText(current_chat.getChat_first_message());
        holder.timestamp.setText(current_chat.getTimestamp());
        if(current_chat.getProfile_image_url().trim().length()>0)
        Glide.with(context).load(current_chat.getProfile_image_url()).placeholder(R.drawable.ic_baseline_hourglass_top_24).into(holder.profile_image);
        else
        {
            Glide.with(context).load(R.drawable.ic_baseline_person_24).into(holder.profile_image);
            holder.profile_image.setBackgroundColor(Color.parseColor("#A0A0A0"));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        return chatList.size();
    }

    public static class chatViewHolder extends RecyclerView.ViewHolder
    {
        private ShapeableImageView profile_image;
        private TextView username;
        private TextView chat_preview;
        private TextView timestamp;

        public chatViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_image = itemView.findViewById(R.id.chat_profile_image);
            username = itemView.findViewById(R.id.chat_person_username);
            chat_preview = itemView.findViewById(R.id.chat_text_preview);
            timestamp = itemView.findViewById(R.id.chat_last_timestamp);
        }
    }

}
