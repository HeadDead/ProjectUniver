package com.example.psutipower.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.psutipower.R;
import com.example.psutipower.adapters.OrganizationItemAdapter;
import com.example.psutipower.api.UserApi;
import com.example.psutipower.responses.AllApplicationsResponse;
import com.example.psutipower.responses.SubmitApplicationResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChooseOrganizationActivity extends AppCompatActivity {

    int applicationId;

    ListView organizationsList;
    ArrayList<AllApplicationsResponse> applications = new ArrayList<>();
    OrganizationItemAdapter organizationItemAdapter;
    TextView idTextView;
    UserApi userApi;
    SharedPreferences sharedPreferences;

    static final String BASE_URL = "http://10.0.2.2:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_organization);

        organizationsList = findViewById(R.id.organizationsListView);
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        userApi = connectRetrofit().create(UserApi.class);
        Call<List<AllApplicationsResponse>> call = userApi.allApplications("Bearer " + sharedPreferences.getString("AuthToken", ""));
        call.enqueue(new Callback<List<AllApplicationsResponse>>() {
            @Override
            public void onResponse(Call<List<AllApplicationsResponse>> call, Response<List<AllApplicationsResponse>> response) {
                if (response.isSuccessful()) {
                    Log.d("ConnectSuccessful", response.toString());

                    for (int i = 0; i < response.body().size(); i++) {
                        applications.add(new AllApplicationsResponse(response.body().get(i).getId(),
                                response.body().get(i).getFirstName(),
                                response.body().get(i).getOrganizationName(),
                                response.body().get(i).getAddress(),
                                response.body().get(i).getPrice()));
                    }

                    organizationItemAdapter = new OrganizationItemAdapter(ChooseOrganizationActivity.this, applications);
                    organizationsList.setAdapter(organizationItemAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<AllApplicationsResponse>> call, Throwable t) {
                Log.d("ConnectNotSuccessful", t.toString());
            }
        });
    }

    public void setSubmitApplicationButton(View view) {
        idTextView = findViewById(R.id.application_id);
        applicationId = Integer.parseInt(idTextView.getText().toString());

        Call<SubmitApplicationResponse> call = userApi.submitApplication(applicationId,
                "Bearer " + sharedPreferences.getString("AuthToken", ""));

        call.enqueue(new Callback<SubmitApplicationResponse>() {
            @Override
            public void onResponse(Call<SubmitApplicationResponse> call, Response<SubmitApplicationResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Заявка успешно создана", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SubmitApplicationResponse> call, Throwable t) {

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
