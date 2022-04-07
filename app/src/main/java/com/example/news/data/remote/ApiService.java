package com.example.news.data.remote;

import com.example.news.data.model.ResponseN;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("top-headlines?country=ru&apiKey=ab5ce5ad9f2746dca1124e780e89b096")
    Call<ResponseN> getNews();

}
