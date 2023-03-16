package com.example.freeswitchandroid.rest;


import com.example.freeswitchandroid.rest.model.BusinessNumber;
import com.example.freeswitchandroid.rest.model.CallDetail;
import com.example.freeswitchandroid.rest.model.CallsEndDatum;
import com.example.freeswitchandroid.rest.model.Mobile;
import com.example.freeswitchandroid.rest.model.MobileData;
import com.example.freeswitchandroid.rest.model.Token;
import com.example.freeswitchandroid.rest.model.UserDatum;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface PressOneAPI {

    @POST("/api/login/")
    Call<Void> getMyData(@Body Mobile mobile);

    @POST("/auth/token/")
    Call<Token> getAuthToken(@Body MobileData mobileData);

    @GET("/api/calls/")
    Call<List<CallDetail>> getCallsData(@Header("Authorization") String token, @Query("business_id") String business_id);

    @GET("/api/users/me/")
    Call<UserDatum> getBusinessNumbers(@Header("Authorization") String token);
}