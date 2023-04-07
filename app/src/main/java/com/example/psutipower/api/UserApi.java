package com.example.psutipower.api;

import com.example.psutipower.responses.AllApplicationsResponse;
import com.example.psutipower.responses.SubmitApplicationResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApi {
    @GET("/allapplications")
    Call<List<AllApplicationsResponse>> allApplications(@Header("Authorization") String token);
    @POST("/submitapplication")
    Call<SubmitApplicationResponse> submitApplication(@Query ("id") int id, @Header("Authorization") String token);
}
