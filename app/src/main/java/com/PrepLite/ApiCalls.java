package com.PrepLite;

import static com.PrepLite.app.Constants.REGISTER_USER;

import com.PrepLite.response.ResponseLogin;
import com.PrepLite.response.ResponseRegister;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiCalls {

//    @FormUrlEncoded
//    @POST("register")
//    Call<ResponseRegister> register(
//            @Field("name") String name,
//            @Field("email") String email,
//            @Field("password") String password,
//            @Field("yearOfStudy") String year,
//            @Field("alumni") String alumni
//
//    );

    @POST(REGISTER_USER)
    Call<ResponseRegister> register(@Body HashMap<String, String> registerCredentials);

    @FormUrlEncoded
    @POST("login")
    Call<ResponseLogin> login(
            @Field("email") String email,
            @Field("password") String password

    );
}
