package com.PrepLite.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.PrepLite.OnItemClickListener;
import com.PrepLite.R;
import com.PrepLite.models.Chat;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class ChatDisplayAdapter extends RecyclerView.Adapter<ChatDisplayAdapter.chatViewHolder> {

    private ArrayList<Chat> chats;
    private Context context;

    private OnItemClickListener listener;

    public void setOnChatClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ChatDisplayAdapter(ArrayList<Chat> chats, Context context) {
        this.chats = chats;
        this.context = context;
    }

    @NonNull
    @Override
    public chatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_user_layout, parent, false);
        return new chatViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull chatViewHolder holder, int position) {
        Chat current_chat = chats.get(position);
        holder.username.setText(current_chat.getUser().getUsername());
        holder.chat_preview.setText(current_chat.getChatFirstMessage());
        holder.timestamp.setText(current_chat.getTimestamp());
        if (current_chat.getUser().getProfileImage().trim().length() > 0)
            Glide.with(context).load(current_chat.getUser().getProfileImage()).placeholder(R.drawable.ic_baseline_hourglass_top_24).into(holder.profile_image);
        else {
            Glide.with(context).load(R.drawable.ic_baseline_person_24).into(holder.profile_image);
            holder.profile_image.setBackgroundColor(Color.parseColor("#A0A0A0"));
        }

    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public static class chatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ShapeableImageView profile_image;
        private TextView username;
        private TextView chat_preview;
        private TextView timestamp;
        OnItemClickListener listener;

        public chatViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            this.listener = listener;

            profile_image = itemView.findViewById(R.id.chat_profile_image);
            username = itemView.findViewById(R.id.chat_person_username);
            chat_preview = itemView.findViewById(R.id.chat_text_preview);
            timestamp = itemView.findViewById(R.id.chat_last_timestamp);

            itemView.findViewById(R.id.card_chat_user).setOnClickListener(this);

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
