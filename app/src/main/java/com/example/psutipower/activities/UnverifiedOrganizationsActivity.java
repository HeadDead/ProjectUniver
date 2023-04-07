package com.example.psutipower.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.psutipower.R;
import com.example.psutipower.adapters.ApplicationsFromUsersAdapter;
import com.example.psutipower.adapters.UnverifiedOrganizationsAdapter;
import com.example.psutipower.api.AdminApi;
import com.example.psutipower.api.OrganizationApi;
import com.example.psutipower.responses.ApplicationsFromUsersResponse;
import com.example.psutipower.responses.UnverifiedOrganizationsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UnverifiedOrganizationsActivity extends AppCompatActivity {

    Intent oneOrganizationIntent;
    ListView unverifiedOrganizationsList;
    ArrayList<UnverifiedOrganizationsResponse> unverifiedOrganizationsResponseArrayList = new ArrayList<>();
    UnverifiedOrganizationsAdapter unverifiedOrganizationsAdapter;
    AdminApi adminApi;
    SharedPreferences sharedPreferences;

    static final String BASE_URL = "http://10.0.2.2:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unverified_organizations);

        oneOrganizationIntent = new Intent(UnverifiedOrganizationsActivity.this, OneUnverifiedOrganizationActivity.class);
        unverifiedOrganizationsList = findViewById(R.id.unverified_organizations);
        sharedPreferences = getSharedPreferences("AdminData", MODE_PRIVATE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        adminApi = connectRetrofit().create(AdminApi.class);
        Call<List<UnverifiedOrganizationsResponse>> call =
                adminApi.outputUnverifiedOrganizations("Bearer " +
                        sharedPreferences.getString("AuthToken", ""));
        call.enqueue(new Callback<List<UnverifiedOrganizationsResponse>>() {
            @Override
            public void onResponse(Call<List<UnverifiedOrganizationsResponse>> call, Response<List<UnverifiedOrganizationsResponse>> response) {
                if (response.isSuccessful()) {
                    Log.d("ConnectSuccessful", response.toString());

                    for (int i = 0; i < response.body().size(); i++) {
                        unverifiedOrganizationsResponseArrayList.add(
                                new UnverifiedOrganizationsResponse(response.body().get(i).getId(),
                                        response.body().get(i).getShortName())
                        );
                    }

                    unverifiedOrganizationsAdapter =
                            new UnverifiedOrganizationsAdapter(UnverifiedOrganizationsActivity.this,
                                    unverifiedOrganizationsResponseArrayList);

                    unverifiedOrganizationsList.setAdapter(unverifiedOrganizationsAdapter);

                    unverifiedOrganizationsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            sharedPreferences.edit().putInt("UnverifiedOrgId", response.body().get(position).getId()).apply();
                            startActivity(oneOrganizationIntent);
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Ошибка подключения", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<UnverifiedOrganizationsResponse>> call, Throwable t) {
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
