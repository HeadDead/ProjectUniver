package com.example.psutipower.api;

import com.example.psutipower.requests.LoginRequest;
import com.example.psutipower.requests.OrganizationRequest;
import com.example.psutipower.requests.RegistrationRequest;
import com.example.psutipower.responses.LoginResponse;
import com.example.psutipower.responses.OrganizationResponse;
import com.example.psutipower.responses.RegistrationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegistrationApi {
    @POST("/auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("/auth/registrationuser")
    Call<RegistrationResponse> registration(@Body RegistrationRequest registrationRequest);

    @POST("/auth/registration")
    Call<OrganizationResponse> registrationOrganization(@Body OrganizationRequest organizationRequest);
}
