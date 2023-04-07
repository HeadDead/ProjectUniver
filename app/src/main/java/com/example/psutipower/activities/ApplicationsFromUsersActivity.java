package com.example.psutipower.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.psutipower.R;
import com.example.psutipower.adapters.ApplicationsFromUsersAdapter;
import com.example.psutipower.adapters.OrganizationItemAdapter;
import com.example.psutipower.api.OrganizationApi;
import com.example.psutipower.api.UserApi;
import com.example.psutipower.responses.AllApplicationsResponse;
import com.example.psutipower.responses.ApplicationsFromUsersResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationsFromUsersActivity extends AppCompatActivity {

    Intent organizationMenuIntent;
    ListView applicationsFromUsersList;
    ArrayList<ApplicationsFromUsersResponse> applications = new ArrayList<>();
    ApplicationsFromUsersAdapter applicationsFromUsersAdapter;
    OrganizationApi organizationApi;
    SharedPreferences sharedPreferences;

    static final String BASE_URL = "http://10.0.2.2:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applications_from_users);

        organizationMenuIntent = new Intent(ApplicationsFromUsersActivity.this, OrganizationMenuActivity.class);
        applicationsFromUsersList = findViewById(R.id.applicationsFromUsersListView);
        sharedPreferences = getSharedPreferences("OrgData", MODE_PRIVATE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        organizationApi = connectRetrofit().create(OrganizationApi.class);
        Call<List<ApplicationsFromUsersResponse>> call =
                organizationApi.outputApplicationsFromUsers("Bearer " +
                        sharedPreferences.getString("AuthToken", ""));
        call.enqueue(new Callback<List<ApplicationsFromUsersResponse>>() {
            @Override
            public void onResponse(Call<List<ApplicationsFromUsersResponse>> call, Response<List<ApplicationsFromUsersResponse>> response) {
                if (response.isSuccessful()) {
                    Log.d("ConnectSuccessful", response.toString());

                    for (int i = 0; i < response.body().size(); i++) {
                        applications.add(new ApplicationsFromUsersResponse(response.body().get(i).getFirstName(),
                                response.body().get(i).getUsername(),
                                response.body().get(i).getPhoneNumber()));
                    }

                    applicationsFromUsersAdapter =
                            new ApplicationsFromUsersAdapter(ApplicationsFromUsersActivity.this, applications);

                    applicationsFromUsersList.setAdapter(applicationsFromUsersAdapter);
                } else {
                    Toast.makeText(getApplicationContext(), "Ошибка подключения", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ApplicationsFromUsersResponse>> call, Throwable t) {
                Log.d("ConnectNotSuccessful", t.toString());
            }
        });

    }

    public Retrofit connectRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
