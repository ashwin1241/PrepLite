package com.PrepLite.adapters;

import static com.PrepLite.prefs.SharedPrefsConstants.NAME;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.PrepLite.OnItemClickListener;
import com.PrepLite.R;
import com.PrepLite.models.Comment;
import com.PrepLite.models.User;
import com.PrepLite.prefs.SharedPrefs;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.Comment_ViewHolder> {

    ArrayList<Comment> comments;
    Context context;

    private OnItemClickListener listener;

    public void setOnCommentClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public CommentAdapter(ArrayList<Comment> comments, Context context) {
        this.comments = comments;
        this.context = context;
    }

    @NonNull
    @Override
    public Comment_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_layout, parent, false);
        return new Comment_ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull Comment_ViewHolder holder, int position) {

        Comment current_comment = comments.get(position);
        User user = current_comment.getUser();
        holder.username.setText(SharedPrefs.getStringParams(context, NAME, ""));
        holder.content.setText(current_comment.getContent());
        String timestamp = current_comment.getTimestamp();
        holder.timestamp.setText(timestamp);

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class Comment_ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        private TextView username;
        private TextView content;
        private TextView timestamp;
        OnItemClickListener listener;

        public Comment_ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            this.listener = listener;

            username = itemView.findViewById(R.id.comment_layout_username);
            content = itemView.findViewById(R.id.comment_content);
            timestamp = itemView.findViewById(R.id.comment_timestamp);

            itemView.findViewById(R.id.card_comment).setOnLongClickListener(this);

        }

        @Override
        public boolean onLongClick(View view) {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.OnItemLongClicked(position);
                }
            }
            return true;
        }
    }

}
