package com.PrepLite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.PrepLite.R;
import com.PrepLite.dataBindings.chatMainData;

import java.util.ArrayList;

public class chatMainAdapter extends RecyclerView.Adapter{

    private ArrayList<chatMainData> messageList;
    private Context context;

    public chatMainAdapter(ArrayList<chatMainData> messageList, Context context) {
        this.messageList = messageList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if(messageList.get(position).isSender())
        {
            if(messageList.get(position).isFirstOfSameType())
                return 2;
            else
                return 0;
        }
        else
        {
            if(messageList.get(position).isFirstOfSameType())
                return 3;
            else
                return 1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch(viewType)
        {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sender_chat_message,parent,false);
                return new senderChatViewHolder(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receiver_chat_message,parent,false);
                return new receiverChatViewHolder(view);
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sender_first_chat_message,parent,false);
                return new senderChatViewHolder(view);
            case 3:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receiver_first_chat_message,parent,false);
                return new receiverChatViewHolder(view);
            default: return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        chatMainData current_item = messageList.get(position);
        if(current_item.isSender())
        {
            ((senderChatViewHolder)holder).senderDate.setText(current_item.getDate());
            ((senderChatViewHolder)holder).senderTime.setText(current_item.getTime());
            ((senderChatViewHolder)holder).senderMessage.setText(current_item.getMessage());
        }
        else
        {
            ((receiverChatViewHolder)holder).receiverDate.setText(current_item.getDate());
            ((receiverChatViewHolder)holder).receiverTime.setText(current_item.getTime());
            ((receiverChatViewHolder)holder).receiverMessage.setText(current_item.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class senderChatViewHolder extends RecyclerView.ViewHolder
    {
        private TextView senderDate;
        private TextView senderTime;
        private TextView senderMessage;

        public senderChatViewHolder(@NonNull View itemView) {
            super(itemView);

            senderDate = itemView.findViewById(R.id.sender_chat_message_date);
            senderTime = itemView.findViewById(R.id.sender_chat_message_time);
            senderMessage = itemView.findViewById(R.id.sender_chat_text);

        }
    }

    public static class receiverChatViewHolder extends RecyclerView.ViewHolder
    {
        private TextView receiverDate;
        private TextView receiverTime;
        private TextView receiverMessage;

        public receiverChatViewHolder(@NonNull View itemView) {
            super(itemView);

             receiverDate = itemView.findViewById(R.id.receiver_chat_message_date);
             receiverTime = itemView.findViewById(R.id.receiver_chat_message_time);
             receiverMessage = itemView.findViewById(R.id.receiver_chat_text);

        }
    }
}
