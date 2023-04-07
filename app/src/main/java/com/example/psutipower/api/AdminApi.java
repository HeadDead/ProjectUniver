package com.example.psutipower.api;

import com.example.psutipower.requests.CreateApplicationRequest;
import com.example.psutipower.responses.ApproveOrganizationResponse;
import com.example.psutipower.responses.CreateApplicationResponse;
import com.example.psutipower.responses.OneUnverifiedOrganizationResponse;
import com.example.psutipower.responses.UnverifiedOrganizationsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.Query;

public interface AdminApi {
    @GET("/onenonapproveorganization")
    Call<List<UnverifiedOrganizationsResponse>> outputUnverifiedOrganizations(@Header("Authorization") String token);
    @GET("/checkorganization")
    Call<List<OneUnverifiedOrganizationResponse>> outputInfoAboutOneOrganization(@Header("Authorization") String token);
    @PATCH("/approve")
    Call<ApproveOrganizationResponse> approveOrganization(@Query ("login") String login, @Header("Authorization") String token);
}
