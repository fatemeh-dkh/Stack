package com.example.fatemeh_d.stack;


import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("questions?page=1&pagesize=10&order=desc&sort=activity&site=stackoverflow")
    Call<MyJsonResponse> readjson();
}