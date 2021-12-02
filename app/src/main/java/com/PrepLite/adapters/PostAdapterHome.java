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
import com.PrepLite.models.Company;
import com.PrepLite.models.Post;
import com.PrepLite.models.University;
import com.PrepLite.models.User;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PostAdapterHome extends RecyclerView.Adapter<PostAdapterHome.Post_ViewHolder> {

    ArrayList<Post> posts;
    Context context;
    OnItemClickListener listener;

    public PostAdapterHome(ArrayList<Post> posts, Context context)
    {
        this.posts = posts;
        this.context = context;
    }

    public void setOnPostHomeClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Post_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_layout_for_home_page,parent,false);
        return new Post_ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull Post_ViewHolder holder, int position) {
        Post current_post = posts.get(position);
        User user = current_post.getUser();
        Company company = current_post.getCompany();
        University university = current_post.getUniversity();

        holder.username.setText(user.getUsername());
        holder.content.setText(current_post.getContent());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String dateString = simpleDateFormat.format(Long.parseLong(current_post.getTimestamp())*1000);
        holder.timestamp.setText(dateString);

        //holder.comp_insti.setText(current_post.());

//        if(current_post.getPostImage().trim().length()>0)
//        Glide.with(context).load(current_post.getPostImage()).placeholder(R.drawable.ic_baseline_hourglass_top_24).into(holder.compinstilogo);
//        else
//        {
//            if(!current_post.getCompany().getCompanyName().isEmpty())
//                Glide.with(context).load(R.drawable.ic_baseline_work_24).into(holder.compinstilogo);
//            else if(!current_post.getUniversity().getUniversityName().isEmpty())
//                Glide.with(context).load(R.drawable.ic_baseline_institute_40).into(holder.compinstilogo);
//            else
//                holder.compinstilogo.setBackgroundColor(Color.parseColor("#A0A0A0"));
//        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class Post_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
    {
        private TextView username;
        private TextView comp_insti;
        private TextView content;
        private TextView timestamp;
        private TextView post_comments;
        private ShapeableImageView compinstilogo;

        OnItemClickListener postListener;

        public Post_ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            this.postListener = listener;

            username = itemView.findViewById(R.id.post_layout_username);
            comp_insti = itemView.findViewById(R.id.post_layout_comp_insti);
            content = itemView.findViewById(R.id.post_content);
            timestamp = itemView.findViewById(R.id.post_timestamp);
            post_comments = itemView.findViewById(R.id.post_comments);
            compinstilogo = itemView.findViewById(R.id.feed_post_compinsti_pic);

            itemView.findViewById(R.id.card_home).setOnClickListener(this);
            itemView.findViewById(R.id.card_home).setOnLongClickListener(this);
            post_comments.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (postListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    int id = view.getId();
                    if (id == R.id.card_home) {
                        postListener.OnItemClicked(position, 0);
                    } else if (id == R.id.post_comments) {
                        postListener.OnCommentClicked(position, 0);
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
                    if (id == R.id.card_home) {
                        postListener.OnItemLongClicked(position);
                    }
                }
            }
            return true;
        }
    }

}
