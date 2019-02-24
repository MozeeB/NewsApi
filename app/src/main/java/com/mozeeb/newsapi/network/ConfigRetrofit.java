package com.mozeeb.newsapi.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {

    public static final String API_URL = "https://newsapi.org/";
    //TODO 1 mensetting retrofit
    //setting alamat webservice
    //add conventer GSON

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //TODO 2 membuat object webservice
    public ApiService service = retrofit.create(ApiService.class);
}
