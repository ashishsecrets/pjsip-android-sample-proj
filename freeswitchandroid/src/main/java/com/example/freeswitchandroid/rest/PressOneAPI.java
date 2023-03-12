package com.example.freeswitchandroid.rest;

import android.content.SharedPreferences;

import com.example.freeswitchandroid.HelperClass;
import com.example.freeswitchandroid.rest.model.BusinessNumber;
import com.example.freeswitchandroid.rest.model.Mobile;
import com.example.freeswitchandroid.rest.model.MobileData;
import com.example.freeswitchandroid.rest.model.Token;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface PressOneAPI {

    String token = HelperClass.AuthHelper.authToken;

    @POST("/api/login/")
    Call<Void> getMyData(@Body Mobile mobile);

    @POST("/auth/token/")
    Call<Token> getAuthToken(@Body MobileData mobileData);

    @GET("/api/calls/")
    Call<BusinessNumber> getCallsData();
}