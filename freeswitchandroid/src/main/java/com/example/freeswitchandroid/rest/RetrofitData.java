package com.example.freeswitchandroid.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitData {

    public static final String BASE_URL = "https://api.pressone.co/";
    public static Retrofit retrofit = null;

    public static Retrofit getRetrofit(){

        if(retrofit == null){

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
