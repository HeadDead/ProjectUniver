package com.example.psutipower.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.psutipower.R;
import com.example.psutipower.adapters.OneUnverifiedOrganizationAdapter;
import com.example.psutipower.api.AdminApi;
import com.example.psutipower.responses.ApproveOrganizationResponse;
import com.example.psutipower.responses.OneUnverifiedOrganizationResponse;
import com.example.psutipower.responses.UnverifiedOrganizationsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OneUnverifiedOrganizationActivity extends AppCompatActivity {

    TextView usernameTextView;

    Intent unverifiedOrganizationsIntent;
    AdminApi adminApi;
    OneUnverifiedOrganizationAdapter oneUnverifiedOrganizationAdapter;
    ArrayList<OneUnverifiedOrganizationResponse> oneUnverifiedOrganizationResponseArrayList = new ArrayList<>();
    ListView oneUnverifiedOrganizationListView;
    static final String BASE_URL = "http://10.0.2.2:8080/";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_unverified_organization);
        oneUnverifiedOrganizationListView = findViewById(R.id.one_unverified_organization_list);
        sharedPreferences = getSharedPreferences("AdminData", MODE_PRIVATE);
        unverifiedOrganizationsIntent = new Intent(OneUnverifiedOrganizationActivity.this,
                UnverifiedOrganizationsActivity.class);

    }

    public Retrofit connectRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();

        adminApi = connectRetrofit().create(AdminApi.class);
        Call<List<OneUnverifiedOrganizationResponse>> call = adminApi.outputInfoAboutOneOrganization(
                sharedPreferences.getString("AuthKey", "Bearer " +
                        sharedPreferences.getString("AuthToken", "")));

        call.enqueue(new Callback<List<OneUnverifiedOrganizationResponse>>() {
            @Override
            public void onResponse(Call<List<OneUnverifiedOrganizationResponse>> call, Response<List<OneUnverifiedOrganizationResponse>> response) {

                if (response.isSuccessful()) {
                    Log.d("ConnectSuccessful", response.toString());

                    for (int i = 0; i < response.body().size(); i++) {
                        oneUnverifiedOrganizationResponseArrayList.add(
                                new OneUnverifiedOrganizationResponse(response.body().get(i).getId(),
                                        response.body().get(i).getFirstName(), response.body().get(i).getLastName(),
                                        response.body().get(i).getPatronymic(), response.body().get(i).getPhoneNumber(),
                                        response.body().get(i).getUsername(), response.body().get(i).getEmail(),
                                        response.body().get(i).getOrganizationFullName(), response.body().get(i).getOrganizationShortName(),
                                        response.body().get(i).getInn(), response.body().get(i).getKpp(), response.body().get(i).getOgrn(),
                                        response.body().get(i).getAddress())
                        );
                    }

                    oneUnverifiedOrganizationAdapter = new OneUnverifiedOrganizationAdapter(OneUnverifiedOrganizationActivity.this, oneUnverifiedOrganizationResponseArrayList);
                    oneUnverifiedOrganizationListView.setAdapter(oneUnverifiedOrganizationAdapter);
                } else {
                    Toast.makeText(getApplicationContext(), "Ошибка подключения", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<OneUnverifiedOrganizationResponse>> call, Throwable t) {
                Log.d("ConnectNotSuccessful", t.toString());
            }
        });
    }

    public void approveButton(View view) {
        adminApi = connectRetrofit().create(AdminApi.class);

        Toast.makeText(getApplicationContext(), "Организация успешно подтверждена", Toast.LENGTH_SHORT).show();
    }
}
