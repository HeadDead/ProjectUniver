package com.example.psutipower.api;

import com.example.psutipower.requests.CreateApplicationRequest;
import com.example.psutipower.responses.ApplicationsFromUsersResponse;
import com.example.psutipower.responses.CreateApplicationResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface OrganizationApi {
    @POST("/createapplication")
    Call<CreateApplicationResponse> createApplication(@Header("Authorization") String token,
                                                      @Body CreateApplicationRequest createApplicationRequest);

    @GET("/usersubmit")
    Call<List<ApplicationsFromUsersResponse>> outputApplicationsFromUsers(@Header("Authorization") String token);
}
