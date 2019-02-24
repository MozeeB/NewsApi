package com.mozeeb.newsapi.network;

import com.mozeeb.newsapi.responseapi.ResponseBerita;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    //TODO melakukan proses getdata pada url
    @GET("v2/everything?q=bitcoin&from=2019-01-23&sortBy=publishedAt&apiKey=7659de32d86f41bfbf3864f433c6252b")
    Call<ResponseBerita>getBerita();
}
