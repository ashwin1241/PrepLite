package com.PrepLite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.PrepLite.R;
import com.PrepLite.models.Comment;
import com.PrepLite.models.User;

import java.util.ArrayList;

public class commentAdapter extends RecyclerView.Adapter<commentAdapter.Comment_ViewHolder> {

    ArrayList<Comment> comments;
    Context context;

    private OnCommentClickListener cListener;

    public void setOnCommentClickListener(OnCommentClickListener listener)
    {
        this.cListener = listener;
    }

    public interface OnCommentClickListener
    {
        void OnItemLongClicked(int position);
    }

    public commentAdapter(ArrayList<Comment> comments, Context context)
    {
        this.comments = comments;
        this.context = context;
    }

    @NonNull
    @Override
    public Comment_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_layout,parent,false);
        return new Comment_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Comment_ViewHolder holder, int position) {

        Comment current_comment = comments.get(position);
        User user = current_comment.getUser();
        holder.username.setText(user.getUsername());
        holder.content.setText(current_comment.getContent());
        String timestamp = current_comment.getTimestamp();;

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(cListener!=null)
                {
                    int position = holder.getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION)
                    {
                        cListener.OnItemLongClicked(position);
                    }
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class Comment_ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView username;
        private TextView content;
        private TextView date;
        private TextView time;
        public Comment_ViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.comment_layout_username);
            content = itemView.findViewById(R.id.comment_content);
            date = itemView.findViewById(R.id.comment_date);
            time = itemView.findViewById(R.id.comment_time);

        }
    }

}
