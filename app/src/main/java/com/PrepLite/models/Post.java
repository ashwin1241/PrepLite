package com.PrepLite.models;

import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("post_id")
    public int postId;

    @SerializedName("user")
    public User user;

    @SerializedName("company")
    public Company company;

    @SerializedName("university")
    public University university;

    @SerializedName("timestamp")
    public String timestamp;

    @SerializedName("upvotes")
    public int upvotes;

    @SerializedName("downvotes")
    public int downvotes;

    @SerializedName("content")
    public String content;

    @SerializedName("postImage")
    public String postImage;

    public Post(int postId, User user, Company company, University university, String timestamp, int upvotes, int downvotes, String content, String postImage) {
        this.postId = postId;
        this.user = user;
        this.company = company;
        this.university = university;
        this.timestamp = timestamp;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.content = content;
        this.postImage = postImage;
    }

    public Post(User user, University university, Company company, String timestamp, String content, String postImage) {
        this.user = user;
        this.company = company;
        this.university = university;
        this.timestamp = timestamp;
        this.content = content;
        this.postImage = postImage;
    }


    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
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

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }
}
