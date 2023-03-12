package com.example.freeswitchandroid.rest;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface GetPressOneAPI {

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwaG9uZU51bWJlciI6IjEyMzQ1Njc4OTAiLCJjb21wYW55SWQiOiIyNWNiZjU4Yi00NmJhLTRiYTItYjI1ZC04ZjhmNjUzZTlmMTEiLCJjb3VudHJ5Q29kZSI6IjkxIiwidHlwZSI6MSwiaWQiOiI4N2M2YzEyZS1mYzI1LTQyYzEtOWU1NS1mZGU5MmI3NDEwZGQiLCJpYXQiOjE1ODY5NDMwNjIsImV4cCI6MTU4NzExNTg2Mn0.HusRyEg-p2oF-S5RVnklO6JMQU9GLDYUURnnR5PO0GQ")

    @POST("getCategories")
    Call<Network> getImageData();
}