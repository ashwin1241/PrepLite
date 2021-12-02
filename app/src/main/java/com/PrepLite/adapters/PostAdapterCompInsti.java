package com.PrepLite.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.PrepLite.OnItemClickListener;
import com.PrepLite.activities.CommentsActivity;
import com.PrepLite.R;
import com.PrepLite.models.Post;
import com.PrepLite.models.User;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class PostAdapterCompInsti extends RecyclerView.Adapter<PostAdapterCompInsti.Post_ViewHolder12> {

    ArrayList<Post> posts;
    Context context;

    private OnItemClickListener listener;

    public void setOnPostCompInstiClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public PostAdapterCompInsti(ArrayList<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public Post_ViewHolder12 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_layout_for_individual_pages, parent, false);
        return new Post_ViewHolder12(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull Post_ViewHolder12 holder, int position) {

        Post current_post = posts.get(position);
        User user = current_post.getUser();
        holder.username.setText(user.getUsername());
        holder.content.setText(current_post.getContent());
        String timestamp = current_post.getTimestamp();
        holder.timestamp.setText(current_post.getTimestamp());

        if (current_post.getUser().getProfileImage().trim().length() > 0)
            Glide.with(context).load(current_post.getUser().getProfileImage()).placeholder(R.drawable.ic_baseline_hourglass_top_24).into(holder.profile_pic);
        else {
            Glide.with(context).load(R.drawable.ic_baseline_person_24).into(holder.profile_pic);
            holder.profile_pic.setBackgroundColor(Color.parseColor("#A0A0A0"));
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class Post_ViewHolder12 extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView username;
        private TextView content;
        private TextView timestamp;
        private TextView post_comments;
        private ShapeableImageView profile_pic;

        OnItemClickListener postListener;

        public Post_ViewHolder12(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            this.postListener = listener;

            username = itemView.findViewById(R.id.post_layout_username);
            content = itemView.findViewById(R.id.post_content);
            timestamp = itemView.findViewById(R.id.post_timestamp);
            post_comments = itemView.findViewById(R.id.post_comments);
            profile_pic = itemView.findViewById(R.id.ind_post_profile_pic);

            itemView.findViewById(R.id.card_individual_page).setOnClickListener(this);
            itemView.findViewById(R.id.card_individual_page).setOnLongClickListener(this);

            post_comments.setOnClickListener(this);
            username.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (postListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    int id = view.getId();
                    if (id == R.id.card_individual_page) {
                        postListener.OnItemClicked(position, 0);
                    } else if (id == R.id.post_comments) {
                        postListener.OnCommentClicked(position, 0);
                    }
                    else if(id == R.id.post_layout_username)
                    {
                        postListener.OnItemClicked(position,1);
                    }
                }
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (postListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    int id = view.getId();
                    if (id == R.id.card_individual_page) {
                        postListener.OnItemLongClicked(position);
                    }
                }
            }
            return true;
        }
    }


}
