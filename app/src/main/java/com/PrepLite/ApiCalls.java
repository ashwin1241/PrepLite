package com.PrepLite;

import com.PrepLite.response.ResponseRegister;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiCalls {
    @FormUrlEncoded
    @POST("register")
    Call<ResponseRegister> register(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password

    );
}
