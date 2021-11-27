package com.PrepLite;

import static com.PrepLite.app.Constants.LOGIN_USER;
import static com.PrepLite.app.Constants.PROFILE_USER;
import static com.PrepLite.app.Constants.REGISTER_USER;
import static com.PrepLite.app.Constants.VIEW_COMPANIES;
import static com.PrepLite.app.Constants.VIEW_UNIVERSITIES;

import com.PrepLite.models.ServerResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
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
}
