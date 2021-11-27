package com.PrepLite.models;

import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("post_id")
    public int postId;

    @SerializedName("user_id")
    public int userId;

    @SerializedName("company_id")
    public int company_id;

    @SerializedName("timestamp")
    public String timestamp;

    @SerializedName("upvotes")
    public int upvotes;

    @SerializedName("downvotes")
    public int downvotes;

    @SerializedName("content")
    public String content;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
