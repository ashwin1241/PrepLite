package com.PrepLite;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {

    private  static  String BASE_URL = "https://b612-112-133-232-80.ngrok.io/";
    private static  Client client;
    private  static Retrofit retrofit;

    private Client(){
     retrofit =  new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }

    public static synchronized Client getInstance(){

        if(client == null){
            client= new Client();
        }
        return  client;
    }

    public ApiCalls getApi(){
        return retrofit.create(ApiCalls.class);
    }
}
