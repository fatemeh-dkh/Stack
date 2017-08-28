package com.example.fatemeh_d.stack;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitSetting {
    APIService apiService ;
    Retrofit retrofit ;
    public RetrofitSetting()
    {
        OkHttpClient client = new OkHttpClient();
        retrofit = new Retrofit.Builder().baseUrl("http://api.stackexchange.com/2.2/").client(client).addConverterFactory(GsonConverterFactory.create()).build();
      apiService = retrofit.create(APIService.class);


    }
public APIService getApiService()
{
    return apiService;
}
public Retrofit getRetrofit()
{
    return retrofit ;
}
}
