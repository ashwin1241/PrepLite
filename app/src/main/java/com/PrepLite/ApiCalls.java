package com.PrepLite;

import static com.PrepLite.app.Constants.ADD_COMMENT;
import static com.PrepLite.app.Constants.CREATE_POST;
import static com.PrepLite.app.Constants.DELETE_COMMENT;
import static com.PrepLite.app.Constants.LOGIN_USER;
import static com.PrepLite.app.Constants.PROFILE_USER;
import static com.PrepLite.app.Constants.REGISTER_USER;
import static com.PrepLite.app.Constants.UPDATE_PROFILE_PIC;
import static com.PrepLite.app.Constants.UPLOAD_MATERIALS;
import static com.PrepLite.app.Constants.VIEW_CHATS;
import static com.PrepLite.app.Constants.VIEW_COMMENTS;
import static com.PrepLite.app.Constants.VIEW_COMPANIES;
import static com.PrepLite.app.Constants.VIEW_COMPANY_POSTS;
import static com.PrepLite.app.Constants.VIEW_POSTS;
import static com.PrepLite.app.Constants.VIEW_UNIVERSITIES;
import static com.PrepLite.app.Constants.VIEW_UNIVERSITY_POSTS;
import static com.PrepLite.app.Constants.VIEW_USERS;

import com.PrepLite.models.Attachment;
import com.PrepLite.models.ImageResponse;
import com.PrepLite.models.ServerResponse;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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

    @POST(VIEW_COMPANY_POSTS)
    Call<ServerResponse> retrieveCompanyPosts(@Body HashMap<String, Integer> company_id);

    @POST(VIEW_UNIVERSITY_POSTS)
    Call<ServerResponse> retrieveUniversityPosts(@Body HashMap<String, Integer> university_id);

    @POST(CREATE_POST)
    Call<ServerResponse> createPost(
            @Body HashMap<String, Object> post);

    @Multipart
    @POST(UPLOAD_MATERIALS)
    Call<ServerResponse> uploadMaterial(@Part ArrayList<MultipartBody.Part> attachments);

    @POST(VIEW_COMMENTS)
    Call<ServerResponse> retrieveComments(@Body HashMap<String, Integer> postId);

    @POST(ADD_COMMENT)
    Call<ServerResponse> addComment(@Body HashMap<String, Object> comment);

    @POST(DELETE_COMMENT)
    Call<ServerResponse> deleteComment(@Body HashMap<String, Integer> comment_id);

    @POST(VIEW_CHATS)
    Call<ServerResponse> retrieveChats(@Body HashMap<String, Integer> userId);

    @POST(VIEW_USERS)
    Call<ServerResponse> retrieveUsers(@Body HashMap<String, Integer> userId);

    @FormUrlEncoded
    @POST("https://api.imgur.com/3/image")
    Call<ImageResponse> postImage(@Header("Authorization") String auth,
                                  @Field("image") String encoded);

    @POST(UPDATE_PROFILE_PIC)
    Call<ServerResponse> updateProfilePic(@Body HashMap<String, Object> map);

}
