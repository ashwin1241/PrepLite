package com.PrepLite;

import static com.PrepLite.app.Constants.ADD_COMMENT;
import static com.PrepLite.app.Constants.CREATE_POST;
import static com.PrepLite.app.Constants.LOGIN_USER;
import static com.PrepLite.app.Constants.PROFILE_USER;
import static com.PrepLite.app.Constants.REGISTER_USER;
import static com.PrepLite.app.Constants.VIEW_CHATS;
import static com.PrepLite.app.Constants.VIEW_COMMENTS;
import static com.PrepLite.app.Constants.VIEW_COMPANIES;
import static com.PrepLite.app.Constants.VIEW_POSTS;
import static com.PrepLite.app.Constants.VIEW_UNIVERSITIES;

import com.PrepLite.models.ServerResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface ApiCalls {

    @POST(REGISTER_USER)
    Call<ServerResponse> register(@Body HashMap<String, String> registerCredentials);

    @POST(LOGIN_USER)
    Call<ServerResponse> login(@Body HashMap<String, String> loginCredentials);

    @POST(PROFILE_USER)
    Call<ServerResponse> profile(@Body HashMap<String, Integer> userid);

    @POST(VIEW_UNIVERSITIES)
    Call<ServerResponse> retrieveUniversities();

    @POST(VIEW_COMPANIES)
    Call<ServerResponse> retrieveCompanies();

    @POST(VIEW_POSTS)
    Call<ServerResponse> retrievePosts();

    @Multipart
    @POST(CREATE_POST)
    Call<ServerResponse> createPost(@Body HashMap<String, Object> post);

    @POST(VIEW_COMMENTS)
    Call<ServerResponse> retrieveComments(@Body HashMap<String, Integer> postId);

    @POST(ADD_COMMENT)
    Call<ServerResponse> addComment(@Body HashMap<String, Object> comment);

    @POST(VIEW_CHATS)
    Call<ServerResponse> retrieveChats(@Body HashMap<String, Integer> userId);
}
