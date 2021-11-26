package com.PrepLite.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.PrepLite.Comments;
import com.PrepLite.R;
import com.PrepLite.dataBindings.postData;

import java.util.ArrayList;

public class postAdapter_CompInsti extends RecyclerView.Adapter<postAdapter_CompInsti.Post_ViewHolder12> {

    ArrayList<postData> posts;
    Context context;

    public postAdapter_CompInsti(ArrayList<postData> posts, Context context)
    {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public Post_ViewHolder12 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_layout_for_individual_pages,parent,false);
        return new Post_ViewHolder12(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Post_ViewHolder12 holder, int position) {
        postData current_post = posts.get(position);
        holder.username.setText(current_post.getUsername());
        holder.content.setText(current_post.getContent());
        holder.date.setText(current_post.getDate());
        holder.time.setText(current_post.getTime());
        holder.post_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Comments.class);
                //backend code to add data to intent as to which post comments we are viewing
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class Post_ViewHolder12 extends RecyclerView.ViewHolder
    {
        private TextView username;
        private TextView content;
        private TextView date;
        private TextView time;
        private TextView post_comments;
        public Post_ViewHolder12(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.post_layout_username);
            content = itemView.findViewById(R.id.post_content);
            date = itemView.findViewById(R.id.post_date);
            time = itemView.findViewById(R.id.post_time);
            post_comments = itemView.findViewById(R.id.post_comments);

        }
    }

}
